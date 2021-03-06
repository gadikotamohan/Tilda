/* ===========================================================================
 * Copyright (C) 2015 CapsicoHealth Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tilda.db;

import java.sql.Array;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tilda.data.ZoneInfo_Data;
import tilda.db.metadata.FKMeta;
import tilda.db.metadata.PKMeta;
import tilda.db.processors.RecordProcessor;
import tilda.db.stores.DBType;
import tilda.enums.AggregateType;
import tilda.enums.ColumnMode;
import tilda.enums.ColumnType;
import tilda.enums.TransactionType;
import tilda.generation.interfaces.CodeGenSql;
import tilda.parsing.parts.Column;
import tilda.parsing.parts.ForeignKey;
import tilda.parsing.parts.Object;
import tilda.parsing.parts.Schema;
import tilda.parsing.parts.View;
import tilda.performance.PerfTracker;
import tilda.types.ColumnDefinition;
import tilda.types.Type_DatetimePrimitive;
import tilda.utils.AnsiUtil;
import tilda.utils.CollectionUtil;
import tilda.utils.DurationUtil.IntervalEnum;
import tilda.utils.SystemValues;
import tilda.utils.pairs.StringStringPair;


public final class Connection
  {
    static final Logger LOG = LogManager.getLogger(Connection.class.getName());

    public Connection(java.sql.Connection C)
      throws Exception,
      SQLException
      {
      }

    public Connection(java.sql.Connection C, String PoolId)
      throws Exception,
      SQLException
      {
        _C = C;
        _Url = _C.getMetaData().getURL();
        // LDH-NOTE: YEAH YEAH.... This is ugly!!! Need a virtual constructor pattern here.
        _DB = _Url.startsWith("jdbc:postgresql:") ? DBType.Postgres
        : _Url.startsWith("jdbc:sqlserver:") ? DBType.SQLServer
        : _Url.startsWith("jdbc:db2:") ? DBType.DB2
        : null;
        if (_DB == null)
          throw new Exception("Can't find the DBType based on URL " + _Url);
        _PoolId = PoolId;
        _PoolName = PoolId + ": " + getURL() + "USER="+_C.getMetaData().getUserName()+", " + getDatabaseProductName() + " V" + getDatabaseProductVersion();
      }


    protected String              _PoolId;
    protected String              _PoolName;
    protected java.sql.Connection _C;
    protected DBType              _DB;
    protected String              _Url;

    public final String getPoolId()
      {
        return _PoolId;
      }

    public final String getPoolName()
      {
        return _PoolName;
      }

    public final String getURL()
      {
        return _Url;
      }

    public final String getDatabaseProductName()
    throws SQLException
      {
        return _C.getMetaData().getDatabaseProductName();
      }

    public final String getDatabaseProductVersion()
    throws SQLException
      {
        return _C.getMetaData().getDatabaseProductVersion();
      }

    public final String getDBTypeName()
      {
        return _DB.getName();
      }

    /**
     * Wrapper to {@link java.sql.Connection#commit()} with extra logging and performance tracking
     * 
     * @return
     * @return an SQException (instead of throwing) if one happens.
     * @throws SQLException
     */
    public final void commit()
    throws SQLException
      {
        try
          {
            LOG.info(QueryDetails._LOGGING_HEADER + AnsiUtil.NEGATIVE + "C O M M I T" + AnsiUtil.NEGATIVE_OFF + "                              -----  " + _PoolName);
            long T0 = System.nanoTime();
            _C.commit();
            _SavePoints.clear();
            PerfTracker.add(TransactionType.COMMIT, System.nanoTime() - T0);
          }
        catch (SQLException E)
          {
            LOG.error(AnsiUtil.NEGATIVE + ">>>>>>>>>>>>>>>" + AnsiUtil.NEGATIVE_OFF + "  F A I L U R E   C O M M I T  " + AnsiUtil.NEGATIVE + "<<<<<<<<<<<<<<<" + AnsiUtil.NEGATIVE_OFF);
            LOG.catching(E);
            LOG.error(SystemValues.NEWLINEx2);
            _SavePoints.clear();
            throw E;
          }
      }

    /**
     * Wrapper to {@link java.sql.Connection#rollback()} with extra logging and performance tracking
     * 
     * @return an SQException (instead of throwing) if one happens.
     */
    public final void rollback()
    throws SQLException
      {
        try
          {
            LOG.info(QueryDetails._LOGGING_HEADER + AnsiUtil.NEGATIVE + "R O L L B A C K" + AnsiUtil.NEGATIVE_OFF + "                          -----  " + _PoolName);
            long T0 = System.nanoTime();
            _C.rollback();
            _SavePoints.clear();
            PerfTracker.add(TransactionType.ROLLBACK, System.nanoTime() - T0);
          }
        catch (SQLException E)
          {
            LOG.error(AnsiUtil.NEGATIVE + ">>>>>>>>>>>>>>>" + AnsiUtil.NEGATIVE_OFF + "  F A I L U R E   R O L L B A C K  " + AnsiUtil.NEGATIVE + "<<<<<<<<<<<<<<<" + AnsiUtil.NEGATIVE_OFF);
            LOG.catching(E);
            LOG.error(SystemValues.NEWLINEx2);
            _SavePoints.clear();
            throw E;
          }
      }

    /**
     * Wrapper to {@link #commit()} or {@link #rollback()} based on parameter
     * 
     * @param Commit true if commit is needed, or false if rollback
     * @return an SQException (instead of throwing) if one happens.
     * @throws SQLException
     */
    public final void CommitRollbackConnection(boolean Commit)
    throws SQLException
      {
        if (Commit == true)
          commit();
        else
          rollback();
      }

    /**
     * Wrapper to {@link java.sql.Connection#close()} with extra logging and performance tracking
     * 
     * @return an SQException (instead of throwing) if one happens.
     * @throws SQLException
     */
    public final void close()
    throws SQLException
      {
        try
          {
            LOG.info(QueryDetails._LOGGING_HEADER + "C L O S I N G   A N D   R E T U R N I N G   C O N N E C T I O N  -----  " + _PoolName);
            long T0 = System.nanoTime();
            _C.close();
            _SavePoints.clear();
            PerfTracker.add(TransactionType.CONNECTION_CLOSE, System.nanoTime() - T0);
            _C = null;
          }
        catch (SQLException E)
          {
            LOG.error(AnsiUtil.NEGATIVE + ">>>>>>>>>>>>>>>" + AnsiUtil.NEGATIVE_OFF + "  F A I L U R E   C L O S I N G   C O N N E C T I O N  " + AnsiUtil.NEGATIVE + "<<<<<<<<<<<<<<<" + AnsiUtil.NEGATIVE_OFF, E);
            LOG.catching(E);
            LOG.error(SystemValues.NEWLINEx2);
            _SavePoints.clear();
            _C = null;
            throw E;
          }
      }

    /**
     * Straight wrapper to {@link java.sql.Connection#createStatement()}
     * 
     * @return
     * @throws SQLException
     */
    public final Statement createStatement()
    throws SQLException
      {
        return _C.createStatement();
      }


    public String getIsolation()
    throws SQLException
      {
        switch (_C.getTransactionIsolation())
          {
            case java.sql.Connection.TRANSACTION_NONE:
              return "TRANSACTION_NONE";
            case java.sql.Connection.TRANSACTION_READ_UNCOMMITTED:
              return "TRANSACTION_READ_UNCOMMITTED";
            case java.sql.Connection.TRANSACTION_READ_COMMITTED:
              return "TRANSACTION_READ_COMMITTED";
            case java.sql.Connection.TRANSACTION_REPEATABLE_READ:
              return "TRANSACTION_REPEATABLE_READ";
            case java.sql.Connection.TRANSACTION_SERIALIZABLE:
              return "TRANSACTION_SERIALIZABLE";
            default:
              return "TRANSACTION_UNKNOWN";
          }
      }

    /**
     * Straight wrapper to {@link java.sql.Connection#prepareStatement(String)}
     * 
     * @return
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String Q)
    throws SQLException
      {
        return _C.prepareStatement(Q);
      }

    public boolean isErrNoData(String SQLState, int ErrorCode)
    throws SQLException
      {
        return _DB.isErrNoData(SQLState, ErrorCode);
      }

    public String getCurrentTimestampStr()
    throws SQLException
      {
        return _DB.getCurrentTimestampStr();
      }

    public boolean isLockOrConnectionError(Throwable T)
    throws SQLException
      {
        if (T == null)
          return false;

        if (T instanceof SQLException == false)
          {
            T = T.getCause();
            if (T == null || T instanceof SQLException == false)
              return false;
          }

        if (T.getMessage() == null)
          return false;

        return _DB.isLockOrConnectionError((SQLException) T);
      }


    public int ExecuteSelect(String SchemaName, String TableName, String Query, RecordProcessor RP)
    throws Exception
      {
        return JDBCHelper.ExecuteSelect(_C, SchemaName, TableName, Query, RP);
      }

    /**
     * Executes a query with a record processor, starting at Start (0 is beginning), and for Size records.
     */
    public int ExecuteSelect(String SchemaName, String TableName, String Query, RecordProcessor RP, int Start, boolean Offsetted, int Size, boolean Limited)
    throws Exception
      {
        return ExecuteSelect(SchemaName, TableName, Query, RP, Start, Offsetted, Size, Limited, false);
      }

    /**
     * Executes a query with a record processor, starting at Start (0 is beginning), and for Size records.
     */
    public int ExecuteSelect(String SchemaName, String TableName, String Query, RecordProcessor RP, int Start, boolean Offsetted, int Size, boolean Limited, boolean CountAll)
    throws Exception
      {
        return JDBCHelper.ExecuteSelect(_C, SchemaName, TableName, Query, RP, Start, Offsetted, Size, Limited, CountAll);
      }


    public int ExecuteUpdate(String SchemaName, String TableName, String Query)
    throws Exception
      {
        return JDBCHelper.ExecuteUpdate(_C, SchemaName, TableName, Query);
      }

    public int ExecuteInsert(String SchemaName, String TableName, String Query)
    throws Exception
      {
        return JDBCHelper.ExecuteInsert(_C, SchemaName, TableName, Query);
      }


    public boolean ExecuteDDL(String SchemaName, String TableName, String Query)
    throws Exception
      {
        return JDBCHelper.ExecuteDDL(_C, SchemaName, TableName, Query);
      }

    public Array createArrayOf(String TypeName, java.lang.Object[] A)
    throws SQLException
      {
        return _C.createArrayOf(TypeName, A);
      }

    Deque<Savepoint> _SavePoints = new ArrayDeque<Savepoint>();

    public void setSavepoint()
    throws SQLException
      {
        if (_DB.needsSavepoint() == false)
          return;
        long T0 = System.nanoTime();
        _SavePoints.add(_C.setSavepoint());
        PerfTracker.add(TransactionType.SAVEPOINT_SET, System.nanoTime() - T0);
      }

    public void releaseSavepoint(boolean commit)
    throws SQLException
      {
        if (_SavePoints.isEmpty() == true)
          return;

        long T0 = System.nanoTime();
        if (commit == true)
          {
            _C.releaseSavepoint(_SavePoints.pop());
            PerfTracker.add(TransactionType.SAVEPOINT_COMMIT, System.nanoTime() - T0);
          }
        else
          {
            _C.rollback(_SavePoints.pop());
            PerfTracker.add(TransactionType.SAVEPOINT_ROLLBACK, System.nanoTime() - T0);
          }
      }

    public CodeGenSql getSQlCodeGen()
      {
        return _DB.getSQlCodeGen();
      }

    public DatabaseMetaData getMetaData()
    throws SQLException
      {
        return _C.getMetaData();
      }

    public boolean alterTableAddColumn(Column Col, String DefaultValue)
    throws Exception
      {
        return _DB.alterTableAddColumn(this, Col, DefaultValue);
      }

    public boolean alterTableDropColumn(Object Obj, String ColumnName)
    throws Exception
      {
        return _DB.alterTableDropColumn(this, Obj, ColumnName);
      }

    public boolean alterTableAlterColumnNull(Column Col, String DefaultValue)
    throws Exception
      {
        return _DB.alterTableAlterColumnNull(this, Col, DefaultValue);
      }

    public boolean createTable(Object Obj)
    throws Exception
      {
        return _DB.createTable(this, Obj);
      }

    public boolean createKeysEntry(Object Obj)
    throws Exception
      {
        return _DB.createKeysEntry(this, Obj);
      }

    public boolean dropView(View V)
    throws Exception
      {
        return _DB.dropView(this, V);
      }

    public boolean createView(View V)
    throws Exception
      {
        return _DB.createView(this, V);
      }

    public boolean createSchema(Schema S)
    throws Exception
      {
        return _DB.createSchema(this, S);
      }

    public String getAggregateStr(AggregateType Agg)
      {
        return _DB.getAggregateStr(Agg);
      }

    public boolean supportsSelectLimit()
      {
        return _DB.supportsSelectLimit();
      }

    public boolean supportsSelectOffset()
      {
        return _DB.supportsSelectLimit();
      }

    public String getSelectLimitClause(int Start, int Size)
      {
        return _DB.getSelectLimitClause(Start, Size);
      }

    public int getCLOBThreshhold()
      {
        return _DB.getCLOBThreshhold();
      }

    public boolean alterTableAlterColumnStringSize(Column Col, int DBSize)
    throws Exception
      {
        return _DB.alterTableAlterColumnStringSize(this, Col, DBSize);
      }

    public boolean alterTableAlterColumnType(ColumnType FromType, Column Col, ZoneInfo_Data defaultZI)
    throws Exception
      {
        return _DB.alterTableAlterColumnType(this, FromType, Col, defaultZI);
      }

    public String getHelperFunctionsScript()
    throws Exception
      {
        return _DB.getHelperFunctionsScript(this);
      }

    public String getAclRolesScript(List<Schema> TildaList)
    throws Exception
      {
        return _DB.getAclRolesScript(this, TildaList);
      }

    public StringStringPair getTypeMapping(int Type, String Name, int Size, String TypeName)
    throws Exception
      {
        return _DB.getTypeMapping(Type, Name, Size, TypeName);
      }

    public boolean supportsArrays()
      {
        return _DB.supportsArrays();
      }

    public java.lang.Object getEqualCurrentTimestamp()
      {
        return "=" + _DB.getCurrentTimestampStr();
      }

    public java.lang.Object getCommaCurrentTimestamp()
      {
        return ", " + _DB.getCurrentTimestampStr();
      }

    public void getFullColumnVar(StringBuilder Str, String SchemaName, String TableName, String ColumnName)
      {
        _DB.getFullColumnVar(Str, SchemaName, TableName, ColumnName);
      }

    public void getColumnType(StringBuilder Str, ColumnType T, Integer S, ColumnMode M, boolean Collection)
      {
        _DB.getColumnType(Str, T, S, M, Collection);
      }


    public void getFullTableVar(StringBuilder Str, String SchemaName, String TableName)
      {
        _DB.getFullTableVar(Str, SchemaName, TableName);
      }

    public void setArray(PreparedStatement PS, int i, ColumnType Type, List<java.sql.Array> allocatedArrays, String[] val)
    throws Exception
      {
        _DB.setArray(this, PS, i, Type, allocatedArrays, CollectionUtil.toList(val));
      }

    public void setArray(PreparedStatement PS, int i, ColumnType Type, List<java.sql.Array> allocatedArrays, Collection<?> val)
    throws Exception
      {
        _DB.setArray(this, PS, i, Type, allocatedArrays, val);
      }

    public Collection<?> getArray(ResultSet RS, int i, ColumnType Type, boolean isSet)
    throws Exception
      {
        return _DB.getArray(RS, i, Type, isSet);
      }

    public String getJsonParametrizedQueryPlaceHolder()
      {
        return _DB.getJsonParametrizedQueryPlaceHolder();
      }

    public void setOrderByWithNullsOrdering(StringBuilder Str, ColumnDefinition Col, boolean Asc, boolean NullsLast)
      {
        _DB.setOrderByWithNullsOrdering(this, Str, Col, Asc, NullsLast);
      }

    public void truncateTable(String SchemaName, String TableName, boolean cascade)
    throws Exception
      {
        _DB.truncateTable(this, SchemaName, TableName, cascade);
      }

    // public void setTableLogging(String SchemaName, String TableName, boolean Logged) throws Exception
    // {
    // _DB.setTableLogging(this, SchemaName, TableName, Logged);
    // }

    public void age(StringBuilder Str, Type_DatetimePrimitive ColStart, Type_DatetimePrimitive ColEnd, IntervalEnum Type, int Count, String Operator)
      {
        _DB.age(this, Str, ColStart, ColEnd, Type, Count, Operator);
      }

    public boolean alterTableComment(Object Obj)
    throws Exception
      {
        return _DB.alterTableComment(this, Obj);
      }

    public boolean alterTableAlterColumnComment(Column Col)
    throws Exception
      {
        return _DB.alterTableAlterColumnComment(this, Col);
      }

    public void within(StringBuilder Str, Type_DatetimePrimitive Col, Type_DatetimePrimitive ColStart, long DurationCount, IntervalEnum DurationType)
      {
        _DB.within(this, Str, Col, ColStart, DurationCount, DurationType);
      }


    public boolean HandleCatch(java.sql.SQLException E, String OperationDebugStr)
    throws java.sql.SQLException
      {
        if (isLockOrConnectionError(E) == true)
          QueryDetails.setLastQueryDeadlocked();
        else if (E.getSQLState() == null)
          {
            LOG.warn("JDBC Error: No row " + OperationDebugStr + ": SQLState is null, ErrorCode=" + E.getErrorCode());
            LOG.warn("JDBC Message: " + E.getMessage());
            return false;
          }
        else if (isErrNoData(E.getSQLState(), E.getErrorCode()) == true)
          {
            LOG.warn("JDBC Error: No row " + OperationDebugStr + ": SQLState=" + E.getSQLState() + ", ErrorCode=" + E.getErrorCode());
            LOG.warn("JDBC Message: " + E.getMessage());
            return false;
          }
        LOG.error("JDBC Error: Fatal sql error: SQLState=" + E.getSQLState() + ", ErrorCode=" + E.getErrorCode());
        LOG.catching(E);
        throw E;
      }

    public boolean alterTableReplaceTablePK(Object Obj, PKMeta OldPK)
    throws Exception
      {
        return _DB.alterTableReplaceTablePK(this, Obj, OldPK);
      }

    public boolean alterTableDropFK(Object Obj, FKMeta FK)
    throws Exception
      {
        return _DB.alterTableDropFK(this, Obj, FK);
      }

    public boolean alterTableAddFK(ForeignKey FK)
    throws Exception
      {
        return _DB.alterTableAddFK(this, FK);
      }

    public boolean isSuperUser()
    throws Exception
      {
        return _DB.isSuperUser(this);
      }
  }



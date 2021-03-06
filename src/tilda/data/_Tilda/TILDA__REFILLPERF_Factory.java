
package tilda.data._Tilda;

import java.time.*;
import java.util.*;

import tilda.db.*;
import tilda.enums.*;
import tilda.types.*;
import tilda.utils.*;
import tilda.utils.pairs.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class TILDA__REFILLPERF_Factory
 {
   protected static final Logger LOG = LogManager.getLogger(TILDA__REFILLPERF_Factory.class.getName());

   protected TILDA__REFILLPERF_Factory() { }

   public static final Class<TILDA__REFILLPERF> DATA_CLASS= TILDA__REFILLPERF.class;
   public static final String SCHEMA_LABEL = TextUtil.Print("TILDA", "");
   public static final String TABLENAME_LABEL = TextUtil.Print("REFILLPERF", "");
   public static final String SCHEMA_TABLENAME_LABEL = TextUtil.Print("TILDA.REFILLPERF", "");
   public static void getFullTableNameVar(Connection C, StringBuilder S) { C.getFullTableVar(S, "TILDA", "REFILLPERF"); }

   public static abstract class COLS {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.schemaName -> TILDA.REFILLPERF."schemaName"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.schemaName of type String</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.schemaName of type varchar(64)</TD></TR>

  <TR><TD align="right"><B>Size</B></TD><TD>64</TD></TR>
  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The name of the schema tracked</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>NORMAL</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>true</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_StringPrimitive        SCHEMANAME   = new Type_StringPrimitive       (SCHEMA_LABEL, TABLENAME_LABEL, "schemaName"   , 0/*0*/, "The name of the schema tracked");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.objectName -> TILDA.REFILLPERF."objectName"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.objectName of type String</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.objectName of type varchar(64)</TD></TR>

  <TR><TD align="right"><B>Size</B></TD><TD>64</TD></TR>
  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The name of the table/object tracked</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>NORMAL</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>true</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_StringPrimitive        OBJECTNAME   = new Type_StringPrimitive       (SCHEMA_LABEL, TABLENAME_LABEL, "objectName"   , 1/*1*/, "The name of the table/object tracked");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.startPeriodTZ -> TILDA.REFILLPERF."startPeriodTZ"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.startPeriodTZ of type String</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.startPeriodTZ of type character(5)</TD></TR>

  <TR><TD align="right"><B>Size</B></TD><TD>5</TD></TR>
  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>Generated helper column to hold the time zone ID for 'startPeriod'.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>AUTO</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>true</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_StringPrimitive        STARTPERIODTZ= new Type_StringPrimitive       (SCHEMA_LABEL, TABLENAME_LABEL, "startPeriodTZ", 2/*2*/, "Generated helper column to hold the time zone ID for 'startPeriod'.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.startPeriod -> TILDA.REFILLPERF."startPeriod"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.startPeriod of type ZonedDateTime</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.startPeriod of type timestamptz</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The timestamp for when the refill started.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>NORMAL</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>true</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_DatetimePrimitive      STARTPERIOD  = new Type_DatetimePrimitive     (SCHEMA_LABEL, TABLENAME_LABEL, "startPeriod"  , 3/*3*/, "The timestamp for when the refill started.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.timeCreateMs -> TILDA.REFILLPERF."timeCreateMs"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.timeCreateMs of type long</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.timeCreateMs of type bigint</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The time, in milliseconds, the create took.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>NORMAL</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>false</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_LongPrimitive          TIMECREATEMS = new Type_LongPrimitive         (SCHEMA_LABEL, TABLENAME_LABEL, "timeCreateMs" , 4/*4*/, "The time, in milliseconds, the create took.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.timeIndexMs -> TILDA.REFILLPERF."timeIndexMs"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.timeIndexMs of type long</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.timeIndexMs of type bigint</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The time, in milliseconds, the indexing took.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>NORMAL</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>false</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_LongPrimitive          TIMEINDEXMS  = new Type_LongPrimitive         (SCHEMA_LABEL, TABLENAME_LABEL, "timeIndexMs"  , 5/*5*/, "The time, in milliseconds, the indexing took.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.timeAnalyzeMs -> TILDA.REFILLPERF."timeAnalyzeMs"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.timeAnalyzeMs of type long</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.timeAnalyzeMs of type bigint</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The time, in milliseconds, the analyze took.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>NORMAL</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>false</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_LongPrimitive          TIMEANALYZEMS= new Type_LongPrimitive         (SCHEMA_LABEL, TABLENAME_LABEL, "timeAnalyzeMs", 6/*6*/, "The time, in milliseconds, the analyze took.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.timeTotalMs -> TILDA.REFILLPERF."timeTotalMs"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.timeTotalMs of type long</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.timeTotalMs of type bigint</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The time, in milliseconds, the whole refill took.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>NORMAL</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>false</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_LongPrimitive          TIMETOTALMS  = new Type_LongPrimitive         (SCHEMA_LABEL, TABLENAME_LABEL, "timeTotalMs"  , 7/*7*/, "The time, in milliseconds, the whole refill took.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.columnsMs -> TILDA.REFILLPERF."columnsMs"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.columnsMs of type long</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.columnsMs of type bigint</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The list of columns that were refilled.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>NORMAL</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>false</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_LongPrimitive          COLUMNSMS    = new Type_LongPrimitive         (SCHEMA_LABEL, TABLENAME_LABEL, "columnsMs"    , 8/*8*/, "The list of columns that were refilled.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.created -> TILDA.REFILLPERF."created"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.created of type ZonedDateTime</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.created of type timestamptz</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The timestamp for when the record was created.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>AUTO</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>true</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
  <TR valign="top"><TD align="right"><B>Values</B></TD><TD>

<TABLE border="0px" cellpadding="2px" cellspacing="0px">   <TR align="left"><TH>&nbsp;</TH><TH align="right">Name&nbsp;&nbsp;</TH><TH>Value&nbsp;&nbsp;</TH><TH>Label&nbsp;&nbsp;</TH><TH>Default&nbsp;&nbsp;</TH><TH>Groupings&nbsp;&nbsp;</TH><TH>Description</TH></TR>
  <TR bgcolor="#FFFFFF"><TD>0&nbsp;&nbsp;</TD><TD align="right"><B>Creation</B>&nbsp;&nbsp;</TD><TD>NOW&nbsp;&nbsp;</TD><TD>Creation&nbsp;&nbsp;</TD><TD>CREATE&nbsp;&nbsp;</TD><TD>&nbsp;&nbsp;</TD><TD>Creation time</TD></TR>
</TABLE>
</TD></TR>

</TABLE>
*/
     public static Type_DatetimePrimitive      CREATED      = new Type_DatetimePrimitive     (SCHEMA_LABEL, TABLENAME_LABEL, "created"      , 9/*9*/, "The timestamp for when the record was created.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.lastUpdated -> TILDA.REFILLPERF."lastUpdated"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.lastUpdated of type ZonedDateTime</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.lastUpdated of type timestamptz</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>false</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The timestamp for when the record was last updated.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>AUTO</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>false</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
  <TR valign="top"><TD align="right"><B>Values</B></TD><TD>

<TABLE border="0px" cellpadding="2px" cellspacing="0px">   <TR align="left"><TH>&nbsp;</TH><TH align="right">Name&nbsp;&nbsp;</TH><TH>Value&nbsp;&nbsp;</TH><TH>Label&nbsp;&nbsp;</TH><TH>Default&nbsp;&nbsp;</TH><TH>Groupings&nbsp;&nbsp;</TH><TH>Description</TH></TR>
  <TR bgcolor="#FFFFFF"><TD>0&nbsp;&nbsp;</TD><TD align="right"><B>Update</B>&nbsp;&nbsp;</TD><TD>NOW&nbsp;&nbsp;</TD><TD>Update&nbsp;&nbsp;</TD><TD>ALWAYS&nbsp;&nbsp;</TD><TD>&nbsp;&nbsp;</TD><TD>Last updated time</TD></TR>
</TABLE>
</TD></TR>

</TABLE>
*/
     public static Type_DatetimePrimitive      LASTUPDATED  = new Type_DatetimePrimitive     (SCHEMA_LABEL, TABLENAME_LABEL, "lastUpdated"  , 10/*10*/, "The timestamp for when the record was last updated.");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   Field tilda.data.TILDA.REFILLPERF.deleted -> TILDA.REFILLPERF."deleted"
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
This is the column definition for:<BR>
<TABLE border="0px" cellpadding="3px" cellspacing="0px">
  <TR><TD align="right"><B>Name</B></TD><TD>tilda.data.TILDA.REFILLPERF.deleted of type ZonedDateTime</TD></TR>
  <TR><TD align="right"><B>Column</B></TD><TD>TILDA.REFILLPERF.deleted of type timestamptz</TD></TR>

  <TR><TD align="right"><B>Nullable</B></TD><TD>true</TD></TR>
  <TR valign="top"><TD align="right"><B>Description</B></TD><TD>The timestamp for when the record was deleted.</TD></TR>
  <TR><TD align="right"><B>Mode</B></TD><TD>AUTO</TD></TR>
  <TR><TD align="right"><B>Invariant</B></TD><TD>false</TD></TR>
  <TR><TD align="right"><B>Protect</B></TD><TD>NONE</TD></TR>
</TABLE>
*/
     public static Type_DatetimePrimitiveNull  DELETED      = new Type_DatetimePrimitiveNull (SCHEMA_LABEL, TABLENAME_LABEL, "deleted"      , 11/*11*/, "The timestamp for when the record was deleted.");
;
   }

   private static Boolean  __INITIALIZED = false;
   protected static void initObject(Connection C) throws Exception
     {
       if (__INITIALIZED == false)
        synchronized(__INITIALIZED)
         {
           if (__INITIALIZED == false)
            {
              tilda.data.RefillPerf_Factory.init(C);
              __INITIALIZED = true;
            }
         }
     }
   private static class RecordProcessorInternal implements tilda.db.processors.RecordProcessor
     {
       public RecordProcessorInternal(Connection C, int Start)
         {
           _C = C;
           _L = new ArrayListResults<tilda.data.RefillPerf_Data>(Start);
         }
       public RecordProcessorInternal(Connection C, tilda.db.processors.ObjectProcessor<tilda.data.RefillPerf_Data> OP)
         {
           _C = C;
           _OP = OP;
         }
       protected Connection _C = null;
       protected tilda.db.processors.ObjectProcessor<tilda.data.RefillPerf_Data> _OP;
       protected ArrayListResults<tilda.data.RefillPerf_Data> _L = null;
       public void    Start  () { }
       public void    End    (boolean HasMore, int Max) { if (_OP == null) _L.wrapup(HasMore, Max); }
       public boolean Process(int Index, java.sql.ResultSet RS) throws Exception
        {
          tilda.data.RefillPerf_Data Obj = new tilda.data.RefillPerf_Data();
          boolean OK = ((tilda.data._Tilda.TILDA__REFILLPERF)Obj).Init(_C, RS);
          if (OK == true)
           {
             if (_OP == null)
              _L.add(Obj);
             else
              _OP.Process(Index, Obj);
           }
          return OK;
        }
     }

   private static final void ReadMany(Connection C, int LookupId, tilda.db.processors.RecordProcessor RP, tilda.data._Tilda.TILDA__REFILLPERF Obj, Object ExtraParams, int Start, int Size) throws Exception
     {
       long T0 = System.nanoTime();
       StringBuilder S = new StringBuilder(1024);
       S.append("select ");
       S.append(" "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "schemaName");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "objectName");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "startPeriodTZ");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "startPeriod");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "timeCreateMs");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "timeIndexMs");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "timeAnalyzeMs");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "timeTotalMs");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "columnsMs");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "created");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "lastUpdated");
       S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "deleted");
       S.append(" from "); C.getFullTableVar(S, "TILDA", "REFILLPERF");
       switch (LookupId)
        {
          case -7:
             String clause = ((SelectQuery)ExtraParams).getWhereClause();
             if (TextUtil.isNullOrEmpty(clause) == false) S.append(clause);
             break;
          case 1:
             S.append(" where ("); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "schemaName"); S.append("=?)");
             S.append(" order by "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "objectName"); S.append(" ASC");S.append(", "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "startPeriod"); S.append(" DESC");
             break;
          case 2:
             S.append(" where ("); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "schemaName"); S.append("=? AND "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "objectName"); S.append("=?)");
             S.append(" order by "); C.getFullColumnVar(S, "TILDA", "REFILLPERF", "startPeriod"); S.append(" DESC");
             break;
          case -666: break;
          default: throw new Exception("Invalid LookupId "+LookupId+" found. Cannot create where clause.");
        }

       
       String Q = S.toString() + C.getSelectLimitClause(Start, Size+1);
       S.setLength(0);
       S = null;
       QueryDetails.setLastQuery(SCHEMA_TABLENAME_LABEL, Q);
       QueryDetails.logQuery("TILDA.REFILLPERF", Q, null);
       java.sql.PreparedStatement PS=null;
       int count = 0;
       try
        {
          PS = C.prepareStatement(Q);
          int i = 0;
          switch (LookupId)
           {
             case -7:
                break;
             case 1: {
               PS.setString   (++i, Obj._schemaName   );
               break;
             }
             case 2: {
               PS.setString   (++i, Obj._schemaName   );
               PS.setString   (++i, Obj._objectName   );
               break;
             }
             case -666: break;
             default: throw new Exception("Invalid LookupId "+LookupId+" found. Cannot prepare statement.");
           }


          count = JDBCHelper.Process(PS.executeQuery(), RP, Start, true, Size, true);
        }
       catch (java.sql.SQLException E)
        {
          tilda.data._Tilda.TILDA__1_0.HandleCatch(C, E, "selected");
        }
       finally
        {
          tilda.data._Tilda.TILDA__1_0.HandleFinally(PS, T0, TILDA__REFILLPERF_Factory.SCHEMA_TABLENAME_LABEL, StatementType.SELECT, count, null);
          PS = null;
        }

    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 Creates a new object in memory, which you can subsequently {@link #Write()} to the data store.
 current object to the destination. 
 @param schemaName    (max size 64) The name of the schema tracked
 @param objectName    (max size 64) The name of the table/object tracked
 @param startPeriod   The timestamp for when the refill started.
 @param timeCreateMs  The time, in milliseconds, the create took.
 @param timeIndexMs   The time, in milliseconds, the indexing took.
 @param timeAnalyzeMs The time, in milliseconds, the analyze took.
 @param timeTotalMs   The time, in milliseconds, the whole refill took.
 @param columnsMs     The list of columns that were refilled.
*/
   static public tilda.data.RefillPerf_Data Create(String schemaName, String objectName, ZonedDateTime startPeriod, long timeCreateMs, long timeIndexMs, long timeAnalyzeMs, long timeTotalMs, long columnsMs) throws Exception
     {
       tilda.data._Tilda.TILDA__REFILLPERF Obj = new tilda.data.RefillPerf_Data();
       Obj.initForCreate();


       // Explicit setters
       Obj.setSchemaName   (schemaName   );
       Obj.setObjectName   (objectName   );
       Obj.setStartPeriod  (startPeriod  );
       Obj.setTimeCreateMs (timeCreateMs );
       Obj.setTimeIndexMs  (timeIndexMs  );
       Obj.setTimeAnalyzeMs(timeAnalyzeMs);
       Obj.setTimeTotalMs  (timeTotalMs  );
       Obj.setColumnsMs    (columnsMs    );

       // Default Create-time setters
       Obj.setCreatedNow       ();
       Obj.setLastUpdatedNow   ();

       return (tilda.data.RefillPerf_Data) Obj;
     }

   static public tilda.data.RefillPerf_Data Create(Map<String, String> Values, List<StringStringPair> Errors)
   throws Exception
     {
       int IncomingErrors = Errors.size();

       String        _schemaName    =                       ParseUtil.parseString("schemaName"   , true , Values.get("schemaName"   ), Errors );
       String        _objectName    =                       ParseUtil.parseString("objectName"   , true , Values.get("objectName"   ), Errors );
       ZonedDateTime        _startPeriod   =                       ParseUtil.parseZonedDateTime("startPeriod"  , true , Values.get("startPeriod"  ), Errors );
       Long        _timeCreateMs  =                       ParseUtil.parseLong("timeCreateMs" , true , Values.get("timeCreateMs" ), Errors );
       Long        _timeIndexMs   =                       ParseUtil.parseLong("timeIndexMs"  , true , Values.get("timeIndexMs"  ), Errors );
       Long        _timeAnalyzeMs =                       ParseUtil.parseLong("timeAnalyzeMs", true , Values.get("timeAnalyzeMs"), Errors );
       Long        _timeTotalMs   =                       ParseUtil.parseLong("timeTotalMs"  , true , Values.get("timeTotalMs"  ), Errors );
       Long        _columnsMs     =                       ParseUtil.parseLong("columnsMs"    , true , Values.get("columnsMs"    ), Errors );

       if (IncomingErrors != Errors.size())
        return null;

      tilda.data.RefillPerf_Data Obj = tilda.data.RefillPerf_Factory.Create(_schemaName, _objectName, _startPeriod, _timeCreateMs, _timeIndexMs, _timeAnalyzeMs, _timeTotalMs, _columnsMs);


      return Obj;
     }

   static public tilda.data.RefillPerf_Data LookupByPrimaryKey(String schemaName, String objectName, ZonedDateTime startPeriod) throws Exception
     {
       tilda.data._Tilda.TILDA__REFILLPERF Obj = new tilda.data.RefillPerf_Data();
       Obj.initForLookup(0);

       Obj.setSchemaName   (schemaName   ); Obj.__Saved_schemaName    = Obj._schemaName   ;
       Obj.setObjectName   (objectName   ); Obj.__Saved_objectName    = Obj._objectName   ;
       Obj.setStartPeriod  (startPeriod  ); Obj.__Saved_startPeriod   = Obj._startPeriod  ;

       return (tilda.data.RefillPerf_Data) Obj;
     }

   static public ListResults<tilda.data.RefillPerf_Data> LookupWhereSchemaByObjectStart(Connection C, String schemaName, int Start, int Size) throws Exception
     {
       tilda.data._Tilda.TILDA__REFILLPERF Obj = new tilda.data.RefillPerf_Data();
       Obj.initForLookup(tilda.utils.SystemValues.EVIL_VALUE);

       Obj.setSchemaName   (schemaName   );


       RecordProcessorInternal RPI = new RecordProcessorInternal(C, Start);
       ReadMany(C, 1, RPI, Obj, null, Start, Size);
       return RPI._L;
     }

   static public void LookupWhereSchemaByObjectStart(Connection C, tilda.db.processors.ObjectProcessor<tilda.data.RefillPerf_Data> OP, String schemaName, int Start, int Size) throws Exception
     {
       tilda.data._Tilda.TILDA__REFILLPERF Obj = new tilda.data.RefillPerf_Data();
       Obj.initForLookup(tilda.utils.SystemValues.EVIL_VALUE);

       Obj.setSchemaName   (schemaName   );


       RecordProcessorInternal RPI = new RecordProcessorInternal(C, OP);
       ReadMany(C, 1, RPI, Obj, null, Start, Size);
     }


   static public ListResults<tilda.data.RefillPerf_Data> LookupWhereSchemaObjectByStart(Connection C, String schemaName, String objectName, int Start, int Size) throws Exception
     {
       tilda.data._Tilda.TILDA__REFILLPERF Obj = new tilda.data.RefillPerf_Data();
       Obj.initForLookup(tilda.utils.SystemValues.EVIL_VALUE);

       Obj.setSchemaName   (schemaName   );
       Obj.setObjectName   (objectName   );


       RecordProcessorInternal RPI = new RecordProcessorInternal(C, Start);
       ReadMany(C, 2, RPI, Obj, null, Start, Size);
       return RPI._L;
     }

   static public void LookupWhereSchemaObjectByStart(Connection C, tilda.db.processors.ObjectProcessor<tilda.data.RefillPerf_Data> OP, String schemaName, String objectName, int Start, int Size) throws Exception
     {
       tilda.data._Tilda.TILDA__REFILLPERF Obj = new tilda.data.RefillPerf_Data();
       Obj.initForLookup(tilda.utils.SystemValues.EVIL_VALUE);

       Obj.setSchemaName   (schemaName   );
       Obj.setObjectName   (objectName   );


       RecordProcessorInternal RPI = new RecordProcessorInternal(C, OP);
       ReadMany(C, 2, RPI, Obj, null, Start, Size);
     }



   public static SelectQuery newSelectQuery(Connection C) throws Exception { return new SelectQuery(C, SCHEMA_LABEL, TABLENAME_LABEL, true); }
   public static SelectQuery newWhereQuery (Connection C) throws Exception { return new SelectQuery(C, SCHEMA_LABEL, TABLENAME_LABEL, false); }
   public static ListResults<tilda.data.RefillPerf_Data> runSelect(Connection C, SelectQuery Q, int Start, int Size) throws Exception
     {
       RecordProcessorInternal RPI = new RecordProcessorInternal(C, Start);
       ReadMany(C, -7, RPI, null, Q, Start, Size);
       return RPI._L;
     }
   public static void runSelect(Connection C, SelectQuery Q, tilda.db.processors.ObjectProcessor<tilda.data.RefillPerf_Data> OP, int Start, int Size) throws Exception
     {
       RecordProcessorInternal RPI = new RecordProcessorInternal(C, OP);
       ReadMany(C, -7, RPI, null, Q, Start, Size);
     }
   public static UpdateQuery newUpdateQuery(Connection C) throws Exception { return new UpdateQuery(C, SCHEMA_LABEL, TABLENAME_LABEL); }
   public static DeleteQuery newDeleteQuery(Connection C) throws Exception { return new DeleteQuery(C, SCHEMA_LABEL, TABLENAME_LABEL); }

 }

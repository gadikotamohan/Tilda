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

package tilda.db.metadata;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tilda.db.Connection;

public class TableMeta
  {
    static final Logger LOG = LogManager.getLogger(TableMeta.class.getName());

    public TableMeta(String SchemaName, String TableName, String Descr)
      {
        _SchemaName = SchemaName;
        _TableName = TableName;
        _Descr = Descr;
      }

    public final String            _SchemaName;
    public final String            _TableName;
    public final String            _Descr;
    public Map<String, ColumnMeta> _Columns = new HashMap<String, ColumnMeta>();
    public Map<String, IndexMeta>  _Indices = new HashMap<String, IndexMeta>();

    public void load(Connection C)
    throws Exception
      {
        DatabaseMetaData meta = C.getMetaData();
        ResultSet RS = meta.getColumns(null, _SchemaName.toLowerCase(), _TableName.toLowerCase(), null);
        while (RS.next() != false)
          {
            ColumnMeta CI = new ColumnMeta(C, RS);
            _Columns.put(CI._Name, CI);
          }

        RS = meta.getIndexInfo(null, _SchemaName.toLowerCase(), _TableName.toLowerCase(), true, true);
        loadIndices(RS);
        RS = meta.getIndexInfo(null, _SchemaName.toLowerCase(), _TableName.toLowerCase(), false, true);
        loadIndices(RS);
      }

    private void loadIndices(ResultSet RS)
    throws SQLException, Exception
      {
        while (RS.next() != false)
          {
            IndexMeta IM = new IndexMeta(RS);
            IndexMeta prevIM = _Indices.get(IM._Name);
            if (prevIM == null)
              _Indices.put(IM._Name, IM);
            else
              IM = prevIM;
            IM.addColumn(RS);
          }
      }

    public ColumnMeta getColumnMeta(String ColumnName)
      {
        return _Columns.get(ColumnName.toLowerCase());
      }
  }

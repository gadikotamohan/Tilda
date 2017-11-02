package tilda.loader.csv.stores;

import java.util.Map;

import org.apache.commons.csv.CSVRecord;

import tilda.data.JobFile_Data;
import tilda.db.Connection;
import tilda.db.metadata.ColumnMeta;
import tilda.loader.parser.ColumnHeader;
import tilda.loader.parser.DataObject;

public class IBMDB2CSVImporter extends CSVImporter
  {

    public IBMDB2CSVImporter(Connection C, String rootFolder, DataObject cmsDO, Connection status, JobFile_Data jobFile)
      {
        this.C = C;
        this.rootFolder = rootFolder;
        this.cmsDO = cmsDO;
        this.statusConnection = status;
        this.jobFile = jobFile;
      }    
    
    @Override
    protected long insertData(boolean isUpsert, long t0, Map<String, ColumnMeta> DBColumns, boolean withHeader, Iterable<CSVRecord> records, StringBuilder Str, String schemaName, String tableName, String[] headers, String[] columns, Map<String, ColumnHeader> columnMap, String[] completeHeaders, String DateTimePattern, String DateTimeZoneInfoId, String DatePattern)
      {
        return 0;
      }
  
    @Override
    protected StringBuilder GenerateSQL(boolean isUpsert, String schemaName, String tableName, String[] columns, Map<String, ColumnMeta> DBColumns, String[] uniqueColumns)
      {
        StringBuilder Str = GenerateInsertSQL(schemaName, tableName, columns, DBColumns);
        return Str;
      }
    
  }

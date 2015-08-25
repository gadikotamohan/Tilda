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

package tilda.parsing.parts;

import java.util.ArrayList;
import java.util.List;

import tilda.enums.ColumnMapperMode;
import tilda.enums.MultiType;
import tilda.parsing.ParserSession;
import tilda.parsing.parts.helpers.ReferenceHelper;
import tilda.parsing.parts.helpers.ValidationHelper;
import tilda.utils.TextUtil;

import com.google.gson.annotations.SerializedName;

public class ColumnEnum
  {

    /*@formatter:off*/
    @SerializedName("srcColumns") public String[]          _SrcColumns ;
    @SerializedName("destObject") public String            _DestObject ;
    @SerializedName("multi"     ) public MultiType         _Multi  = MultiType.NONE;
    /*@formatter:on*/

    public transient List<Column> _SrcColumnObjs = new ArrayList<Column>();
    public transient Object       _DestObjectObj;
    public transient Column       _ParentColumn ;
    
    public ColumnEnum()
     {
     }
    
    public ColumnEnum(String[] SrcColumns, String DestObject, MultiType Multi)
      {
        SrcColumns = _SrcColumns;
        DestObject = _DestObject;
        Multi      = _Multi;
      }

    public boolean Validate(ParserSession PS, Column C)
      {
        int Errs = PS.getErrorCount();

        _ParentColumn = C;
        
        ValidateDestinationObject(PS);
        ValidateSourceColumns(PS);
        _SrcColumnObjs.add(_ParentColumn);

        if (Errs != PS.getErrorCount())
          return false;

        ForeignKey.CheckForeignKeyMapping(PS, _ParentColumn._ParentObject, _SrcColumnObjs, _DestObjectObj, "enum");
        
        return Errs == PS.getErrorCount();
      }

    private boolean ValidateSourceColumns(ParserSession PS)
      {
        if (_SrcColumns != null && _SrcColumns.length> 0)
         _SrcColumnObjs = ValidationHelper.ProcessColumn(PS, _ParentColumn._ParentObject, "enum", _SrcColumns, null);

        return true;
      }
    
    private boolean ValidateDestinationObject(ParserSession PS)
      {
        if (TextUtil.isNullOrEmpty(_DestObject) == true)
          return PS.AddError("Column '" + _ParentColumn.getFullName() + "' is defining a mapper without a destination object.");

        ReferenceHelper R = ReferenceHelper.parseObjectReference(_DestObject, _ParentColumn._ParentObject.getSchema());
        if (TextUtil.isNullOrEmpty(R._S) == true || TextUtil.isNullOrEmpty(R._O) == true)
         return PS.AddError("Column '" + _ParentColumn.getFullName() + "' declares mapper with an incorrect syntax for the destination object '" + _DestObject + "'. It should be '((package\\.)?schema\\.)?object'.");
        
        _DestObjectObj = PS.getObject(R._P, R._S, R._O);
        if (_DestObjectObj == null)
         return PS.AddError("Column '" + _ParentColumn.getFullName() + "' declares mapper with destination Object '" + _DestObject + "' resolving to '"+R.getFullName()+"' which cannot be found.");

        return true;
      }
    
  }

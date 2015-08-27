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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewColumnWrapper extends Column
  {
    static final Logger              LOG                = LogManager.getLogger(View.class.getName());

    public ViewColumnWrapper(Column Col, ViewColumn VCol)
      {
        _VCol             = VCol;
        
        _Name             = Col.getName();
        _SameAs           = Col._SameAs;
        if (_SameAs == null)
         {
           _TypeStr          = Col._TypeStr;
           _Size             = Col._Size;
         }
        
        _Nullable         = Col._Nullable;
        _ModeStr          = Col._ModeStr;
        _Invariant        = Col._Invariant;
        _ProtectStr       = Col._ProtectStr;
        _Description      = Col._Description;
        if (Col._Mapper != null)
         {
           _Mapper = new ColumnMapper(Col._Mapper._SrcColumns, Col._Mapper._DestObject, Col._Mapper._Name, Col._Mapper._Group, Col._Mapper._Multi);
         }
        if (Col._Enum != null)
          {
            _Enum = new ColumnEnum(Col._Enum._SrcColumns, Col._Enum._DestObject, Col._Enum._Multi);
          }
        if (Col._Values != null)
         {
           _Values           = new ColumnValue[Col._Values.length];
           for (int i = 0; i < Col._Values.length; ++i)
            {
              ColumnValue cv = Col._Values[i];
              _Values[i] = new ColumnValue(cv._Name, cv._Value, cv._Label, cv._Description, cv._Default);
            }
         }
//        _FrameworkManaged = Col._FrameworkManaged;
        
//        _Mode               = Col._Mode;
//        _Protect            = Col._Protect;
//        _SameAsObj          = Col._SameAsObj;
//        _ParentObject       = Col._ParentObject;
//        _PadderValueNames   = Col._PadderValueNames;
//        _PadderValueValues  = Col._PadderValueValues;
//        _PrimaryKey         = Col._PrimaryKey;
//        _UniqueIndex        = Col._UniqueIndex;
//        _MapperDef          = Col._MapperDef;
//        _DefaultCreateValue = Col._DefaultCreateValue;
//        _DefaultUpdateValue = Col._DefaultUpdateValue;
      }
    
    protected ViewColumn _VCol;
    
    @Override
    public String getFullName()
      {
        return _VCol.getFullName();
      }

    @Override
    public String getShortName()
      {
        return _VCol.getShortName();
      }
    
    @Override
    public String getName()
      {
        return _VCol.getName();
      }
  }
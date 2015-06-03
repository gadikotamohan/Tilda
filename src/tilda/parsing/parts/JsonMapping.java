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

import java.util.List;

import tilda.enums.ColumnType;
import tilda.parsing.ParserSession;
import tilda.parsing.parts.helpers.ValidationHelper;

import com.google.gson.annotations.SerializedName;

public class JsonMapping
  {

    public JsonMapping()
      {
      }

    /*@formatter:off*/
    @SerializedName("name"   ) public String   _Name;
    @SerializedName("columns") public String[] _Columns;
    @SerializedName("sync"   ) public boolean  _Sync = false;
    /*@formatter:on*/

    public transient List<Column> _ColumnObjs;

    public transient IThing       _ParentThing;

    public boolean Validate(ParserSession PS, IThing Thing)
      {
        int Errs = PS.getErrorCount();
        _ParentThing = Thing;

        _ColumnObjs = ValidationHelper.ProcessColumn(PS, Thing, "JSONMapping '" + _Name + "'", _Columns, new ValidationHelper.Processor() {
          @Override
          public boolean process(ParserSession PS, IThing Thing, String What, Column C)
            {
              if (C._Type == ColumnType.BINARY)
                PS.AddError("Object '" + _ParentThing.getFullName() + "' is defining a JSON mapping with column '" + C._Name + "' which is a binary. Binaries cannot be JSONed.");
              return true;
            }
        });

        return Errs == PS.getErrorCount();
      }
  }

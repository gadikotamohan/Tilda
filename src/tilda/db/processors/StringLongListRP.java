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

package tilda.db.processors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tilda.utils.pairs.StringLongPair;

public class StringLongListRP implements RecordProcessor
  {
    public void Start()
      {
      };

    @Override
    public boolean Process(int Index, ResultSet RS)
      throws SQLException
      {
        String s = RS.getString(1);
        long   l = RS.getLong  (2);
        _Res.add(new StringLongPair(s, l));
        _TotalSum+=l;
        return true;
      }

    @Override
    public void End(boolean hasMore, int MaxIndex)
      {
      }

    protected List<StringLongPair> _Res = new ArrayList<StringLongPair>();
    protected long _TotalSum = 0;

    public List<StringLongPair> getResult()
      {
        return _Res;
      }
    public long getTotalSum()
      {
        return _TotalSum;
      }
    
  }


package tilda.data._Tilda;

import java.time.*;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tilda.db.*;
import tilda.enums.*;
import tilda.performance.*;
import tilda.utils.*;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings({ "unused" })
public class TILDA__DATELIMITDIM_Json
 {
   static final Logger             LOG                = LogManager.getLogger(TILDA__DATELIMITDIM_Json.class.getName());

   protected TILDA__DATELIMITDIM_Json() { }

   /*@formatter:off*/
   @SerializedName("invalidDate") public LocalDate  _invalidDate;
   @SerializedName("minDate"    ) public LocalDate  _minDate    ;
   @SerializedName("maxDate"    ) public LocalDate  _maxDate    ;
   /*@formatter:on*/

   public tilda.data.DateLimitDim_Data Write(Connection C) throws Exception
    {
      if (_invalidDate == null)
       throw new Exception("Incoming value for 'tilda.data.TILDA.DateLimitDim.invalidDate' was null or empty. It's not nullable in the model.\n"+toString());
      if (_minDate     == null)
       throw new Exception("Incoming value for 'tilda.data.TILDA.DateLimitDim.minDate' was null or empty. It's not nullable in the model.\n"+toString());
      if (_maxDate     == null)
       throw new Exception("Incoming value for 'tilda.data.TILDA.DateLimitDim.maxDate' was null or empty. It's not nullable in the model.\n"+toString());

      tilda.data.DateLimitDim_Data Obj = tilda.data.DateLimitDim_Factory.Create(_invalidDate, _minDate, _maxDate);
      Update(Obj);
      if (Obj.Write(C) == false)
       {
         Obj = tilda.data.DateLimitDim_Factory.LookupByInvalidDate(_invalidDate);
         if (Obj.Read(C) == false)
          throw new Exception("Cannot create the tilda.data.TILDA.DateLimitDim object.\n"+toString());
         if (_minDate    != null) Obj.setMinDate    (_minDate    );
         if (_maxDate    != null) Obj.setMaxDate    (_maxDate    );
         if (Obj.Write(C) == false)
          throw new Exception("Cannot update the tilda.data.TILDA.DateLimitDim object: "+Obj.toString());

       }
      return Obj;
   }

   public void Update(tilda.data.DateLimitDim_Data Obj) throws Exception
    {
      if (_invalidDate!= null) Obj.setInvalidDate(_invalidDate);
      if (_minDate    != null) Obj.setMinDate    (_minDate    );
      if (_maxDate    != null) Obj.setMaxDate    (_maxDate    );
    }

   public String toString()
    {
      return
             "invalidDate"+ (_invalidDate == null ? ": NULL" : ": " + _invalidDate)
         + "; minDate"    + (_minDate     == null ? ": NULL" : ": " + _minDate    )
         + "; maxDate"    + (_maxDate     == null ? ": NULL" : ": " + _maxDate    )
         + ";";
    }

 }
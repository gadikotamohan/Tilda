
package tilda.data._Tilda;

import java.io.*;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tilda.db.*;
import tilda.enums.*;
import tilda.performance.*;
import tilda.utils.*;

import com.google.gson.annotations.SerializedName;


public class TILDA__MAINTENANCE_Json
 {
   static final Logger             LOG                = LogManager.getLogger(TILDA__MAINTENANCE_Json.class.getName());

   protected TILDA__MAINTENANCE_Json() { }

   /*@formatter:off*/
   @SerializedName("type"       ) public String  _type       ;
   @SerializedName("name"       ) public String  _name       ;
   @SerializedName("value"      ) public String  _value      ;
   /*@formatter:on*/

   public tilda.data.Maintenance_Data Write(Connection C) throws Exception
    {
      if (TextUtil.isNullOrEmpty(_type       ) == true)
       throw new Exception("Incoming value for 'tilda.data.TILDA.MAINTENANCE.type' was null or empty. It's not nullable in the model.\n"+toString());
      if (TextUtil.isNullOrEmpty(_name       ) == true)
       throw new Exception("Incoming value for 'tilda.data.TILDA.MAINTENANCE.name' was null or empty. It's not nullable in the model.\n"+toString());

      tilda.data.Maintenance_Data Obj = tilda.data.Maintenance_Factory.Create(_type, _name);
      Update(Obj);
      if (Obj.Write(C) == false)
       {
         Obj = tilda.data.Maintenance_Factory.LookupByPrimaryKey(_type, _name);
         if (Obj.Read(C) == false)
          throw new Exception("Cannot create the tilda.data.TILDA.MAINTENANCE object.\n"+toString());
         if (_value      != null) Obj.setValue      (_value      );
         if (Obj.Write(C) == false)
          throw new Exception("Cannot update the tilda.data.TILDA.MAINTENANCE object: "+Obj.toString());

       }
      return Obj;
   }

   public void Update(tilda.data.Maintenance_Data Obj) throws Exception
    {
      if (_type       != null) Obj.setType       (_type       );
      if (_name       != null) Obj.setName       (_name       );
      if (_value      != null) Obj.setValue      (_value      );
    }

   public String toString()
    {
      return
             "type"       + (_type        == null ? ": NULL" : "(" + (_type        == null ? 0 : _type       .length())+"): "+_type)
         + "; name"       + (_name        == null ? ": NULL" : "(" + (_name        == null ? 0 : _name       .length())+"): "+(_name        == null || _name       .length() < 100 ? _name        : _name       .substring(0, 100)+"..."))
         + "; value"      + (_value       == null ? ": NULL" : "(" + (_value       == null ? 0 : _value      .length())+"): "+(_value       == null || _value      .length() < 100 ? _value       : _value      .substring(0, 100)+"..."))
         + ";";
    }

 }

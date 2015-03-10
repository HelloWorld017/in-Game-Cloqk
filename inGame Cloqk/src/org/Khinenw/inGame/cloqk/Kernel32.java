package org.Khinenw.inGame.cloqk;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public interface Kernel32 extends StdCallLibrary {

    public Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("Kernel32", Kernel32.class);

    /**
     * @see http://msdn2.microsoft.com/en-us/library/aa373232.aspx
     */
    public class SYSTEM_POWER_STATUS extends Structure {
    	 public byte ACLineStatus;
         public byte BatteryFlag;
         public byte BatteryLifePercent;
         public byte Reserved1;
         public int BatteryLifeTime;
         public int BatteryFullLifeTime;

        @Override
        protected List<String> getFieldOrder() {
            ArrayList<String> fields = new ArrayList<String>();
            fields.add("ACLineStatus");
            fields.add("BatteryFlag");
            fields.add("BatteryLifePercent");
            fields.add("Reserved1");
            fields.add("BatteryLifeTime");
            fields.add("BatteryFullLifeTime");
            return fields;
        }
        

        public boolean hasToShowBattery() {
           if(ACLineStatus == 0){
        	   return true;
           }
           return false;
        }

        public String getBatteryLifePercent() {
            return (BatteryLifePercent == (byte) 255) ? "Unknown" : BatteryLifePercent + "%";
        }

        @Override
        public String toString() {
        	if(hasToShowBattery()){
        		return getBatteryLifePercent();
        	}
            return "";
        }
        
    }

    public int GetSystemPowerStatus(SYSTEM_POWER_STATUS result);
}

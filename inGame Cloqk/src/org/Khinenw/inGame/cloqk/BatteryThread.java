package org.Khinenw.inGame.cloqk;


import javax.swing.JLabel;

public class BatteryThread extends Thread {
	private long refresh;
	private JLabel battery;
	private Kernel32.SYSTEM_POWER_STATUS batteryStatus;
	
	public BatteryThread(long refresh, JLabel battery){
		this.refresh = refresh;
		this.battery = battery;
		batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
	}
	
	@Override
	public void run(){
		while(true){
			Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
			String s = batteryStatus.toString();
			if(s != ""){
				battery.setText(s);
				battery.setVisible(true);
			}else{
				battery.setVisible(false);
			}
			
			try {
				Thread.sleep(refresh);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

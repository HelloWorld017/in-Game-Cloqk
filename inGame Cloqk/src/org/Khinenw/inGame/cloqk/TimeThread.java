package org.Khinenw.inGame.cloqk;

import java.util.Calendar;

import javax.swing.JLabel;

public class TimeThread extends Thread{
	
	private JLabel time;
	private FromThread fThread;
	private int lValue;
	private boolean isLaunched = false;
	
	public TimeThread(JLabel l, FromThread fThread){
		time = l;
		this.fThread = fThread;
		isLaunched = false;
		Calendar c = Calendar.getInstance();
		lValue = c.get(Calendar.SECOND);
	}
	
	@Override
	public void run(){
		while(true){
			Calendar c = Calendar.getInstance();
			if(!isLaunched && lValue != c.get(Calendar.SECOND)){
				fThread.start();
				isLaunched = true;
			}
			String s =ZeroPads.pad(2, c.get(Calendar.HOUR_OF_DAY)) + ":"
					+ ZeroPads.pad(2, c.get(Calendar.MINUTE)) +":"
					+ ZeroPads.pad(2, c.get(Calendar.SECOND));			
			
			time.setText(s);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e){e.printStackTrace();}
		}
	}
}

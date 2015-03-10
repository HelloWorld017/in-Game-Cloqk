package org.Khinenw.inGame.cloqk;

import javax.swing.JLabel;

public class FromThread extends Thread{
	private int hour = 0;
	private int min = 0;
	private int sec = 1;
	private JLabel from;
	
	public FromThread(JLabel j){
		from = j;
		hour = 0;
		min = 0;
		sec = 1;
	}
	@Override
	public void run(){
		
		while(true){
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			tick();
			from.setText(hour + ":" + ZeroPads.pad(2, min) + ":" + ZeroPads.pad(2, sec));
		}
	}
	
	public void tick(){
		sec++;
		if(sec >= 60){
			min++;
			sec = 0;
		}
		
		if(min >= 60){
			hour++;
			 min = 0;
		}
	}
}

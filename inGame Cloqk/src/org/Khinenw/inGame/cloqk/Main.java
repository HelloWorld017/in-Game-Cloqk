package org.Khinenw.inGame.cloqk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.Khinenw.inGame.cloqk.BatteryThread;
import org.Khinenw.inGame.cloqk.TimeThread;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Main {
	private JLabel time, battery, from;
	private JPanel root;
	private JFrame evanescent;

	private TimeThread tThread;
	private BatteryThread bThread;
	private FromThread fThread;
	
	public Main() throws Exception {
		root = new JPanel();
		
		time = new JLabel("00:00:00", SwingConstants.CENTER);
		time.setFont(new Font("Segoe UI", Font.BOLD, 20));
		time.setForeground(new Color(255, 255, 255));
		
		battery = new JLabel("000%", SwingConstants.LEFT);
		battery.setFont(new Font("Segoe UI", Font.BOLD, 20));
		battery.setForeground(new Color(255, 255, 255));
		
		from = new JLabel("0:00:01", SwingConstants.CENTER);
		from.setFont(new Font("Segoe UI", Font.BOLD, 20));
		from.setForeground(new Color(255, 255, 255));
		
		root.setLayout(new BorderLayout());
		root.add(from, BorderLayout.SOUTH);
		root.add(time, BorderLayout.CENTER);
		root.add(battery, BorderLayout.WEST);
		root.setOpaque(true);
		
		evanescent = new JFrame("in-Game cloqk");
		evanescent.setContentPane(root);
		evanescent.setUndecorated(true);
		evanescent.setBackground(new Color(0, 0, 0, 179));
		evanescent.setAlwaysOnTop(true);
		evanescent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		evanescent.setVisible(true);
		
		bThread = new BatteryThread(6000, battery);
		fThread = new FromThread(from);
		tThread = new TimeThread(time, fThread);
		
		tThread.start();
		bThread.start();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int y = (int)Math.round(size.getHeight() - 100);
		evanescent.setLocation(100, y);
		evanescent.pack();
		setTransparent(evanescent);
	}
	
	private static void setTransparent(Component w) {
	    WinDef.HWND hwnd = getHWnd(w);
	    int wl = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
	    wl = wl | WinUser.WS_EX_LAYERED | WinUser.WS_EX_TRANSPARENT;
	    User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, wl);
	}
	
	/**
	 * Get the window handle from the OS
	 */
	private static HWND getHWnd(Component w) {
	    HWND hwnd = new HWND();
	    hwnd.setPointer(Native.getComponentPointer(w));
	    return hwnd;
	}
	
	public static void main(String args[]){
		try{
			new Main();
		}catch(Exception e){
			System.out.println("in-Game cloqk found an error!");
			e.printStackTrace();
		}
	}

}

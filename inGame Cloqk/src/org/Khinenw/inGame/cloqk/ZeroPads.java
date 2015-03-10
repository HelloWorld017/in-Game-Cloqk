package org.Khinenw.inGame.cloqk;

public class ZeroPads {
	public static String pad(int digit, int target){
		String s = String.valueOf(target);
		while(s.length() < digit){
			s = "0" + s;
		}
		return s;
	}
}

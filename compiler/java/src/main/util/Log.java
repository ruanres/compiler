package util;

public class Log {


	private static boolean activate_log = false;
	
	public static void printLog(String value) {
		if (activate_log) {
			System.out.println(value);
		};
	}
}

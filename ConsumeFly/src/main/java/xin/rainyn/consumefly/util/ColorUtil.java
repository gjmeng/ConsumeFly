package xin.rainyn.consumefly.util;

public class ColorUtil {
	public static String RED_4 = "§4";
	public static String RED_DAN_C = "§c";
	public static String ZHONG_6 = "§6";
	public static String YELLOW_E = "§e";
	public static String GREEN_2 = "§2";
	public static String GREEN_DAN_A = "§a";
	public static String QING_B = "§b";
	public static String QING_DAN_3 = "§3";
	public static String LAN_1 = "§1";
	public static String LAN_DAN_9 = "§9";
	public static String PINK_D = "§d";
	public static String PINK_DAN_5 = "§5";
	public static String WHITE_F = "§f";
	public static String GRAY_DAN_7 = "§7";
	public static String GRAY_8 = "§8";
	public static String BLACK_0 = "§0";
	public static String BOLD_L = "§l";
	public static String MAGIC_K = "§k";
	public static String UNDERLINE_N = "§n";
	public static String STRIKE_M = "§m";
	public static String ITALLIC = "§o";
	public static String RESET = "§r";
	
	public static String appendString(String... args) {
		StringBuffer sb = new StringBuffer();
		for(String str : args) {
			sb.append(str);
		}
		return sb.toString();
	}
}

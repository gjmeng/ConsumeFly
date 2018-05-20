package xin.rainyn.consumefly.logger;

import org.bukkit.Bukkit;

import xin.rainyn.consumefly.ConsumeFly;
import xin.rainyn.consumefly.util.ColorUtil;
import xin.rainyn.consumefly.util.ConfigUtil;

public class Logger {
	private static java.util.logging.Logger logger;
	public static String PLUGIN_NAME="ConsumeFly";
	public static ConsumeFly CONSUMEFLY = null;
	public static Integer TICK = 20;
	public static void setLogger(java.util.logging.Logger logger) {
		Logger.logger = logger;
	}
	
	public static void info(String... args) {
		for(String str : args ) {
			Logger.logger.info(str);
		}
	}
	
	public static void debug(String... args) {
		if(ConfigUtil.getConfig().isDebug()) {
			for(String str : args ) {
				Logger.logger.info(str);
			}
		}
	}

	public static void consoleError(String string) {
		Bukkit.getConsoleSender().sendMessage(addPrefix(ColorUtil.RED_4 + string));
	}
	
	public static void consoleDebug(String... args) {
		if(ConfigUtil.getConfig().isDebug()) {
			for(String str : args ) {
				Bukkit.getConsoleSender().sendMessage(addPrefix(str));
			}
		}
	}
	
	/**
	 * 给字符串加上插件前缀
	 * @param msg
	 * @return
	 */
	public static String addPrefix(String msg) {
		return ColorUtil.appendString(ColorUtil.GREEN_2,"[",ColorUtil.YELLOW_E,PLUGIN_NAME,ColorUtil.GREEN_2,"]",msg);
	}
}

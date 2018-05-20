package xin.rainyn.consumefly.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xin.rainyn.consumefly.consume.Consume;
import xin.rainyn.consumefly.consume.impl.LevelConsumeImpl;
import xin.rainyn.consumefly.consume.impl.VaultConsumeImpl;
import xin.rainyn.consumefly.logger.Logger;
import xin.rainyn.consumefly.util.ColorUtil;
import xin.rainyn.consumefly.util.ConfigUtil;

public class ConsumflyCommandExecutor implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("consumefly")) {
			return false;
		}
		//判断是否是玩家，不是玩家提示信息
		if(!(sender instanceof Player)) {
			sender.sendMessage(Logger.addPrefix("请在游戏内使用此命令，谢谢！！！"));
			return true;
		}
		//强转为玩家类
		Player player = (Player)sender;
		
		//没有参数，显示帮助信息
		if(args.length == 0) {
			sendHelp(player);
			return true;
		}
		//帮助信息显示
		if(args.length >= 1 && ("help".equals(args[0]) || "?".equals("args[0]"))) {
			sendHelp(player);
			return true;
		}
		//根据第一个参数设置消费的类型
		if(args.length >= 1 && ("info".equals(args[0]))) {
			info(player);
			return true;
		}
		Consume consume = null;
		if(args.length >= 1 && ("level".equals(args[0]))) {
			consume = new LevelConsumeImpl();
		}
		if(args.length >= 1 && ("vault".equals(args[0]))) {
			consume = new VaultConsumeImpl();
		}
		if(consume != null) {
			consume.fly(player, args);
		}else {
			player.sendMessage(ColorUtil.RED_4 + "请输入正确的参数！！！");
		}
		return true;
	}
	
	
	private void info(Player player) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("消费经验飞行：", ConfigUtil.getConfig().isLevelEnable()?"是":"否");
		map.put("1级经验可飞行时间(秒)：", ConfigUtil.getConfig().getLevelTime()+"");
		map.put("消费金币飞行：", ConfigUtil.getConfig().isVaultEnable()?"是":"否");
		map.put("1金币可飞行时间(秒)：", ConfigUtil.getConfig().getVaultTime()+"");
		sendMap(player,map);
	}
	/**
	 * 给玩家发送帮助信息
	 * @param player
	 */
	private void sendHelp(Player player) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("/costfly info", "查询能够使用的消费方式");
		map.put("/costfly level num <num>", "使用等级飞行");
		map.put("/costfly level time <num>", "使用多少等级飞行多少秒");
		map.put("/costfly vault num <num>", "使用多少金币飞行");
		map.put("/costfly vault time <num>", "使用金币飞行多少秒");
		sendMap(player,map);
	}
	
	private void sendMap(Player player, Map<String, String> map) {
		player.sendMessage(ColorUtil.appendString(ColorUtil.LAN_1, "======================== ",ColorUtil.QING_DAN_3,"消费飞行",ColorUtil.LAN_1, " ========================"));
		for( Entry<String, String> entry : map.entrySet()) {
			player.sendMessage(ColorUtil.appendString(ColorUtil.PINK_DAN_5,entry.getKey(),"  ",ColorUtil.GREEN_2,entry.getValue()));
		}
		player.sendMessage(ColorUtil.appendString(ColorUtil.LAN_1, "==========================================================="));
	}

}

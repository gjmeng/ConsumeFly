package xin.rainyn.consumefly.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xin.rainyn.consumefly.consume.Consume;
import xin.rainyn.consumefly.consume.impl.LevelConsumeImpl;
import xin.rainyn.consumefly.consume.impl.VaultConsumeImpl;
import xin.rainyn.consumefly.logger.Logger;

public class ConsumeflyFlyCommandExecutor implements CommandExecutor{
	private Consume consume;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Logger.addPrefix("请在游戏内使用此命令，谢谢！！！"));
			return true;
		}
		Player player = (Player)sender;
		if(args.length >= 1 && ("help".equals(args[0]) || "?".equals("args[0]"))) {
			sendHelp(player);
			return true;
		}
		if(args.length >= 1 && ("level".equals(args[0]))) {
			consume = new LevelConsumeImpl();
		}
		if(args.length >= 1 && ("vault".equals(args[0]))) {
			consume = new VaultConsumeImpl();
		}
		if(consume != null) {
			consume.fly(player, args);
		}
		return true;
	}
	/**
	 * 给玩家发送帮助信息
	 * @param player
	 */
	private void sendHelp(Player player) {
		List<String> list = Arrays.asList(
				"&1========= &3消费飞行 &1 ============"
				,"&a/costfly info &b查询能够使用的消费方式"
				,"&a/costfly level num <num> &b使用等级飞行"
				,"&a/costfly level time <num> &b使用多少等级飞行多少秒"
				,"&a/costfly vault num <num> &b使用多少金币飞行"
				,"&a/costfly vault time <num> &b使用金币飞行多少秒"
				,"&1================================="
				);
		for(String str : list) {
			player.sendMessage(str);
		}
	}

}

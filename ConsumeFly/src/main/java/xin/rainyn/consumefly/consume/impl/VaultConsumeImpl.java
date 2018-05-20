package xin.rainyn.consumefly.consume.impl;

import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
import xin.rainyn.consumefly.Runnable.FlyRunnable;
import xin.rainyn.consumefly.consume.Consume;
import xin.rainyn.consumefly.logger.Logger;
import xin.rainyn.consumefly.util.ColorUtil;
import xin.rainyn.consumefly.util.ConfigUtil;
import xin.rainyn.consumefly.util.EconomyUtil;
import xin.rainyn.consumefly.util.StringUtil;

public class VaultConsumeImpl implements Consume{

	public void fly(Player player, int num) {
		//判断是否已经开启等级飞行
		if(ConfigUtil.getConfig().isLevelEnable()) {
			//判断金钱是否足够
			if(EconomyUtil.economy.has(player, num)) {
				//计算飞行多长时间
				Long time = num * ConfigUtil.getConfig().getVaultTime();
				//扣除金币
				EconomyResponse response = EconomyUtil.economy.withdrawPlayer(player, num);
				if(response.type == ResponseType.SUCCESS) {
					//启动线程计时，到时停止飞行
					FlyRunnable runnable = new FlyRunnable(time, player);
					runnable.runTask(Logger.CONSUMEFLY);
					player.sendMessage(Logger.addPrefix("您使用"+num+"金币来飞行"+time+"秒！！！"));
				}else {
					player.sendMessage(Logger.addPrefix(ColorUtil.RED_4+"金钱扣除失败："+response.errorMessage));
				}
			}else {
				player.sendMessage(Logger.addPrefix("您的金币不足"+num+"，不能启动飞行！！！"));
			}
		}else {
			player.sendMessage(Logger.addPrefix(ConfigUtil.getConfig().getLevelMessage()));
		}
	}

	public void flyTime(Player player, Long time) {
		//判断是否已经开启等级飞行
		if(ConfigUtil.getConfig().isLevelEnable()) {
			//计算需要多少金币
			int num = (int) (time / ConfigUtil.getConfig().getVaultTime());
			//如果不能整除，要多扣1金币
			if(time % ConfigUtil.getConfig().getLevelTime() > 0) {
				num++;
			}
			//判断金币是否足够
			if(EconomyUtil.economy.has(player, num)) {
				//扣除金币
				EconomyResponse response = EconomyUtil.economy.withdrawPlayer(player, num);
				if(response.type == ResponseType.SUCCESS) {
					//启动线程计时，到时停止飞行
					FlyRunnable runnable = new FlyRunnable(time, player);
					runnable.runTask(Logger.CONSUMEFLY);
					player.sendMessage(Logger.addPrefix("您使用"+num+"金币来飞行"+time+"秒！！！"));
				}else {
					player.sendMessage(Logger.addPrefix(ColorUtil.RED_4+"金钱扣除失败："+response.errorMessage));
				}
			}else {
				player.sendMessage(Logger.addPrefix("您当前等级不足，请先升级！！！"));
			}
		}else {
			player.sendMessage(Logger.addPrefix(ConfigUtil.getConfig().getLevelMessage()));
		}
	}

	public void fly(Player player, String... args) {
		//先判断是否已经可以飞行
		if(player.getAllowFlight()) {
			player.sendMessage(Logger.addPrefix("您已拥有飞行权限，无需再消费飞行！！！"));
		}else {
			if(args.length == 3) {
				if("num".equals(args[1])) {
					if(StringUtil.isInteger(args[2])) {
						this.fly(player, Integer.parseInt(args[2]));
					}else {
						player.sendMessage(Logger.addPrefix("请输入正确的参数！！！"));
					}
				}
				if("time".equals(args[1])) {
					if(StringUtil.isInteger(args[2])) {
						this.flyTime(player, Long.parseLong(args[2]));
					}else {
						player.sendMessage(Logger.addPrefix("请输入正确的参数！！！"));
					}
				}
			}else {
				player.sendMessage(Logger.addPrefix("请输入正确的参数！！！"));
			}
		}
	}


}

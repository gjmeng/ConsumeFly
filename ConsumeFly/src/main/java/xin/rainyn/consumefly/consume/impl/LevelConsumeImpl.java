package xin.rainyn.consumefly.consume.impl;

import org.bukkit.entity.Player;

import xin.rainyn.consumefly.Runnable.FlyRunnable;
import xin.rainyn.consumefly.consume.Consume;
import xin.rainyn.consumefly.logger.Logger;
import xin.rainyn.consumefly.util.ConfigUtil;
import xin.rainyn.consumefly.util.StringUtil;

public class LevelConsumeImpl implements Consume{

	public void fly(Player player, int num) {
		//判断是否已经开启等级飞行
		if(ConfigUtil.getConfig().isLevelEnable()) {
			//判断等级是否足够
			if(player.getLevel() >= num) {
				//计算飞行多长时间
				Long time = num * ConfigUtil.getConfig().getLevelTime();
				//扣除等级
				player.setLevel(player.getLevel() - num);
				//启动线程计时，到时停止飞行
				FlyRunnable runnable = new FlyRunnable(time, player);
				runnable.runTask(Logger.CONSUMEFLY);
				player.sendMessage(Logger.addPrefix("您使用"+num+"级来飞行"+time+"秒！！！"));
			}else {
				player.sendMessage(Logger.addPrefix("您当前等级不足，请先升级！！！"));
			}
		}else {
			player.sendMessage(Logger.addPrefix(ConfigUtil.getConfig().getLevelMessage()));
		}
	}

	public void flyTime(Player player, Long time) {
		//判断是否已经开启等级飞行
		if(ConfigUtil.getConfig().isLevelEnable()) {
			//计算需要多少等级
			int num = (int) (time / ConfigUtil.getConfig().getLevelTime());
			//如果不能整除，要多扣一级
			if(time % ConfigUtil.getConfig().getLevelTime() > 0) {
				num++;
			}
			//判断等级是否足够
			if(player.getLevel() >= num) {
				//扣除等级
				player.setLevel(player.getLevel() - num);
				//启动线程计时，到时停止飞行
				FlyRunnable runnable = new FlyRunnable(time, player);
				runnable.runTask(Logger.CONSUMEFLY);
				player.sendMessage(Logger.addPrefix("您使用"+num+"级来飞行"+time+"秒！！！"));
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

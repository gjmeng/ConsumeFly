package xin.rainyn.consumefly.Runnable;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import xin.rainyn.consumefly.logger.Logger;
import xin.rainyn.consumefly.util.ColorUtil;

public class FlyRunnable extends BukkitRunnable{
	private Long time = -1L;//秒
	private Player player = null;
	public FlyRunnable(Long time, Player player) {
		this.time = time;
		this.player = player;
	}
	public void run() {
		//计算tick
		Long timeTick = time*Logger.TICK;
		//设置飞行
		new BukkitRunnable() {
			
			public void run() {
				FlyRunnable.this.player.setAllowFlight(true);
				FlyRunnable.this.player.setFlying(true);
				FlyRunnable.this.player.sendMessage(Logger.addPrefix(ColorUtil.RED_4+"已开启飞行，您可以飞行"+time+"秒"));
			}
		}.runTask(Logger.CONSUMEFLY);
		//倒计时提醒设置
		leftTimeTip(60);
		leftTimeTip(30);
		leftTimeTip(10);
		leftTimeTip(5);
		leftTimeTip(4);
		leftTimeTip(3);
		leftTimeTip(2);
		leftTimeTip(1);
		//设置停止飞行
		new BukkitRunnable() {
			
			public void run() {
				FlyRunnable.this.player.setFlying(false);
				FlyRunnable.this.player.setAllowFlight(false);
				FlyRunnable.this.player.sendMessage(Logger.addPrefix(ColorUtil.RED_4+"飞行时间到，已关闭飞行。。。"));
			}
		}.runTaskLater(Logger.CONSUMEFLY, timeTick);
	}
	
	
	private void leftTimeTip(final int leftTime) {
		if(time >= leftTime) {
			new BukkitRunnable() {
				
				public void run() {
					FlyRunnable.this.player.sendMessage(Logger.addPrefix(ColorUtil.RED_4+"飞行时间剩余"+leftTime+"秒。。。"));
				}
			}.runTaskLater(Logger.CONSUMEFLY, (time-leftTime)*Logger.TICK);
		}
	}
}

package xin.rainyn.consumefly.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import xin.rainyn.consumefly.logger.Logger;
import xin.rainyn.consumefly.util.ConfigUtil;
/**
 * 监听玩家事件
 * @author Rainyner
 *
 */
public class PlayerListener implements Listener {
	/**
	 * 监听玩家加入事件
	 * @param e
	 */
	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerJoinGame(PlayerJoinEvent e) {
		 // 判断是否启用玩家加入提示信息功能
		if(ConfigUtil.getConfig().isPlayerJoinMessageEnable()) {
			//循环发出玩家的提示消息
			for(String str : ConfigUtil.getConfig().getPlayerJoinMessage()) {
				e.getPlayer().sendMessage(Logger.addPrefix(str));
			}
		}
	}
}

package xin.rainyn.consumefly.consume;

import org.bukkit.entity.Player;

/**
 * 所有的消费
 * @author Rainyner
 *
 */
public interface Consume {
	/**
	 * 飞行统一对外函数
	 * @param player
	 * @param args
	 */
	public void fly(Player player, String... args);
}

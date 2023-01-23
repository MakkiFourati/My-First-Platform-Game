/*
 *	Author:      Makki Fourati
 *	Date:        3 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;


public class Overlay extends Actor {

	private Player player;
	final static double SIZE = 0.15;
	final static int PRIORITY = 42;
	
	public Overlay(Player player) {
		this.player = player;
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		
		double health = 5.0 * player.getHealth() / player.getHealthMax();
		for (int i = 1; i <= 5; ++i) { 
			String name;
			Vector center = new Vector(getBox().getCenter().getX()+(i-3)/7.0, getBox().getCenter().getY());
			Box box = new Box(center, SIZE, SIZE);
			
			if (health >= i)
				name = "heart.full";
			else if (health >= i - 0.5) 
				name = "heart.half";
			else
				name = "heart.empty";
			
			output.drawSprite(getSprite(name), box);
		}
	}
	
	@Override
	public void postUpdate(Input input) {
		if(player.getHealth() <= 0.0 || player.getWorld() == null) {
			this.unregister();
		}
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public Box getBox() {
		Vector center = new Vector(player.getPosition().getX(), player.getPosition().getY() + Player.SIZE);
		return new Box(center, SIZE*5.0, SIZE);
	}
}

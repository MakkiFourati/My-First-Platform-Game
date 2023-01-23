/*
 *	Author:      Makki Fourati
 *	Date:        8 déc. 2016
 */
package platform.game;

import platform.game.level.Level;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Exit extends Actor implements Signal{
	private Vector position;
	private Level level;
	private Signal signal;
	private boolean cleared;
	final static double SIZE = 1.0;
	final static int PRIORITY = 2;
	
	public Exit(Vector position, Level level, Signal signal) {
		this.position = position;
		this.level = level;
		this.signal = signal;
		cleared = false;
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		if(signal.isActive()) {
			output.drawSprite(getSprite("door.open"), getBox());
		}else {
			output.drawSprite(getSprite("door.closed"), getBox());
		}
	}
	
	@Override
	public void postUpdate(Input input) {
		super.postUpdate(input);
		if(cleared) {
			this.getWorld().nextLevel();
			this.getWorld().setNextLevel(level);
			this.unregister();
		}
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public Box getBox() {
		return new Box(position, SIZE, SIZE); 
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) { 
		if(type == Damage.ACTIVATION && amount < 0.0 && signal.isActive()) {
			cleared = true;
			return true;
		}else {
			return super.hurt(instigator, type, amount, location);
		}
	}
	
	@Override
	public boolean isActive() {
		return signal.isActive();
	}

}

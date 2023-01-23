/*
 *	Author:      Makki Fourati
 *	Date:        3 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Vector;

public class Limits extends Actor{
	
	private Box box;
	final static int PRIORITY = 1000;
	
	public Limits(Box box) {
		this.box = box;
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if(!getBox().isColliding(other.getBox()))
			other.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);
	}
	
	@Override
	public Box getBox() { 
		return box;
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
}

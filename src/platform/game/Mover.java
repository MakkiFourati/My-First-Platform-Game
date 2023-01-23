/*
 *	Author:      Makki Fourati
 *	Date:        8 déc. 2016
 */

package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Mover extends Block implements Signal {
	private Signal signal;
	private Vector on;
	private Vector off;
	final static double SPEED = 1.0;
	private double current;
	final static int PRIORITY = 1;
	
	public Mover(Box box, Sprite sprite, Signal signal, Vector on) {
		super(box, sprite);
		off = box.getCenter();
		this.signal = signal;
		this.on = on;
		current = 0.0;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		if (signal.isActive()) {
			current += input.getDeltaTime(); 
			if (current > 1.0)
				current = 1.0; 
		} else {
			current -= input.getDeltaTime(); 
			if (current < 0.0)
				current = 0.0; 
		}
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public boolean isSolid() { 
		return true;
	}

	@Override
	public Box getBox() {
		double height = super.getBox().getHeight();
		double width = super.getBox().getWidth();
		Vector deltaX = on.sub(off);
		Vector center = deltaX.mul(current).add(off);
		return new Box(center, width, height);
	}
	
	@Override
	public boolean isActive() {
		return signal.isActive();
	}
}

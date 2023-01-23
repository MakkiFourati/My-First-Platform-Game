/*
 *	Author:      Makki Fourati
 *	Date:        8 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Lever extends Actor implements Signal {
	
	private Box box;
	private boolean value;
	private double duration;
	private double time;

	final static double SIZE = 0.8;
	final static int PRIORITY = 2;
	
	public Lever(Vector center, double duration) {
		this.box = new Box(center, SIZE, SIZE);
		this.duration = duration;
		value = false;
		time = 0.0;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		time -= input.getDeltaTime();
		if(time <= 0.0) {
			value = false;
		}
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		if(value) {
			output.drawSprite(getSprite("lever.right"), getBox());
		}else {
			output.drawSprite(getSprite("lever.left"), getBox());
		}
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public Box getBox() {
		return box; 
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) { 
		if(type == Damage.ACTIVATION && amount > 0.0) {
			value = !value;
			if(value) {
				time = duration;
			}
			return true;
		}else {
			return super.hurt(instigator, type, amount, location);
		}
	}
	
	@Override
	public boolean isActive() {
		return value;
	}
}

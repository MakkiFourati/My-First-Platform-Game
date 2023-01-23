/*
 *	Author:      Makki Fourati
 *	Date:        7 déc. 2016
 */
package platform.game;


import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Door extends Block implements Signal{

	private Signal signal;
	private Signal negSignal; // negation of the original signal
	final static double SIZE = 1.0;
	final static int PRIORITY = 9;
	
	public Door(Vector position, Sprite sprite, Signal signal) {
		super(new Box(position, SIZE, SIZE), sprite);
		this.signal = signal;
		negSignal = new Not(signal);
	}
	
	@Override
	public void draw(Input input, Output output) {
		if(negSignal.isActive()) {
			super.draw(input, output);
		}
	}
	
	@Override
	public void postUpdate(Input input) {
		super.postUpdate(input);
		if(signal.isActive()) {
			getWorld().unregister(this);
		}
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public boolean isSolid() { 
		return negSignal.isActive();
	}
   
	@Override
	public Box getBox() {
		if(signal.isActive()) {
			return null;
		} else {
			return super.getBox();
		}	
	}
	
	@Override
	public boolean isActive() {
		return signal.isActive();
	}
	
}

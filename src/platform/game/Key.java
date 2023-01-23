/*
 *	Author:      Makki Fourati
 *	Date:        6 déc. 2016
 */
package platform.game;


import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Key extends Actor implements Signal{

	private Vector position;
	private boolean taken;
	private Color color;
	final static double SIZE = 0.4;
	final static int PRIORITY = 2;
	
	public Key(Vector position, Color color) {
		this.position = position;
		this.color = color;
		taken = false;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		if(taken) {
			getWorld().unregister(this);
		}
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		if(!taken) {
			switch(color) {
			case BLUE : 
				output.drawSprite(getSprite("key.blue"), getBox());
				break;
			case GREEN :
				output.drawSprite(getSprite("key.green"), getBox());
				break;
			case YELLOW :
				output.drawSprite(getSprite("key.yellow"), getBox());
				break;
			case RED :
				output.drawSprite(getSprite("key.red"), getBox());
				break;
			}
		}
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public Box getBox() {
		return new Box(position, SIZE*1.5, SIZE); 
	}
	
	// the key is considered to be taken if it receive an activation damage
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) { 
		if(type == Damage.ACTIVATION && amount < 0.0) {
			taken = true;
			return true;
		}else {
			return super.hurt(instigator, type, amount, location);
		}
	}
	
	@Override
	public boolean isActive() {
		return taken;
	}
}

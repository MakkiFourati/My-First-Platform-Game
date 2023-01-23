/*
 *	Author:      Makki Fourati
 *	Date:        4 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;

public class Spike extends Actor{

	private Box box;
	private Sprite sprite; // I added a sprite to draw vertical spikes
	final static int PRIORITY = 70;
	
	public Spike(Box box, Sprite sprite) {
		this.box = box;
		this.sprite = sprite;
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		if(getBox().isColliding(other.getBox()) && other.getVelocity().getY() < -1.0 ) {
			other.hurt(this, Damage.PHYSICAL, 10.0, getPosition());
		}
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		output.drawSprite(sprite, getBox());
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

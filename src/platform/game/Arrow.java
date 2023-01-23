/*
 *	Author:      Makki Fourati
 *	Date:        9 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Arrow extends Actor {
	private Vector position;
	private Vector velocity;
	final static double SIZE = 0.2;
	final static int PRIORITY = 555;
	private Actor owner;
	
	public Arrow(Vector position, Vector velocity) {
		if(position == null || velocity == null) {
			throw new NullPointerException();
		}
		this.position = position;
		this.velocity = velocity;
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (other.getBox().isColliding(getBox()) && other != owner) {
			if (other.hurt(this, Damage.PHYSICAL, 20.0, getPosition()) || other.isSolid()) {
				getWorld().unregister(this);
			}
		}
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime()*100;
		position = velocity.mul(delta).add(position);
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		if(velocity.getX() > 0.0) {
			output.drawSprite(getSprite("arrow.right"), getBox());
		}else {
			output.drawSprite(getSprite("arrow.left"), getBox());
		}
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public Box getBox() {
		return new Box(position, SIZE*4, SIZE); 
	}
	
	@Override
	public Vector getVelocity() {
		return velocity;
	}

}

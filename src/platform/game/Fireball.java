/*
 *	Author:      Makki Fourati
 *	Date:        30 nov. 2016
 */
package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

public class Fireball extends Actor{
	
	private Vector position;
	private Vector velocity;
	final static double SIZE = 0.4;
	final static int PRIORITY = 666;
	private Actor owner;
	
	public Fireball(Vector position, Vector velocity, Actor owner) {
		if(position == null || velocity == null) {
			throw new NullPointerException();
		}
		this.position = position;
		this.velocity = velocity;
		this.owner = owner;
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (other.isSolid()) {
			Vector delta = other.getBox().getCollision(position); 
			if (delta != null) {
				position = position.add(delta);
				velocity = velocity.mirrored(delta); 
			}
		}
		
		if (other.getBox().isColliding(getBox()) && other != owner) { //here I just compare the adresses of other and owner without  
			if (other.hurt(this, Damage.FIRE, 1.0, getPosition())) { //using equals											
				getWorld().unregister(this);						
			}
		}
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		velocity = velocity.add(World.getGravity().mul(delta));
		position = position.add(velocity.mul(delta));
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		output.drawSprite(getSprite("fireball"), getBox(), 25*input.getTime());
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
	public Vector getVelocity() {
		return velocity;
	}
}


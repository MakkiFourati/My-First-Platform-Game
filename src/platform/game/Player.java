/*
 *	Author:      Makki Fourati
 *	Date:        30 nov. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;


import java.awt.event.KeyEvent;

public class Player extends Actor {

	private Vector position;
	private Vector velocity;
	private boolean colliding;
	private double health;
	private double healthMax;
	final static double SIZE = 1;
	final static int PRIORITY = 42;
	
	public Player(Vector position, Vector velocity, double healthMax) {
		if(position == null || velocity == null) {
			throw new NullPointerException();
		}
		this.position = position;
		this.velocity = velocity;
		this.healthMax = healthMax;
		health = healthMax;
		colliding = false;
	}
	
	@Override
	public void preUpdate(Input input) {
		colliding = false;
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other); 
		if (other.isSolid()) {
			Vector delta = other.getBox().getCollision(getBox()); 
			if (delta != null) {
				position = position.add(delta); 
				if (delta.getX() != 0.0)
					velocity = new Vector(0.0, velocity.getY()); 
				if (delta.getY() != 0.0)
					velocity = new Vector(velocity.getX(), 0.0);
				colliding = true;
			}
		}
		
		// the player can activate some other actors like keys or exit doors by getting in contact with them
		if (!other.isSolid() && getBox().isColliding(other.getBox())) {
			other.hurt(this, Damage.ACTIVATION, -1.0, getPosition());
		}
		
		// if the player collides with an activation zone of a trap it will enable it
		//by causing activation damage to the trap
		if(other.getZone() != null && getBox().isColliding(other.getZone())) {
			other.hurt(this, Damage.ACTIVATION, -4.0, getPosition());
		}
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		double maxSpeed = 4.7;
		
		if (colliding) {
			double scale = Math.pow(0.001, input.getDeltaTime());
			velocity = velocity.mul(scale);
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()) {
			if (velocity.getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime(); 
				double speed = velocity.getX() + increase;
				if (speed > maxSpeed) 
					speed = maxSpeed; 
				velocity = new Vector(speed, velocity.getY()); 
			}
		}
		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()) {
			if (velocity.getX() > -maxSpeed) {
				double increase = 60.0 * input.getDeltaTime(); 
				double speed = velocity.getX() - increase;
				if (speed < -maxSpeed) 
					speed = -maxSpeed; 
				velocity = new Vector(speed, velocity.getY()); 
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_UP).isPressed() && colliding) {
			velocity = new Vector(velocity.getX(), 8.0);
		}
		
		//throwing fireballs
		if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()) {
			Vector v = velocity.add(velocity.resized(2.0));
			Fireball fireball = new Fireball(position,v,this);
			fireball.register(getWorld());
			this.getWorld().register(fireball);
		}
		
		// blowing
		if (input.getKeyboardButton(KeyEvent.VK_B).isPressed()) {
			getWorld().hurt(getBox(), this, Damage.AIR, 1.0, getPosition());
		}
		
		//activating some stuff like levers
		if (input.getKeyboardButton(KeyEvent.VK_E).isPressed()) {
			getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1.0, getPosition());
		}
		
		double delta = input.getDeltaTime();
		velocity = velocity.add(World.getGravity().mul(delta));
		position = position.add(velocity.mul(delta));
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		output.drawSprite(getSprite("blocker.happy"), getBox());
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public void postUpdate(Input input) {
		super.postUpdate(input);
		final double RADIUS = 8.0;
		this.getWorld().setView(position, RADIUS);
		this.death();
	}
	
	@Override
	public Vector getPosition() { 	
		return position;
	}
	
	@Override
	public Box getBox() {
		return new Box(position, SIZE, SIZE); 
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) { 
		switch (type) {
			case FIRE:
				health -= amount;
				return true;
				
			case AIR :
				velocity = getPosition().sub(location).resized(amount); 
				return true;
				
			case VOID :
				health -= amount;
				return true;
				
			case HEAL :
				if((health + amount) >= healthMax){
					health = healthMax;
				}else {
					health += amount;
				}
				return true;
				
			case PHYSICAL :
				health -= amount;
				return true;
				
				
			default :
				return super.hurt(instigator, type, amount, location);
		} 
	}

	//unregister the player and replay the current level if health is under 0
	public void death() {
		if(health <= 0.0) {
			this.getWorld().nextLevel();
			this.unregister();
		}
	}
	
	@Override
	public Vector getVelocity() {
		return velocity;
	}
	
	public double getHealth() {
		return health;
	}
	
	public double getHealthMax() {
		return healthMax;
	}
}

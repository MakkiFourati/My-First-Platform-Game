/*
 *	Author:      Makki Fourati
 *	Date:        9 déc. 2016
 */
package platform.game;


import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;


public class ArrowThrower extends Block{
	private Box activationZone;
	private double cooldown;
	final static double SIZE = 0.5;
	final static int PRIORITY = 5;
	
	public ArrowThrower(Vector position, Sprite sprite, double zoneSize) {
		super(new Box(position,SIZE, SIZE), sprite);
		this.activationZone = new Box(position, zoneSize, SIZE/2);
		cooldown = 0.0;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		cooldown -= input.getDeltaTime();
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
	public Box getZone() {
		return activationZone;
	}
	
	//this actor throws arrows if it receive activation damage from an other actor
	//which collide with its activation zone
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) { 
		if(type == Damage.ACTIVATION && amount < 0.0 && cooldown<0) {
			Vector direction = new Vector(location.getX(),getBox().getCenter().getY());// the direction of he arrow depends on 
			Vector v = direction.sub(getBox().getCenter());							// from where the instigator activated the trap
			v = v.div(v.getLength()*15); //velocity of the arrow which is constant and always horyzantal
			Arrow arrow = new Arrow(getBox().getCenter().add(v.mul(10)),v );
			arrow.register(getWorld());
			this.getWorld().register(arrow);
			cooldown = 6;
			return true;
		}else {
			return super.hurt(instigator, type, amount, location);
		}
	}
}

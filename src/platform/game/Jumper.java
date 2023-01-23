/*
 *	Author:      Makki Fourati
 *	Date:        2 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Jumper extends Actor {
	
	private Vector position;
	private double cooldown;
	final static int PRIORITY = 60;
	
	public Jumper(Vector position) {
		this.position = position;
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (cooldown <= 0 && getBox().isColliding(other.getBox())) {
			Vector below = new Vector(position.getX(), position.getY() - 1.0);
			if (other.hurt(this, Damage.AIR, 10.0, below))
				cooldown = 0.5;
		} 
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		cooldown -= input.getDeltaTime(); 
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		if(cooldown>0)
			output.drawSprite(getSprite("jumper.extended"), getBox());
		else
			output.drawSprite(getSprite("jumper.normal"), getBox());
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public Box getBox() {
		Vector min = new Vector(position.getX()-0.5, position.getY());
		Vector max = new Vector(position.getX()+0.5, position.getY()+1);
		return new Box(min, max); 
	}
}

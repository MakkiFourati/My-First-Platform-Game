/*
 *	Author:      Makki Fourati
 *	Date:        12 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class BoxParticle extends Actor{
	private Vector position;
	private Vector velocity;
	private double size;
	final static int PRIORITY = 1300;
	
	public BoxParticle(Vector position, Vector velocity, double size) {
		if(position == null || velocity == null) {
			throw new NullPointerException();
		}
		this.position = position;
		this.velocity = velocity;
		this.size=size;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		velocity = velocity.add(World.getGravity().mul(delta));
		position = position.add(velocity.mul(delta));
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		output.drawSprite(getSprite("box.particle"), getBox(), 5*input.getTime());
	}
	
	@Override
	public Box getBox() {
		return new Box(position, size, size); 
	}
}

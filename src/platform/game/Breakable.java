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

public class Breakable extends Block{
	private boolean broken;
	
	public Breakable(Box box, Sprite sprite) {
		super(box, sprite);
		broken = false;
	}
	
	@Override
	public void draw(Input input, Output output) {
		if(!broken) {
			super.draw(input, output);
		}else {
			//divide the box into 9 pieces if it has been broken
			//and draw them instead of it
			for(int i=-1; i<2; ++i) {
				for(int j=-1; j<2; ++j) {
					Vector position = new Vector(getBox().getCenter().getX()+j*getBox().getWidth()/2, 
								getBox().getCenter().getY()+i*getBox().getWidth()/2);
					Vector velocity = new Vector(j, i);
					BoxParticle particle = new BoxParticle(position, velocity, getBox().getHeight());
					this.getWorld().register(particle);
				}	
			}
		}
	}
		
	@Override
	public void postUpdate(Input input) {
		super.postUpdate(input);
		if(broken) {
			getWorld().unregister(this);
		}
	}
	
	@Override
	public boolean isSolid() { 
		return true;
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch(type) {
			case FIRE :
				broken = true;
				return true;
			default :
				return super.hurt(instigator, type, amount, location);
		}
	}
}

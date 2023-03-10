package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;

/**
 * Base class of all simulated actors, attached to a world.
 */
public abstract class Actor  implements Comparable<Actor> {
 
	private World world ;
	
	@Override
	public int compareTo(Actor other) {
		if(this.getPriority() > other.getPriority()) {
			return -1;
		}else if(this.getPriority() == other.getPriority()) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public void preUpdate(Input input) {}
	
	public void interact(Actor other) {}
	
	public void update(Input input) {}
	
	public void draw(Input input, Output output) {}
	
	public void postUpdate(Input input) {}
	
	public abstract int getPriority();
	
	public boolean isSolid() { 
		return false;
	}
	
	public Box getBox() { 
		return null;
	}
	
	//return the box of the activation zone of a trap actor
	public Box getZone() {
		return null;
	}
	
	public Vector getPosition() { 
		Box box = getBox();
		if (box == null)
			return null;
		return box.getCenter();
	}
	
	//return the velocity of an actor
	public Vector getVelocity() {
		return Vector.ZERO;
	}
	
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		return false; 
	}
	
	public void register(World world) { 
		this.world = world;
	}
	
	public void unregister() { 
		world = null;
	}
	
	protected World getWorld() {
		return world;
	}
	
	protected Sprite getSprite(String name) {
		return world.getLoader().getSprite(name);
	}
}


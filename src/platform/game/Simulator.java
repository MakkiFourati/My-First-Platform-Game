package platform.game;


import java.util.ArrayList;
import java.util.List;

import platform.game.level.Level;
import platform.util.Box;
import platform.util.Input;
import platform.util.Loader;
import platform.util.Output;
import platform.util.Vector;
import platform.util.View;
import platform.util.SortedCollection;


/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World {

    private Loader loader;
    
    private Vector currentCenter; 
    private double currentRadius;
    
    private Vector expectedCenter;
    private double expectedRadius;
    
    private SortedCollection<Actor> actors;
    
    private List<Actor> registered;
    private List<Actor> unregistered;
    
    private Level next;
    
    private boolean transition;
  
    /**
     * Create a new simulator.
     * @param loader associated loader, not null
     * @param args level arguments, not null
     */

	/**
	* Create a new simulator.
	* @param loader associated loader, not null * @param args level arguments, not null
	*/
    public Simulator(Loader loader, String[] args) { 
		if (loader == null) {
			throw new NullPointerException(); 
		}
		this.loader = loader;
		currentCenter = Vector.ZERO;
		currentRadius = 10.0;
		
		expectedCenter = Vector.ZERO;
	    expectedRadius = 10.0;
	    
	    actors = new SortedCollection<Actor>();
	    
		registered = new ArrayList<Actor>();
		unregistered = new ArrayList<Actor>();
		
		next = Level.createDefaultLevel();
		
		transition = false;
		
		actors.add(next);
		next.register(this);

    }
    @Override
	public void setView(Vector center, double radius) {
    	if (center == null)
    		throw new NullPointerException();
    	if (radius <= 0.0)
    		throw new IllegalArgumentException("radius must be positive"); 
    	expectedCenter = center; 
    	expectedRadius = radius;
	}
    
    @Override
    public void register(Actor actor) {
    	registered.add(actor);
    	actor.register(this);
    }
    
    @Override
    public void unregister(Actor actor) {
    	unregistered.add(actor); 
    }
    
  
    /**
     * Simulate a single step of the simulation.
     * @param input input object to use, not null
     * @param output output object to use, not null
     */
	public void update(Input input, Output output) {

		double factor = 0.1;
		
		currentCenter = currentCenter.mul(1.0 -factor).add(expectedCenter.mul(factor));
		currentRadius = currentRadius * (1.0 - factor) + expectedRadius * factor;
		
		View view = new View(input, output);
		view.setTarget(currentCenter, currentRadius);
		
		//pre update actors
		for (Actor a : actors) 
			a.preUpdate(view);
		
		//interaction between actors
		for (Actor actor : actors) 
			for (Actor other : actors)
				if (actor.getPriority() > other.getPriority()) 
					actor.interact(other);
		
		//update actors
		for (Actor a : actors) 
			a.update(view);
		
		
		// Draw everything
		for (Actor a : actors.descending())
			a.draw(view, view);
		
		//post update actors
		for (Actor a : actors) 
			a.postUpdate(view);
		
		// Add registered actors
		for (int i = 0; i < registered.size(); ++i) { 
			Actor actor = registered.get(i);
			if (!actors.contains(actor)) {
				actors.add(actor);
			}
		} 
		registered.clear();
		
		// Remove unregistered actors
		for (int i = 0; i < unregistered.size(); ++i) {
			Actor actor = unregistered.get(i); 
			actors.remove(actor);
		} 
		unregistered.clear();
		
		//moving to the next level
		if (transition) {
			if (next == null) {
				next = Level.createDefaultLevel();
				
			}
			Level level = next;
			transition = false;
			next = null;
			actors.clear();
			registered.clear(); 
			unregistered.clear();
			register(level);
		}
	}
	
	public void nextLevel() {
		transition = true;	
	}
	
	public void setNextLevel(Level level) {
		next = level;
	}
	
    @Override
    public Loader getLoader() {
        return loader;
    }
    
  
    @Override
    public int hurt(Box area, Actor instigator, Damage type, double amount, Vector location) { 
    	int victims = 0;
    	for (Actor actor : actors)
    		if (area.isColliding(actor.getBox()))
    			if (actor.hurt(instigator, type, amount, location))
    				++victims; 
    		return victims;
    }

    
}

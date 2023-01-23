package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor {
	private Box box;
	private Sprite sprite;
	final static int PRIORITY = 0;
	
	public Block(Box box, Sprite sprite) {
		this.box = box;
		this.sprite = sprite;
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		output.drawSprite(sprite, getBox());
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
	public Box getBox() {
		return box;
	}
}

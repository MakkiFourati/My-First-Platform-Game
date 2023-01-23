/*
 *	Author:      Makki Fourati
 *	Date:        4 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Torch extends Actor implements Signal {

		private Box box;
		private boolean lit;
		private double variation;
		final static double SIZE = 0.8;
		final static int PRIORITY = 32;
		
		public Torch(Vector center, boolean lit) {
			this.box = new Box(center, SIZE, SIZE);
			this.lit = lit;
			variation = 0.0;
		}
		
		@Override
		public void update(Input input) {
			super.update(input);
			variation -= input.getDeltaTime(); 
			if (variation < 0.0) {
				variation = 0.3;
			}
		}
		
		@Override
		public void draw(Input input, Output output) {
			super.draw(input, output);
			if(lit) {
				String name = "torch.lit.1"; 
				if (variation < 0.15) {
					name = "torch.lit.2";
				}
				output.drawSprite(getSprite(name), getBox());
			}	
			else {
				output.drawSprite(getSprite("torch"), getBox());
			}
		}
		
		@Override
		public int getPriority() {
			return PRIORITY;
		}
		
		@Override
		public Box getBox() {
			return box; 
		}
		
		@Override
		public boolean hurt(Actor instigator, Damage type, double amount, Vector location) { 
			switch (type) {
				case FIRE :
					lit = true;
					return true;
				case AIR :
					lit = false;
					return true;
				default : 
					return super.hurt(instigator, type, amount, location);
			}
		}
		
		@Override
		public boolean isActive() {
			return lit;
		}

}

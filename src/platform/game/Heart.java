/*
 *	Author:      Makki Fourati
 *	Date:        4 déc. 2016
 */
package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Heart  extends Actor{

		private Vector position;
		private double cooldown;
		private double countdown;
		final static double SIZE = 0.5;
		final static int PRIORITY = 100;
		
		public Heart(Vector position) {
			this.position = position;
			cooldown = 10.0;
			countdown = 0.0;
		}
		
		@Override
		public void interact(Actor other) {
			if (countdown <= 0 && getBox().isColliding(other.getBox())) {
				if (other.hurt(this, Damage.HEAL, 100.0, getPosition())) 
					countdown = cooldown;
			} 
		}
		
		@Override
		public void update(Input input) {
			super.update(input);
			countdown -= input.getDeltaTime();
		}
		
		@Override
		public void draw(Input input, Output output) {
			super.draw(input, output);
			if(countdown <= 0)
				output.drawSprite(getSprite("heart.full"), getBox());
		}
		
		@Override
		public int getPriority() {
			return PRIORITY;
		}
		
		@Override
		public Box getBox() {
			return new Box(position, SIZE, SIZE); 
		}
}

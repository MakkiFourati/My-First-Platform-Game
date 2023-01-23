/*
 *	Author:      Makki Fourati
 *	Date:        13 déc. 2016
 */
package platform.game.level;

import platform.game.And;
import platform.game.Block;
import platform.game.Breakable;
import platform.game.Color;
import platform.game.Door;
import platform.game.Exit;
import platform.game.Heart;
import platform.game.Jumper;
import platform.game.Key;
import platform.game.Lever;
import platform.game.Limits;
import platform.game.Mover;
import platform.game.Not;
import platform.game.Or;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.Spike;
import platform.game.Torch;
import platform.game.World;

import platform.util.Box;
import platform.util.Vector;

public class LevelTwo extends Level {
	@Override
    public void register(World world) {
       super.register(world);
       
    // Register a new instance, to restart level automatically
       world.setNextLevel(new LevelTwo());
       
       //create blocks
       world.register(new Block(new Box(new Vector(0.0, 0.0), 3.0, 1.0), world.getLoader().getSprite("stone.3")));
       
       world.register(new Block(new Box(new Vector(-7.0, -4.0), 4.0, 1.0), world.getLoader().getSprite("stone.broken.3")));
       world.register(new Block(new Box(new Vector(-7.0, -5.0), 3.0, 1.0), world.getLoader().getSprite("stone.broken.3")));
       world.register(new Block(new Box(new Vector(-7.0, -6.0), 1.0, 1.0), world.getLoader().getSprite("stone.broken.1")));
       world.register(new Block(new Box(new Vector(7.0, -4.25), 4.0, 1.5), world.getLoader().getSprite("stone.broken.3")));
       world.register(new Block(new Box(new Vector(-7.0, 6.0), 4.0, 1.0), world.getLoader().getSprite("stone.broken.3")));
       world.register(new Block(new Box(new Vector(-8.5, 4.5), 1.0, 2.0), world.getLoader().getSprite("stone.broken.8")));
       
       world.register(new Block(new Box(new Vector(6.0, 5.0), 1.0, 3.0), world.getLoader().getSprite("stone.broken.7")));
       world.register(new Block(new Box(new Vector(8.0, 5.0), 1.0, 3.0), world.getLoader().getSprite("stone.broken.7")));
       world.register(new Block(new Box(new Vector(7.0, 4.0), 1.0, 1.0), world.getLoader().getSprite("stone.broken.1")));
       
       world.register(new Block(new Box(new Vector(-6.0, 6.5), new Vector(-5.0, 8.5)), world.getLoader().getSprite("stone.broken.8")));
       world.register(new Block(new Box(new Vector(-8.0, 7.5), new Vector(-6.0, 8.5)), world.getLoader().getSprite("stone.broken.2")));
       
       world.register(new Block(new Box(new Vector(3.0, 14.0), 2.0, 1.0), world.getLoader().getSprite("stone.broken.2")));
       world.register(new Block(new Box(new Vector(4.5, 15.0), 2.0, 1.0), world.getLoader().getSprite("stone.broken.2")));
       world.register(new Block(new Box(new Vector(5.5, 16.0), 2.0, 1.0), world.getLoader().getSprite("stone.broken.2")));
       
       world.register(new Block(new Box(new Vector(-12.0, -2.5), 1.0, 1.0), world.getLoader().getSprite("stone.1")));
       world.register(new Block(new Box(new Vector(11.0, -2.5), 1.0, 1.0), world.getLoader().getSprite("stone.1")));
       world.register(new Block(new Box(new Vector(-14.0, 1.0), 1.0, 1.0), world.getLoader().getSprite("stone.1")));
       world.register(new Block(new Box(new Vector(14.0, 1.0), 1.0, 1.0), world.getLoader().getSprite("stone.1")));
       world.register(new Block(new Box(new Vector(0.0, 8.5), 1.0, 1.0), world.getLoader().getSprite("stone.1")));
       
     //create jumpers
       world.register(new Jumper(new Vector(-12.0, -2.0)));
       world.register(new Jumper(new Vector(11.0, -2.0)));
       world.register(new Jumper(new Vector(-14.0, 1.5)));
       world.register(new Jumper(new Vector(14.0, 1.5)));
       world.register(new Jumper(new Vector(0.0, 9.0)));
       
     //create torches
       Torch torchR = new Torch(new Vector(1.0, 1.5), true);
       world.register(torchR);

       Torch torchL = new Torch(new Vector(-1.0, 1.5), true);
       world.register(torchL);
       
     //create levers
       Lever leverDL = new Lever(new Vector(-7.0, -3.25), Double.POSITIVE_INFINITY);
       world.register(leverDL);
       Lever leverDR = new Lever(new Vector(7.0, -3.25), Double.POSITIVE_INFINITY);
       world.register(leverDR);
       Lever leverUL = new Lever(new Vector(-6.5, 6.75), Double.POSITIVE_INFINITY);
       world.register(leverUL);
       Lever leverUR = new Lever(new Vector(7.0, 4.75), Double.POSITIVE_INFINITY);
       world.register(leverUR);
       Lever leverM = new Lever(new Vector(6.0, 16.75), 3.5);
       world.register(leverM);
       
       //create breakable blocks
       world.register(new Breakable(new Box(new Vector(-7.75, -3.25), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-6.25, -3.25), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-8.25, -3.25), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-5.75, -3.25), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-7.75, -2.75), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-6.25, -2.75), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-7.25, -2.75), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-6.75, -2.75), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-7.25, -2.25), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-6.75, -2.25), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       world.register(new Breakable(new Box(new Vector(-7.0, -1.75), 0.5, 0.5), world.getLoader().getSprite("box.empty")));
       
     //create spikes
       world.register(new Spike(new Box(new Vector(5.75, -3.25), 1.0, 0.5), world.getLoader().getSprite("spikes")));
       world.register(new Spike(new Box(new Vector(8.25, -3.25), 1.0, 0.5), world.getLoader().getSprite("spikes")));
       world.register(new Spike(new Box(new Vector(9.25, -4.25), 0.5, 1.0), world.getLoader().getSprite("spikes.right")));
       world.register(new Spike(new Box(new Vector(4.75, -4.25), 0.5, 1.0), world.getLoader().getSprite("spikes.left")));
       
     //create a mover that is activated by leverM
       world.register(new Mover(new Box(new Vector(8.0, 25.0), 2.0, 1.0),
    		   world.getLoader().getSprite("stone.broken.2"), leverM, new Vector(8.0, 16)));
       
     //create keys
       Key keyRed = new Key(new Vector(7.5, 27.0), Color.RED);
       world.register(keyRed);
       Key keyBlue = new Key(new Vector(8.5, 27.0), Color.BLUE);
       world.register(keyBlue);
       
     //create 2 doors that can be opened by collected the corresponding keys
       world.register(new Door(new Vector(-7.5, 7.0), world.getLoader().getSprite("lock.red"), keyRed));
       world.register(new Door(new Vector(7.0, 6.0), world.getLoader().getSprite("lock.blue"), keyBlue));
       
       //create a heart
       world.register(new Heart(new Vector(-6.0, 9.5)));
       
     //create the player
       Player player = new Player(new Vector(0.0, 2.0), new Vector(0.0,0.0), 100.0);
       world.register(player);
       
     //create the overlay
       world.register(new Overlay(player));
       
     //signals that allow to open the exit door
       And signal1 = new And(new Not(torchL),new And(torchR, new And(leverDR, new And(leverUL, new And(leverUR, new Not(leverDL))))));
       And signal2 = new And(new Not(torchR),new And(torchL,new And(leverDL, new And(leverDR, new And(leverUL, new Not(leverUR))))));
       Or signal3 = new Or(signal1, signal2);
       
       // create the exit door
       world.register(new Exit(new Vector(0.0, 1.0), null, signal3));
       
       // create limits of the level
       world.register(new Limits(new Box(new Vector(-30.0, -10.0), new Vector(30.0, 30.0))));
       
	}
	
}

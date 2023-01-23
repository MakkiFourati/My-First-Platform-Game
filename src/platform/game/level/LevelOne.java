/*
 *	Author:      Makki Fourati
 *	Date:        10 déc. 2016
 */
package platform.game.level;

import platform.game.ArrowThrower;
import platform.game.Block;
import platform.game.Color;
import platform.game.Constant;
import platform.game.Door;
import platform.game.Exit;
import platform.game.Heart;
import platform.game.Jumper;
import platform.game.Key;
import platform.game.Lever;
import platform.game.Limits;
import platform.game.Mover;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.Spike;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;

public class LevelOne extends Level {
	@Override
    public void register(World world) {
       super.register(world);
       
    // Register a new instance, to restart level automatically
       world.setNextLevel(new LevelOne());
       
     //create player
       Player player = new Player(new Vector(0.5, 0.5), new Vector(0.0,0.0), 100.0);
       world.register(player);
       
     //create overlay
       world.register(new Overlay(player));
       
     //create blocks
       world.register(new Block(new Box(new Vector(0.0, -3.0), new Vector(5.0, 0.0)), world.getLoader().getSprite("stone.broken.5")));
       world.register(new Block(new Box(new Vector(5.0, -3.0), new Vector(9.5, -1.0)), world.getLoader().getSprite("stone.3")));
       world.register(new Block(new Box(new Vector(9.5, -3.0), new Vector(13.0, 0.0)), world.getLoader().getSprite("stone.broken.4")));
       world.register(new Block(new Box(new Vector(15.0, -5.5), new Vector(18.0, 0.0)), world.getLoader().getSprite("stone.broken.6")));
       world.register(new Block(new Box(new Vector(14.0, 7.0), new Vector(17.0, 9.0)), world.getLoader().getSprite("stone.broken.5")));
       
       world.register(new Block(new Box(new Vector(4.0, 7.0), new Vector(7.0, 9.0)), world.getLoader().getSprite("stone.broken.5")));
       world.register(new Block(new Box(new Vector(1.0, 8.0), new Vector(4.0, 10.0)), world.getLoader().getSprite("stone.broken.5")));
       world.register(new Block(new Box(new Vector(-3.0, 9.0), new Vector(1.0, 11.0)), world.getLoader().getSprite("stone.broken.5")));
       
       world.register(new Block(new Box(new Vector(-6.0, 9.0), new Vector(-3.0, 15.0)), world.getLoader().getSprite("stone.broken.6")));
       world.register(new Block(new Box(new Vector(-3.0, 12.0), new Vector(-1.0, 15.0)), world.getLoader().getSprite("stone.broken.6")));
       world.register(new Block(new Box(new Vector(-6.0, 15.0), new Vector(-5.0, 16.0)), world.getLoader().getSprite("stone.broken.1")));
       
     //create spikes
       world.register(new Spike(new Box(new Vector(5.25, -1.0), new Vector(6.25, -0.5)), world.getLoader().getSprite("spikes")));
       world.register(new Spike(new Box(new Vector(6.25, -1.0), new Vector(7.25, -0.5)), world.getLoader().getSprite("spikes")));
       world.register(new Spike(new Box(new Vector(7.25, -1.0), new Vector(8.25, -0.5)), world.getLoader().getSprite("spikes")));
       world.register(new Spike(new Box(new Vector(8.25, -1.0), new Vector(9.25, -0.5)), world.getLoader().getSprite("spikes")));
       
     //create hearts
       world.register(new Heart(new Vector(14, -2.0)));
       world.register(new Heart(new Vector(11.0, 15.0)));
       
     //create lever
       Lever lever = new Lever(new Vector(17.5, 0.25), 3.5);
       world.register(lever);
       
       //create mover that depends on lever signal
       world.register(new Mover(new Box(new Vector(19.0, 8.0), new Vector(21.0, 9.0)),
    		   world.getLoader().getSprite("stone.2"), lever, new Vector(20.0, -0.25)));
       
     //create arrow thrower
       world.register( new ArrowThrower(new Vector(4.25, 9.25), world.getLoader().getSprite("stone.broken.1"), 38));
       world.register( new ArrowThrower(new Vector(4.25, 9.75), world.getLoader().getSprite("stone.broken.1"), 38));
       world.register( new ArrowThrower(new Vector(1.25, 10.25), world.getLoader().getSprite("stone.broken.1"), 38));
       world.register( new ArrowThrower(new Vector(1.25, 10.75), world.getLoader().getSprite("stone.broken.1"), 38));
       world.register( new ArrowThrower(new Vector(-4.75, 15.75), world.getLoader().getSprite("stone.broken.1"), 20));
       world.register( new ArrowThrower(new Vector(-4.75, 15.25), world.getLoader().getSprite("stone.broken.1"), 20));
       
       //create jumper
       world.register(new Jumper(new Vector(14.5, 9.0))); 
       
       //create key
       Key keyGreen = new Key(new Vector(-5.5, 16.5), Color.GREEN);
       world.register(keyGreen);
       
       // create a door which can be opened by collecting keyGreen
       world.register(new Door(new Vector(-1.5, 11.5), world.getLoader().getSprite("lock.green"), keyGreen));
       
       //create an exit door which is always open
       world.register(new Exit(new Vector(-2.5, 11.5), new LevelTwo(), new Constant()));
       
       //create limits of the level
       world.register(new Limits(new Box(new Vector(-20.0, -10.0), new Vector(30.0, 20.0))));
       
	}
}

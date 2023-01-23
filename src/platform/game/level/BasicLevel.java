package platform.game.level;


import platform.game.World;
import platform.game.Block;
import platform.game.Constant;
import platform.game.Exit;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.Player;
import platform.util.Box;
import platform.util.Vector;


public class BasicLevel extends Level {

   @Override
    public void register(World world) {
       super.register(world);
       
       // Register a new instance, to restart level automatically
       world.setNextLevel(new BasicLevel());
      
       // Create blocks
       world.register(new Block(new Box(new Vector(0.0, 0.0), 3.0, 1.0), world.getLoader().getSprite("stone.broken.3")));
       world.register(new Block(new Box(new Vector(2.0, 1.0), 3.0, 1.0), world.getLoader().getSprite("stone.broken.3")));
       world.register(new Block(new Box(new Vector(4.0, 2.0), 3.0, 1.0), world.getLoader().getSprite("stone.broken.3")));
       world.register(new Block(new Box(new Vector(6.0, 3.0), 3.0, 1.0), world.getLoader().getSprite("stone.broken.3")));
       
       //create limits of the level 
       world.register(new Limits(new Box(Vector.ZERO, 40, 30)));

       //create player
       Player player = new Player(new Vector(0.0, 2.0), new Vector(0.0,0.0), 100.0);
       world.register(player);
       
       //create overlay
       world.register(new Overlay(player));
      
       //create an exit door which is always open
       world.register(new Exit(new Vector(6.5, 4.0), new LevelOne(), new Constant()));
       
   }
    
}

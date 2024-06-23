package silkclient.events.impl;

import net.minecraft.world.World;
import silkclient.events.Event;

public class WorldChange extends Event {

    private World world;

    public World getWorld() {
        return world;
    }

   public WorldChange(World world) {
        this.world = world;
    }

}

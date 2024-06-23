package silkclient.mods.skyblock;

import net.minecraft.init.Items;
import silkclient.events.EventTarget;
import silkclient.events.impl.ContainerOpenEvent;

public class Test {

    @EventTarget
    public void onChestOpen(ContainerOpenEvent e) {
        if(e.getSlots().contains(Items.diamond_boots)) {
            System.out.println("Diamond Boots Found");
        }
    }

}

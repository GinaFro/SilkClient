package silkclient.events.impl;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import silkclient.events.Event;

import java.util.List;

public class ContainerOpenEvent extends Event {

    private GuiContainer container;

    public ContainerOpenEvent(GuiContainer c) {
        this.container = c;
    }

    public GuiContainer getContainer() {
        return container;
    }

    public List<ItemStack> getSlots() {
        return container.inventorySlots.getInventory();
    }

}

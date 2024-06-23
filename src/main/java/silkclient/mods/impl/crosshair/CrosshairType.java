package silkclient.mods.impl.crosshair;

import net.minecraft.util.ResourceLocation;

public enum CrosshairType {

    CROSS(new ResourceLocation("silkclient/crosshair/cross.png")),
    PLUS(new ResourceLocation("silkclient/crosshair/plus.png"));

    private ResourceLocation r;
    CrosshairType(ResourceLocation r) {
        this.r = r;
    }

    public ResourceLocation getResource() {
        return r;
    }

}

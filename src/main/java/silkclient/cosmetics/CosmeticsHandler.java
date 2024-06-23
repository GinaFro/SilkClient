package silkclient.cosmetics;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public abstract class CosmeticsHandler {


    private final String name;
    private final CosmeticsType type;
    private boolean enabled;

    public CosmeticsHandler(String name, CosmeticsType type) {
        this.name = name;
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract void render(Entity entityIn, ModelRenderer modelRenderer);

    public String getName() {
        return name;
    }

    public CosmeticsType getType() {
        return type;
    }

    public enum CosmeticsType {

        HAT,
        BODY,

    }

}

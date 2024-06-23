package silkclient;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;

public class ServerDataFeatured extends ServerData {

    public static final ResourceLocation STAR_ICON = new ResourceLocation("silkclient/star.png");

    public ServerDataFeatured(String name, String ip) {
        super(name, ip, false);
    }


}

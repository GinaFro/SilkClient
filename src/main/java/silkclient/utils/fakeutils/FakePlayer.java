package silkclient.utils.fakeutils;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import silkclient.mods.impl.toggless.SilkClientMovementInput;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;

public class FakePlayer extends EntityPlayerSP {

    public FakePlayer(Minecraft mc, World world) {
        this(mc, world, checkNullGameProfile());
    }






        private static final HashMap<String, ResourceLocation> playerHeads = new HashMap<>();

        public static void drawPlayerHead(String name, int x, int y, int size) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation headLocation = getHeadLocation(name);
            Minecraft.getMinecraft().getTextureManager().bindTexture(headLocation);
            Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, size, size, size, size);
        }

        public static ResourceLocation getHeadLocation(String username) {
            ResourceLocation playerHead = (ResourceLocation) playerHeads.getOrDefault(username,
                    new ResourceLocation("silkclient/heads/" + username + ".png"));
            if (!playerHeads.containsKey(username)) {
                ThreadDownloadImageData skinData = new ThreadDownloadImageData(null,
                        "https://minotar.net/helm/" + username + "/32.png",
                        new ResourceLocation("silkclient/heads/steve.png"), null);
                (Minecraft.getMinecraft()).getTextureManager().loadTexture(playerHead, skinData);
                playerHeads.put(username, playerHead);
            } else {
            }
            return playerHead;
        }


    public FakePlayer(Minecraft mc, World world, GameProfile gp) {
        super(mc, world, new NetHandlerPlayClient(mc, mc.currentScreen, new FakeNetworkManager(EnumPacketDirection.CLIENTBOUND), gp) {
            @Override
            public NetworkPlayerInfo getPlayerInfo(String p_175104_1_) {
                return new FakeNetworkPlayerInfo(gp);
            }
            @Override
            public NetworkPlayerInfo getPlayerInfo(UUID p_175102_1_) {
                return new FakeNetworkPlayerInfo(gp);
            }
        }, null);

        this.dimension = 0;
        this.movementInput = new SilkClientMovementInput(Minecraft.getMinecraft().gameSettings);
        this.posX = 0;
        this.posY = 0;
        this.posZ = 0;
    }

    @Override
    public float getEyeHeight() {
        return 1.82F;
    }

    @Override
    public boolean isWearing(EnumPlayerModelParts p_175148_1_) {
        return true;
    }

    //getPlayerInfo() is aparently not called in hasPlayerInfo? Not super sure why
    @Override
    public boolean hasPlayerInfo() {
        return true;
    }

    //might not be nessessary, not sure as of now
    @Override
    protected NetworkPlayerInfo getPlayerInfo() {
        return new FakeNetworkPlayerInfo(getGameProfile());
    }

    //Some people were experiencing a null GameProfile for their session
    //Not sure why, but his is a fix for it
    private static GameProfile checkNullGameProfile() {
        if(Minecraft.getMinecraft().getSession() == null || Minecraft.getMinecraft().getSession().getProfile() == null) {
            return new GameProfile(UUID.randomUUID(), "FakePlayer");
        }
        return Minecraft.getMinecraft().getSession().getProfile();
    }
}

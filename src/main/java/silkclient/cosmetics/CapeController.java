package silkclient.cosmetics;


import com.google.gson.JsonObject;
import net.minecraft.util.AnimatedResourceLocation;
import net.minecraft.util.ResourceLocation;
import silkclient.mods.ModInstances;
import silkclient.mods.impl.ModCape;

public class CapeController {

    private static CapeController instance = new CapeController();

    public static CapeController getInstance() {
        return instance;
    }



    public enum Cape {
        DEFAULT(new ResourceLocation("silkclient/cosmetics/capes/default.png") , 1),
        GALAXY(new ResourceLocation("silkclient/cosmetics/capes/galaxy.png"), 2),
        MINECON(new ResourceLocation("silkclient/cosmetics/capes/minecon.png"), 3),
        WITHER(new ResourceLocation("silkclient/cosmetics/capes/wither.PNG"), 4),
        DRAGON(new ResourceLocation("silkclient/cosmetics/capes/dragon.PNG"), 5),
        CLIENT(new ResourceLocation("silkclient/cosmetics/capes/silkclient.PNG"), 6),
        SAD_MAN(new ResourceLocation("silkclient/cosmetics/capes/sad_man.png"), 7),
        WATERWAVE(new ResourceLocation("silkclient/cosmetics/capes/waterwave.png"), 8),
        AKATSUKI(new ResourceLocation("silkclient/cosmetics/capes/Akatsuki.png"), 9),
        SAKURA(new ResourceLocation("silkclient/cosmetics/capes/sakura.png"), 10),
        OMEGA(new ResourceLocation("silkclient/cosmetics/capes/omega.png"), 11),
        PURPLE_SUNSET(new ResourceLocation("silkclient/cosmetics/capes/purple_sunset.png"), 12),
        HAPPY_MAN(new ResourceLocation("silkclient/cosmetics/capes/happy_man.png"), 13),
        NETHER(new ResourceLocation("silkclient/cosmetics/capes/nether.png"), 14),
        FIELD(new ResourceLocation("silkclient/cosmetics/capes/anime.png"), 15),
        NIGHT(new ResourceLocation("silkclient/cosmetics/capes/anime_night.png"), 16),
        NARUTO_CAPE(new ResourceLocation("silkclient/cosmetics/capes/naruto.png"), 17),
        BATMAN(new ResourceLocation("silkclient/cosmetics/capes/batman.png"),18);

        private ResourceLocation r;
        private int id;
        Cape(ResourceLocation r, int id) {
            this.r = r;
            this.id = id;
        }

        public ResourceLocation getResource() {
            return r;
        }

        public int getId() {
            return id;
        }

        public static Cape getCapeFromID(int id) {
            Cape cape = Cape.DEFAULT;
            for(Cape c : Cape.values()) {
                if(c.id == id) {
                    cape = c;
                }
            }
            return cape;
        }



    }

    public static void next() {
        Cape current = getCurrentCape();
        int nextId = current.id + 1;
        Cape next = Cape.getCapeFromID(nextId);
        setCurrentCape(next);
    }

    public static void prev() {
        Cape current = getCurrentCape();
        int prevID = current.id - 1;
        if(current.id - 1 == 0){
            prevID = 18;
        }
        Cape next = Cape.getCapeFromID(prevID);
        setCurrentCape(next);
    }

    private static Cape currentCape = ModInstances.getModCape().getCape();

    public static void setCurrentCape(Cape currentCape) {
        CapeController.currentCape = currentCape;
    }

    public static Cape getCurrentCape() {
        return currentCape;
    }
}

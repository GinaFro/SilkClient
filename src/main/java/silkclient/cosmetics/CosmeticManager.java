package silkclient.cosmetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CosmeticManager {

    public static List<CosmeticsHandler> cosmetics = new ArrayList<>();


    public static void registerCosmetics(CosmeticsHandler... handlers) {
        cosmetics.addAll(Arrays.asList(handlers));
    }
    
}

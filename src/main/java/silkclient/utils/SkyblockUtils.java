package silkclient.utils;

import silkclient.Client;
import silkclient.mods.skyblock.Location;

public class SkyblockUtils {

    public static boolean inDungeons() {
        if(inSkyblock()) {
            return Location.getLocation(Client.getLocationString()) == Location.CATACOMBS;
        }else {
            return false;
        }
    }

    public static boolean inSkyblock() {
        return Client.getLocationString() != null;
    }

    public static Location getLocation() {
        return Location.getLocation(Client.getLocationString());
    }

}

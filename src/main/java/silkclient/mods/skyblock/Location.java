package silkclient.mods.skyblock;

public enum Location {
    NONE(""),
    VILLAGE("Village"),
    CATACOMBS("The Catacombs"),
    SPRUCE("Spruce Woods"),
    DARK("Dark Thicket"),
    SAVANNA("Savanna Woodlands"),
    JUNGLE("Jungle Island"),
    BAZAAR("Bazaar Alley"),
    AUCTION("Auction House"),
    GRAVEYARD("Graveyard"),
    FOREST("Forest"),
    MOUNTAIN("Mountain"),
    HIGH_LEVEL("High Level"),
    WILDERNESS("Wilderness"),
    FARM("Farm"),
    AREA("Coal Mine"),
    BARN("The Barn"),
    DESERT("Desert Settlement"),
    OASIS("Oasis"),
    MUSHROOM("Mushroom Desert"),
    END("The End"),
    DRAGON("Dragon Nest"),
    VOID("Void Sculpture"),
    GOLD("Gold Mine"),
    DEEP("Deep Caverns"),
    IRON("Gunpowder Mines"),
    LAPIS("Lapis Quarry"),
    ISLAND("Private Island"),
    REDSTONE("Pigmen's Den"),
    EMERALD("Slimehill"),
    DIAMOND("Diamond Reserve"),
    OBSIDIAN("Obsiddian Sanctuary"),
    DWARVEN("Dwarven Village"),
    DWARVEN_MINES("Dwarven Mines"),
    BRIDGE("Palace Bridge"),
    PALACE("Royal Palace"),
    ICE_WALL("Great Ice Wall"),
    DIVAN("Divan's Gateway"),
    CLIFFSIDE("Cliffside Veins"),
    RAMPART("Rampart's Quarry"),
    UPPER("Upper Mines"),
    FORGE("Forge Basin"),
    GATE("Gates to the Mines"),
    THE_FORGE("The Forge"),
    DUNGEON_HUB("Dungeon Hub"),
    CRIMSON("Crimson Isle"),
    STRONGHOLD("Strongholdd"),
    CRIMSON_FIELDS("Crimson Fields"),
    BURNING_DESERT("Burning Desert"),
    DRAGONTAIL("Dragontail"),
    ASHFANG("Ruins of Ashfang"),
    WASTELAND("The Wasteland"),
    MARSH("Mythic Marsh"),
    SCARELTON("Scarleton"),
    SPIDER("Spider's Den"),
    BIRCH_PARK("Birch Park");

    private String s;
    Location(String s) {
        this.s = s;
    }

    public String getArea() {
        return s;
    }

    public static Location getLocation(String s) {
        for(Location l : Location.values()) {
            if(l.getArea().toLowerCase().contains(s.toLowerCase())){
                return l;
            }
        }
        return NONE;
    }

    public static boolean locationExists(String s) {
        for(Location l : Location.values()) {
            if(s.equals(l.getArea())) {
               return true;
            }
        }
        return false;
    }

}

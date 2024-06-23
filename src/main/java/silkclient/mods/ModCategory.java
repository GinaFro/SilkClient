package silkclient.mods;

public enum ModCategory {

    SKYBLOCK("Skyblock"),
    HUD("Hud"),
    PVP("Pvp"),
    MISC("Misc");

    private String s;
  ModCategory(String s) {
    this.s = s;
  }

  private String getCategory(){return s;}


}

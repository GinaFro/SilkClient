package silkclient.utils;

import com.google.gson.JsonObject;

public class MiscUtils {

    public static MiscUtils Instance = new MiscUtils();
    private double cakeTime = 0;

    public void setCakeTime(double cakeTime) {
        this.cakeTime = cakeTime;
    }

    public double getCakeTime() {
        return cakeTime;
    }

    public static MiscUtils getInstance() {
        return Instance;
    }



}

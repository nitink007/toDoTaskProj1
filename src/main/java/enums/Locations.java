package enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Locations {

    GROFERS(-40f, 5f, 5f, 100f);

    private float minLat;
    private float minLng;
    private float maxLat;
    private float maxLng;

    public float getMinLat() {
        return this.minLat;
    }

    public float getMinLng() {
        return this.minLng;
    }

    public float getMaxLat() {
        return this.maxLat;
    }

    public float getMaxLng() {
        return this.maxLng;
    }

    Locations(float minLat, float maxLat, float minLng, float maxLng) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLng  = minLng;
        this.maxLng = maxLng;
    }

}

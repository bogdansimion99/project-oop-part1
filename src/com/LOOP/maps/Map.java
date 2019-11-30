package com.LOOP.maps;

public class Map {
    private float modificator;
    private String type;
    private static Map instance = null;

    public static Map getInstance() {
        if(instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public float getModificator() {
        return modificator;
    }

    public void setModificator(float modificator) {
        this.modificator = modificator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map() {
        this.modificator = 0;
        this.type = "";
    }

    public Map(float modificator, String type) {
        this.modificator = modificator;
        this.type = type;
    }
}

package com.LOOP.maps;

import com.LOOP.helpers.GeneralConstants;

public class Land extends Map {
    private Map land;

    public Map getLand() {
        return land;
    }

    public Land(Map land) {
        super(GeneralConstants.LAND_MODIFICATOR, "Land");
        this.land = land;
    }
}

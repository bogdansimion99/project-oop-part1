package com.LOOP.maps;

import com.LOOP.helpers.GeneralConstants;

public class Desert extends Map {
    private Map desert;

    public Map getDesert() {
        return desert;
    }

    public Desert(Map desert) {
        super(GeneralConstants.DESERT_MODIFICATOR, "Desert");
        this.desert = desert;
    }
}

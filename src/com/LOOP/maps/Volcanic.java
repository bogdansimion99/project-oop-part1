package com.LOOP.maps;

import com.LOOP.helpers.GeneralConstants;

public class Volcanic extends Map {
    private Map volcanic;

    public Map getVolcanic() {
        return volcanic;
    }

    public Volcanic(Map volcanic) {
        super(GeneralConstants.VOLCANIC_MODIFICATOR, "Volcanic");
        this.volcanic = volcanic;
    }
}

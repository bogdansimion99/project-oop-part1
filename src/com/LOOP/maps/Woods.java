package com.LOOP.maps;

import com.LOOP.helpers.GeneralConstants;

public class Woods extends Map {
    private Map woods;

    public Map getWoods() {
        return woods;
    }

    public Woods(Map woods) {
        super(GeneralConstants.WOODS_MODIFICATOR, "Woods");
        this.woods = woods;
    }
}

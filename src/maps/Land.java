package maps;

import helpers.GeneralConstants;

public class Land extends Map {
    private Map land;

    /**
     * @return
     */
    public Map getLand() {
        return land;
    }

    public Land(final Map land) {
        super(GeneralConstants.LAND_MODIFICATOR, "Land");
        this.land = land;
    }
}

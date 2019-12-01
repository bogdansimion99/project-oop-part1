package maps;

import helpers.GeneralConstants;

public class Desert extends Map {
    private Map desert;

    /**
     * @return
     */
    public Map getDesert() {
        return desert;
    }

    public Desert(final Map desert) {
        super(GeneralConstants.DESERT_MODIFICATOR, "Desert");
        this.desert = desert;
    }
}

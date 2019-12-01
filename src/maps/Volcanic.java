package maps;

import helpers.GeneralConstants;

public class Volcanic extends Map {
    private Map volcanic;

    /**
     * @return
     */
    public Map getVolcanic() {
        return volcanic;
    }

    public Volcanic(final Map volcanic) {
        super(GeneralConstants.VOLCANIC_MODIFICATOR, "Volcanic");
        this.volcanic = volcanic;
    }
}

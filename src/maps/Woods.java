package maps;

import helpers.GeneralConstants;

public class Woods extends Map {
    private Map woods;

    /**
     * @return
     */
    public Map getWoods() {
        return woods;
    }

    public Woods(final Map woods) {
        super(GeneralConstants.WOODS_MODIFICATOR, "Woods");
        this.woods = woods;
    }
}

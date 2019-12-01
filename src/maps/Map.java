package maps;

public class Map {
    private float modificator;
    private String type;
    private static Map instance = null;

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    /**
     * @return
     */
    public float getModificator() {
        return modificator;
    }

    /**
     * @param modificator
     */
    public void setModificator(final float modificator) {
        this.modificator = modificator;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    public Map() {
        this.modificator = 0;
        this.type = "";
    }

    public Map(final float modificator, final String type) {
        this.modificator = modificator;
        this.type = type;
    }
}

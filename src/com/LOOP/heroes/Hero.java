package com.LOOP.heroes;

import com.LOOP.helpers.ModificatorVisitor;
import com.LOOP.maps.Map;

public class Hero {
    private int hp;
    private int xp;
    private int level;
    private int[] position;
    private String type;

    /**
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp
     */
    public void setHp(final int hp) {
        this.hp = hp;
    }

    /**
     * @return
     */
    public int getXp() {
        return xp;
    }

    /**
     * @param xp
     */
    public void setXp(final int xp) {
        this.xp = xp;
    }

    /**
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * @return
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(final int[] position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Hero() {
        this.hp = 0;
        this.level = 0;
        this.xp = 0;
        this.position = new int[]{0, 0};
        this.type = "";
    }

    public Hero(final int hp, final int[] position/*, final String type*/) {
        this.hp = hp;
        this.xp = 0;
        this.level = 0;
        this.position = position;
        this.type = type;
    }

    public void levelUp(final Hero player) {
        if (player.xp < 300) {
            player.level = 0;
        } else {
            player.level = (player.hp - 250) / 50;
        }
    }

    public void action (final Hero aggressor, final Hero victim, final Map area) {

    }

    public float accept (final ModificatorVisitor modificatorVisitor, float[] modificators) {
        return 0.0f;
    }
}

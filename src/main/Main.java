package main;

import fileio.FileSystem;
import heroes.*;
import maps.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(final String[] args) throws IOException {
        FileSystem fs = new FileSystem(args[0], args[1]);
        int rows = fs.nextInt();
        int columns = fs.nextInt();
        Map[][] map = new Map[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                switch (fs.nextWord()) {
                    case "W":
                        map[i][j] = new Woods(map[i][j]);
                        break;
                    case "D":
                        map[i][j] = new Desert(map[i][j]);
                        break;
                    case "L":
                        map[i][j] = new Land(map[i][j]);
                        break;
                    default:
                        map[i][j] = new Volcanic(map[i][j]);
                        break;
                }
            }
        }
        int noHeroes = fs.nextInt();
        ArrayList<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < noHeroes; i++) {
            Hero hero;
            switch (fs.nextWord()) {
                case "W":
                    hero = new Wizard(new Hero(), new int[]{fs.nextInt(), fs.nextInt()});
                    heroes.add(hero);
                    break;
                case "K":
                    hero = new Knight(new Hero(), new int[]{fs.nextInt(), fs.nextInt()});
                    heroes.add(hero);
                    break;
                case "P":
                    hero = new Pyromancer(new Hero(), new int[]{fs.nextInt(), fs.nextInt()});
                    heroes.add(hero);
                    break;
                default:
                    hero = new Rogue(new Hero(), new int[]{fs.nextInt(), fs.nextInt()});
                    heroes.add(hero);
                    break;
            }
        }
        int noRounds = fs.nextInt();
        for (int i = 0; i < noRounds; i++) {
            for (int j = 0; j < noHeroes; j++) {
                for (int k = j; k < noHeroes; k++) {
                    if (Arrays.equals(heroes.get(j).getPosition(), heroes.get(k).getPosition())) {
                        heroes.get(j).action(heroes.get(j), heroes.get(k), map[heroes.get(j).
                                getPosition()[0]][heroes.get(j).getPosition()[1]]);
                        heroes.get(k).action(heroes.get(k), heroes.get(j), map[heroes.get(k).
                                getPosition()[0]][heroes.get(k).getPosition()[1]]);
                    }
                }
            }
            for (int j = 0; j < noHeroes; j++) {
                switch (fs.nextWord()) {
                    case "U":
                        if (heroes.get(j).getOvertime() > 0) {
                            heroes.get(j).setPosition(new int[] {heroes.get(j).getPosition()[0] - 1,
                                    heroes.get(j).getPosition()[1]});
                        }
                        break;
                    case "D":
                        if (heroes.get(j).getOvertime() > 0) {
                            heroes.get(j).setPosition(new int[] {heroes.get(j).getPosition()[0] + 1,
                                    heroes.get(j).getPosition()[1]});
                        }
                        break;
                    case "L":
                        if (heroes.get(j).getOvertime() > 0) {
                            heroes.get(j).setPosition(new int[] {heroes.get(j).getPosition()[0],
                                    heroes.get(j).getPosition()[1] - 1});
                        }
                        break;
                    case "R":
                        if (heroes.get(j).getOvertime() > 0) {
                            heroes.get(j).setPosition(new int[] {heroes.get(j).getPosition()[0],
                                    heroes.get(j).getPosition()[1] + 1});
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        for (Hero x: heroes) {
            switch (x.getType()) {
                case "Pyromancer":
                    fs.writeCharacter('P');
                    break;
                case "Knight":
                    fs.writeCharacter('K');
                    break;
                case "Rogue":
                    fs.writeCharacter('R');
                    break;
                case "Wizard":
                    fs.writeCharacter('W');
                    break;
                default:
                    break;
            }
            if (x.getHp() == 0) {
                fs.writeWord("dead");
                fs.writeNewLine();
                continue;
            }
            fs.writeInt(x.getLevel());
            fs.writeInt(x.getXp());
            fs.writeInt(x.getPosition()[0]);
            fs.writeInt(x.getPosition()[1]);
            fs.writeNewLine();
        }
    }
}

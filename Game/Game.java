package Game;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public enum Teams {
        TEAM1, TEAM2
    }

    GameLog gameLog;

    private ArrayList<Hero> team1 = new ArrayList<>();
    private ArrayList<Hero> team2 = new ArrayList<>();

    public Game(GameLog gameLog) {
        this.gameLog = gameLog;
    }

    public void addHero(Teams team, Hero.HeroTypes heroType, String name) {
        Hero hero = null;

        switch (heroType) {
            case WARRIOR:
                hero = new Warrior(290, name, 60, 0);
                break;
            case ASSASSIN:
                hero = new Assassin(160, name, 90, 0);
                break;
            case DOCTOR:
                hero = new Doctor(110, name, 0, 80);
        }

        switch (team) {
            case TEAM1:
                team1.add(hero);
                break;
            case TEAM2:
                team2.add(hero);
                break;
        }
    }

    public ArrayList<Hero> getTeam(Teams team) {
        switch (team) {
            case TEAM1:
                return team1;
            case TEAM2:
                return team2;
        }
        return null;
    }

    public void startGame(int rounds) {
        gameLog.clearLog();

        if(team1.size() == 0 || team2.size() == 0) {
            gameLog.addToLog("Команда не может быть пустой! Выберите героев.");
            return;
        }

        gameLog.addToLog("Да свершится битва героев!");

        Random randomHealing = new Random();
        Random randomHit = new Random();

        for (int j = 0; j < rounds; j++) {
            for (int i = 0; i < team1.size(); i++) {
                String result;
                if (team1.get(i) instanceof Doctor) {
                    result = team1.get(i).healing(team1.get(randomHealing.nextInt(team1.size())));
                } else {
                    result = team1.get(i).hit(team2.get(randomHit.nextInt(team2.size())));
                }
                gameLog.addToLog(result);
            }
            for (int i = 0; i < team2.size(); i++) {
                String result;
                if (team2.get(i) instanceof Doctor) {
                    result = team2.get(i).healing(team2.get(randomHealing.nextInt(team2.size())));
                } else {
                    result = team2.get(i).hit(team1.get(randomHit.nextInt(team1.size())));
                }
                gameLog.addToLog(result);
            }
        }

        gameLog.addToLog("---------------");

        for (Hero t1 : team1) {
            gameLog.addToLog(t1.toString());
        }

        for (Hero t2 : team2) {
            gameLog.addToLog(t2.toString());
        }
    }
}

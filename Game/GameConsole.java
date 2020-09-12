package Game;

import java.util.Random;

class GameConsole implements GameLog {
    public static void main(String[] args) {
        new GameConsole();
    }
    
    public GameConsole() {
        Game game = new Game(this);
        game.addHero(Game.Teams.TEAM1, Hero.HeroTypes.WARRIOR, "Тигрил");
        game.addHero(Game.Teams.TEAM1, Hero.HeroTypes.ASSASSIN, "Акали");
        game.addHero(Game.Teams.TEAM1, Hero.HeroTypes.DOCTOR, "Жанна");
    
        game.addHero(Game.Teams.TEAM2, Hero.HeroTypes.WARRIOR, "Минотавр");
        game.addHero(Game.Teams.TEAM2, Hero.HeroTypes.ASSASSIN, "Джинкс");
        game.addHero(Game.Teams.TEAM2, Hero.HeroTypes.DOCTOR, "Зои");
        
        game.startGame(4);
    }
    
    @Override
    public void clearLog() {
    
    }
    
    @Override
    public void addToLog(String text) {
        System.out.println(text);
    }
}

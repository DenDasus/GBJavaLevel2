package Game;

import java.util.Random;

class Assassin extends Hero {
    
    int cricitalHit;
    Random random = new Random();
    
    public Assassin(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
        this.cricitalHit = random.nextInt(20);
    }
    
    @Override
    String hit(Hero hero) {
        String result = "";
        if (hero != this) {
            if (health < 0) {
                result += "Герой погиб и бить не может!\n";
            } else {
                hero.causeDamage(damage + cricitalHit);
            }
            result += this.name + " нанес урон " + hero.name;
        }
        return result;
    }
    
    @Override
    String healing(Hero hero) {
        return "Убийцы не умеют лечить!";
    }
}
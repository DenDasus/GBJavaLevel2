package Game;

class Warrior extends Hero {
    
    public Warrior(int health, String type, int damage, int addHeal) {
        super(health, type, damage, addHeal);
    }
    
    @Override
    String hit(Hero hero) {
        String result = "";
        if (hero != this) {
            if (health < 0) {
                result += "Герой погиб и бить не может!\n";
            } else {
                hero.causeDamage(damage);
            }
            result += this.name + " нанес урон " + hero.name;
        }
        return result;
    }
    
    @Override
    String healing(Hero hero) {
        return "Войны не умеют лечить!";
    }
}
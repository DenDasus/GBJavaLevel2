package Game;

abstract class Hero {

    public enum HeroTypes {
        ASSASSIN, DOCTOR, WARRIOR
    }
    
    protected int health;
    protected String name;
    protected int damage;
    protected int addHeal;
    
    public Hero(int health, String name, int damage, int addHeal) {
        this.health = health;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
    }
    
    abstract String hit(Hero hero);
    
    abstract String healing(Hero hero);
    
    void causeDamage(int damage) {
        if (health < 0) {
            System.out.println("Герой уже мертвый!");
        } else {
            health -= damage;
        }
        
    }
    
    public int getHealth() {
        return health;
    }
    
    void addHealth(int health) {
        this.health += health;
    }
    
    void info() {
        System.out.println(name + " " + (health < 0 ? "Герой мертвый" : health) + " " + damage);
    }

    @Override
    public String toString() {
        return name + " " + (health < 0 ? "Герой мертвый" : health) + " " + damage;
    }
}
package Game;

class Doctor extends Hero {
    
    public Doctor(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
    }
    
    @Override
    String hit(Hero hero) {
        return "Доктор не может бить!";
    }
    
    @Override
    String healing(Hero hero) {
        hero.addHealth(addHeal);
        return this.name + " лечит " + hero.name;
    }
}
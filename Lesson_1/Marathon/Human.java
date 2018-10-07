package Lesson_1.Marathon;

public class Human implements Competitor {
    String name;

    int maxRunDistance;
    int maxJumpHeigth;
    int maxSwimDistance;

    boolean active;

    public Human(String name) {

        this.name = name;
        this.maxRunDistance = 5000;
        this.maxJumpHeigth = 300;
        this.maxSwimDistance = 500;
        this.active = true;
    }

    @Override
    public void run(int dist) {
        if(dist <= maxRunDistance) {
            System.out.println(name + " справился с кроссом!");
        } else {
            System.out.println(name + " не справился с кроссом!");
            active = false;
        }
    }

    @Override
    public void swim(int dist) {
        if(dist <= maxSwimDistance) {
            System.out.println( name + " справился с заплывом!");
        } else {
            System.out.println(name + " не справился с заплывом!");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        if(height <= maxJumpHeigth) {
            System.out.println( name + " справился с высотой!");
        } else {
            System.out.println(name + " не справился с высотой!");
            active = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return active;
    }

    @Override
    public void info() {
        System.out.println(name/* + " " + active*/);
    }
}
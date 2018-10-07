package Lesson_1.Marathon;

public class Animal implements Competitor {
    String type;
    String name;

    int maxRunDistance;
    int maxJumpHeigth;
    int maxSwimDistance;

    boolean onDistance;

    public Animal(String type, String name, int maxRunDistance, int maxJumpHeigth, int maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeigth = maxJumpHeigth;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    @Override
    public void run(int dist) {
        if(dist <= maxRunDistance) {
            System.out.println(type + " " + name + " справился с кроссом!");
        } else {
            System.out.println(type + " " + name + " не справился с кроссом!");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist) {
        if(dist <= maxSwimDistance) {
            System.out.println(type + " " + name + " справился с заплывом!");
        } else {
            System.out.println(type + " " + name + " не справился с заплывом!");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if(height <= maxJumpHeigth) {
            System.out.println(type + " " + name + " справился с высотой!");
        } else {
            System.out.println(type + " " + name + " не справился с высотой!");
            onDistance = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        System.out.println(type + " " + name/* + " " + onDistance*/);
    }
}

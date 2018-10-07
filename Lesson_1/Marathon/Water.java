package Lesson_1.Marathon;

public class Water extends Obstacle {
    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    void doit(Competitor competitor) {
        competitor.swim(length);
    }
}

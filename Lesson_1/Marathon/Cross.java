package Lesson_1.Marathon;

public class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    void doit(Competitor competitor) {
        competitor.run(length);
    }
}

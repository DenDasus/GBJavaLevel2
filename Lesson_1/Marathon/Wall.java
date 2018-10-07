package Lesson_1.Marathon;

public class Wall extends Obstacle {
    int heigth;

    public Wall(int heigth) {
        this.heigth = heigth;
    }

    @Override
    void doit(Competitor competitor) {
        competitor.jump(heigth);
    }
}

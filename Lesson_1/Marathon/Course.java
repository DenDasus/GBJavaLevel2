package Lesson_1.Marathon;

import java.util.ArrayList;

public class Course {
    ArrayList<Obstacle> obstacles = new ArrayList<>();

    public void doIt(Team team) {
        for (Competitor c : team.getCompetitors()) {
            for (Obstacle o : obstacles) {
                o.doit(c);
                if (!c.isOnDistance()) {
                    break;
                }
            }
        }
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
}

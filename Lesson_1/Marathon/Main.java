package Lesson_1.Marathon;

public class Main {
    public static void main(String[] args) {
        Course c = new Course();
        c.addObstacle(new Cross(80));
        c.addObstacle(new Wall(5));
        c.addObstacle(new Water(100));

        Team team = new Team("Command 1", new Human("Bob"), new Cat("Baksik"), new Dog("Bobick"), new Dog("Tuzik"));
        System.out.println("Команда " + team.getTitle() + ": ");
        team.showResults();

        c.doIt(team);

        System.out.println("Прошли дистанцию: ");
        team.showOnDistance();
    }
}

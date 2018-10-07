package Lesson_1.Marathon;

public class Team {
    Competitor[] competitors;
    String title;

    public Team(String title, Competitor a, Competitor b, Competitor c, Competitor d) {
        this.title = title;
        competitors = new Competitor[4];
        competitors[0] = a;
        competitors[1] = b;
        competitors[2] = c;
        competitors[3] = d;
    }

    public void showResults() {
        for (Competitor c : competitors) {
            c.info();
        }
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }

    public void showOnDistance() {
        for (Competitor c : competitors) {
            if(c.isOnDistance()) {
                c.info();
            }
        }
    }

    public String getTitle() {
        return title;
    }
}

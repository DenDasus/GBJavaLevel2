package Lesson_2.Task1Enums;

public class DayOfWeekMain {
    public enum DayOfWeek {
        MONDAY(1, 8), TUESDAY(2, 8), WEDNESDAY(3, 8), THURSDAY(4, 8), FRIDAY(5, 8), SATURDAY(6, 0), SUNDAY(7, 0);
        
        public int number;
        public int workHours;
        
        DayOfWeek(int number, int workHours) {
            this.number = number;
            this.workHours = workHours;
        }
        
        public int getNumber() {
            return number;
        }
        
        public int getWorkHours() {
            return workHours;
        }
    }
    
    public static int getWorkingHours(DayOfWeek day) {
        if (day != DayOfWeek.SATURDAY || day != DayOfWeek.SUNDAY) {
            int hours = 0;
            for (DayOfWeek d : DayOfWeek.values()) {
                if (d.number >= day.number) {
                    hours += d.getWorkHours();
                }
            }
            return hours;
        }
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println("Сколько часов до окончания рабочей недели?");
        System.out.println("Понедельник: " + getWorkingHours(DayOfWeek.MONDAY));
        System.out.println("Вторник: " + getWorkingHours(DayOfWeek.TUESDAY));
        System.out.println("Среда: " + getWorkingHours(DayOfWeek.WEDNESDAY));
        System.out.println("Четверг: " + getWorkingHours(DayOfWeek.THURSDAY));
        System.out.println("Пятница: " + getWorkingHours(DayOfWeek.FRIDAY));
        System.out.println("Суббота: " + getWorkingHours(DayOfWeek.SATURDAY));
        System.out.println("Воскресенье: " + getWorkingHours(DayOfWeek.SUNDAY));
    }
}

package Lesson_3.Task_3;

public class PasswordCheck {
    public static void main(String[] args) {
        System.out.println(regExPasswordCheck("test1A"));
        System.out.println(regExPasswordCheck("testTEST"));
        System.out.println(regExPasswordCheck("testt1st"));
        System.out.println(regExPasswordCheck("testT1ST"));
    }
    
    public static boolean regExPasswordCheck(String pass) {
        return pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
    }
}


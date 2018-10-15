package Lesson_3.Task_2;

public class PhoneBookMain {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        
        phoneBook.add("Ivanov", "+7(942)984-67-89");
        phoneBook.add("Ivanov", "+7(942)874-62-12");
        phoneBook.add("Ivanov", "+7(942)874-62-12");
        phoneBook.add("Petrov", "+7(921)736-95-15");
        phoneBook.add("Sidorov", "+7(967)483-75-99");
    
        System.out.println("Имя: " + "Ivanov" + ", Телефон:" + phoneBook.get("Ivanov"));
        System.out.println("Имя: " + "Petrov" + ", Телефон:" + phoneBook.get("Petrov"));
    }
}

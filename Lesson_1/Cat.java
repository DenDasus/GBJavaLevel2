package Lesson_1;

public class Cat {
    String name;
    String color;
    private int age;


    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Возраст не может быть отрицательным!");
        }
    }

    //    void sum(int a, int b) {
//        System.out.println(a + b);
//    }
//
//    void sum(float a, float b) {
//        System.out.println(a + b);
//    }

    void sum(int... mass) {
        for (int c : mass) {
            System.out.println(c);
        }
    }

    void info() {
        System.out.println(name + " " + color + " " + age);
    }

    void info(String name, int age) {
        System.out.println(name + " " + color + " " + age);
    }

    public Cat() {

    }

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }


    public Cat(String name, String color) {
        this.name = name;
        this.color = color;
    }
}


class MainClass {
    public static void main(String[] args) {
        Cat[] cats = new Cat[2];
        //  Cat[] cats = {new Cat(), new Cat()};
//        cats[0].name = "Vaska";
//        cats[0].age = 10;
//        cats[1].name = "Vaska1";
//        cats[1].age = 15;
//
//        cats[0].info();
//        cats[1].info();

    }

    void test() {
        System.out.println(1);
    }

}


interface IRun {
    void run();
}


interface IJump {
    void jump();
}


abstract class Animal {

    abstract void run();

    int a;

    void info() {
        System.out.println("Звук животного");
    }
}

class Dog extends Animal implements IRun, IJump {


    @Override
    public void run() {

    }

    @Override
    public void jump() {

    }
}

class SuperDog extends Dog {
    @Override
    void info() {
        System.out.println("Звук SuperDog");
    }

}

class MainZoo {
    public static void main(String[] args) {
        Animal[] animals = new Animal[]{new Dog(), new SuperDog()};
    }
}







class Window {

    public String getTitle() {
        return title;
    }

    private String title = "windowTitle";
    private int size = 100;


    public Window(String title, int size) {
        this.title = title;
        this.size = size;
    }

    void addParam() {
        SubWindow subWindow = new SubWindow(this);
    }
}

class SubWindow {

    Window window;

    public SubWindow(Window window) {
        this.window = window;
        window.getTitle();
    }
}


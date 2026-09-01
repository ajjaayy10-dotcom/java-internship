// Demonstrates Method Overloading
class Calculator {
    // Overloaded methods - same name, different parameters
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

// Demonstrates Method Overriding
class Animal {
    void sound() {
        System.out.println("The animal makes a sound.");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("The dog barks.");
    }
}

public class Practice5_OverloadingOverriding {
    public static void main(String[] args) {
        // Overloading demo
        Calculator calc = new Calculator();
        System.out.println("int add: " + calc.add(2, 3));
        System.out.println("double add: " + calc.add(2.5, 3.5));
        System.out.println("three int add: " + calc.add(1, 2, 3));

        // Overriding demo
        Animal a = new Animal();
        a.sound();

        Animal dog = new Dog(); // Runtime polymorphism
        dog.sound();
    }
}

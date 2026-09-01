// Base class
class Vehicle {
    String brand = "Generic Vehicle";

    void start() {
        System.out.println(brand + " is starting...");
    }

    void stop() {
        System.out.println(brand + " has stopped.");
    }
}

// Subclass 1
class Car extends Vehicle {
    Car() {
        brand = "Car";
    }

    @Override
    void start() {
        System.out.println(brand + " starts with a key/button ignition. Vroom!");
    }
}

// Subclass 2
class Bike extends Vehicle {
    Bike() {
        brand = "Bike";
    }

    @Override
    void start() {
        System.out.println(brand + " starts with a kick/self-start. Vroooom!");
    }
}

public class VehicleInheritanceDemo {
    public static void main(String[] args) {
        Vehicle v1 = new Car();
        Vehicle v2 = new Bike();

        v1.start();
        v1.stop();

        v2.start();
        v2.stop();
    }
}

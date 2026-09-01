class Vehicle {
    void drive() {
        System.out.println("The vehicle is moving.");
    }
}

class Car extends Vehicle {
    @Override
    void drive() {
        System.out.println("The car is driving on the road.");
    }
}

public class Practice3_VehicleCarInheritance {
    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        v.drive();

        Car c = new Car();
        c.drive();

        // Polymorphism: parent reference, child object
        Vehicle v2 = new Car();
        v2.drive();
    }
}

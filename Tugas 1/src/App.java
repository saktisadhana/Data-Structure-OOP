public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Car myCar = new Car();
        Car myHonda = new Car("Sedan", "Honda", "Blue", 120);
        System.out.println(myCar.getBrand());
        System.out.println(myHonda.getBrand());
    }
}

class Car {
    //atribut
    private String type;
    private String brand;
    private String color;
    private int speed;  
    //constructor
    public Car (){
        this.brand = "Default";
    }
    public Car(String type, String brand, String color, int speed) {
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.speed = speed;
    }
    public String getBrand() {
        return brand;
    }
}
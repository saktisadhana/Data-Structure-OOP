import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception //main method
    { 
    System.out.println("===== Assignment 1 =====\n$> Pick an option!");
    String brand, type, color; int speed, mileage;
    System.out.println("1. Create a car");
    System.out.println("2. Display car details");
    System.out.println("3. Exit");
    System.out.println("=======================\n>>");
    Scanner input = new Scanner(System.in);
    String inputUser = input.nextLine(); 
    Car myCar = null;
    switch (inputUser)
    {
        case "1":
            System.out.println("Enter car brand:");
            brand = input.nextLine();
            System.out.println("Enter car type:");
            type = input.nextLine();
            System.out.println("Enter car color:");
            color = input.nextLine();
            System.out.println("Enter car speed:");
            speed = input.nextInt();
            System.out.println("Enter car mileage:");
            mileage = input.nextInt();
            myCar = new GasCar(brand, type, color, speed, mileage);
            System.out.println("Car created successfully!");
            break;
        case "2":   
            System.out.println("Car details:");
            System.out.println("Brand: " + myCar.getBrand());
            System.out.println("Type: " + myCar.getType());
            System.out.println("Color: " + myCar.getColor());
            System.out.println("Speed: " + myCar.getSpeed() + " km/h");
            System.out.println("Mileage: " + myCar.getMileage() + " km");
            break;
        case "3":
            System.out.println("Exiting the program. Goodbye!");
            break;  
        default:
            System.out.println("Invalid option. Please try again.");
    }
    }
}
abstract class Car // class declaration
{
private String brand;
private String type;    
private String color;
private int speed;
private int mileage;
protected CashPayment cashPayment = new CashPayment();
//constructor
public Car(String brand, String type, String color, int speed, int mileage)
{
    this.brand = brand;
    this.type = type;
    this.color = color;
    this.speed = speed;
    this.mileage = mileage;
}
//method
abstract void startEngine();                            
//getter setterr speed
public int getSpeed()
{
    return speed;
}
public void setSpeed(int speedNew)
{
    this.speed = speedNew;
}       
//getter setter mileage
public int getMileage()
{
    return mileage;
}
public void setMileage(int mileageNew)
{
    this.mileage = mileageNew;
}
//getter setter brand
public String getBrand() 
{
    return brand;
}
public void setBrand(String brand) 
{
    this.brand = brand;
}
//getter setter type
public String getType() 
{
    return type;
}
public void setType(String type) 
{
    this.type = type;
}
// getter setter color
public String getColor() 
{
    return color;
}
public void setColor(String color) 
{
    this.color = color;
}

}
class GasCar extends Car 
{
    public GasCar (String brand, String type, String color, int speed, int mileage)
    {
        super (brand,type,color,speed,mileage);
    }
    @Override
    public void startEngine ()
    {
        System.out.println("I love gas");
    }
}
interface TollPayment 
{
    void payToll (int number);
}

class CashPayment implements TollPayment 
{
    @Override
    public void payToll (int number)
    {
        System.out.println("Pembayaran Cash" + number);
    }
}
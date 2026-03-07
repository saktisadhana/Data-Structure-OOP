public class App2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Maw");
        ElectricCar myCar = new ElectricCar("cacing", "pajero", "hitem", 0);
        System.out.println(myCar.getSpeed());
        myCar.startEngine();
    }
}
abstract class Car
{
    protected String brand;
    protected String type;
    protected String color;
    private int speed;
//constructor
    public Car(String brand, String type, String color, int speed)
    {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.speed = speed;
    }
//method
    abstract void  startEngine();
    public int getSpeed()
    {
        return speed;
    }
    public void setSpeed(int speedNew)
    {
        this.speed = speedNew;
    }
}

class ElectricCar extends Car
{
//constructor
    public ElectricCar (String brand, String type, String color, int speed)
    {
        super(brand, type, color, speed);
    }

    @Override
    void startEngine()
    {
        System.out.println ("maw");
    }
}
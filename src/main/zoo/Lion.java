package zoo;

public class Lion extends Animal implements Loggable,Printable {


    public void print(){
        System.out.println("printing....");

    }
    @Override
    public String message(){
        return "something";
    }

    @Override
    public void eat() {
    System.out.println("Lion is eating...");

    }
}

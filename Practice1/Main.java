public class Main {
    public static void main(String[] args) {
        Shape shape = new Shape(4, 5);
        Rectangle rect = new Rectangle(6, 3);
        Circle circle = new Circle(10);

        shape.display();
        System.out.println();
        rect.display();
        System.out.println();
        circle.display();
    }
}

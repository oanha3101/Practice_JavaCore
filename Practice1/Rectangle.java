public class Rectangle extends Shape {

    public Rectangle(double width, double height) {
        super(width, height);
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void display() {
        System.out.println("Rectangle: width = " + width + ", height = " + height);
        System.out.println("  Area      = " + getArea());
        System.out.println("  Perimeter = " + getPerimeter());
    }
}

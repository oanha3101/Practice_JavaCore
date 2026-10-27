public class Circle extends Shape {

    // Hinh tron: width = height = duong kinh
    public Circle(double diameter) {
        super(diameter, diameter);
    }

    public double getRadius() {
        return width / 2;
    }

    public double getArea() {
        double r = getRadius();
        return Math.PI * r * r;
    }

    public double getCircumference() {
        return width * 3.14; // d * 3.14 theo de
    }

    @Override
    public void display() {
        System.out.println("Circle: diameter = " + width + ", radius = " + getRadius());
        System.out.println("  Area          = " + getArea());
        System.out.println("  Circumference = " + getCircumference());
    }
}

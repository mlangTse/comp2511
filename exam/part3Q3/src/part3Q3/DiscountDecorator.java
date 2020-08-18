package part3Q3;

public class DiscountDecorator extends Decorator{
    private int discount;

    public DiscountDecorator(Product product, int discount) {
        super(product);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return (super.getPrice() * (100 - discount) /100);
    }
}

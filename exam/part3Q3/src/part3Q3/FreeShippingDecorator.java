package part3Q3;

public class FreeShippingDecorator extends Decorator{
    private double price;
    private double weight;

    public FreeShippingDecorator(Product product, double price, double weight) {
        super(product);
        this.price = price;
        this.weight = weight;
    }

    @Override
    public double getShippingCost() {
        if (weight > super.getWeight() && price < super.getPrice()) return 0;
        return super.getShippingCost();
    }
}

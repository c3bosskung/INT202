package sit.int202.classsicmodels.models;

public interface CartItem {
    public int getQuantity();

    public void setQuantity(int quantity);

    public double getUnitPrice();

    public double getTotal();

    public double getPercentDiscount();
}

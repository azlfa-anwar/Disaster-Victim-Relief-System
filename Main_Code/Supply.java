package edu.ucalgary.oop;

public class Supply {
    private String type;
    private int quantity;

    public Supply(String type, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.type = type;
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    //getter method for type of supply

    public String getType() {
        return this.type;
    }

    //getter method for quantity

    public int getQuantity() {
        return this.quantity;
    }
}

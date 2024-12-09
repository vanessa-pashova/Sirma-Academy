package models.interfaces;

public interface Item {
    String getName();
    String getItemDetails();        //Properties of the product
    String getItemDescription();    //ex. Purposes, Target Market
    double getPrice();

    void setName(String name);
    void setItemDetails(String itemDetails);
    void setItemDescription(String itemDescription);
    void setPrice(double price);

    double calculatePrice();        //Calculation for the price due to possible discounts
//    void printDescription();
}
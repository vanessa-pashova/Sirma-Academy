package interfacees;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class AbstractItem implements Item, Categorizable, Fragile, Perishable, Sellable, Discount {
    private String itemName;
    private double itemPrice;
    private CategorizableType category;

    private double itemDiscount;

    private boolean fragile;
    private String creationDate, expirationDate;
    private boolean sellable;

    private String itemDetails;
    private String itemDescription;

    //Getters
    @Override
    public String getName() {
        return this.itemName;
    }

    @Override
    public double getPrice() {
        return this.itemPrice;
    }

    @Override
    public CategorizableType getCategory() {
        return this.category;
    }

    @Override
    public double getDiscount() {
        return this.itemDiscount;
    }

    @Override
    public boolean isFragile() {
        return this.fragile;
    }

    @Override
    public String getCreationDate() {
        return this.creationDate;
    }

    @Override
    public String getExpiryDate() {
        return this.expirationDate;
    }

    @Override
    public boolean isSellable() {
        return this.sellable;
    }

    @Override
    public String getItemDetails() {
        return this.itemDetails;
    }

    @Override
    public String getItemDescription() {
        return this.itemDescription;
    }

    //Setters
    @Override
    public void setName(String itemName) {
        if(itemName == null || itemName.isEmpty())
            throw new IllegalStateException(">! Name cannot be empty [AbstactItem, setName()].");

        this.itemName = Character.toUpperCase(itemName.charAt(0)) + itemName.substring(1);
    }

    @Override
    public void setPrice(double itemPrice) {
        if(itemPrice < 0.10)
            throw new IllegalStateException(">! Price cannot be less than 0.10 [AbstactItem, setPrice()].");

        if(itemDiscount == 0) {
//            System.out.println("------- NO ITEM DISCOUNT APPLIED ------");
            this.itemPrice = itemPrice;
        }

        else {
            System.out.println("------- ITEM DISCOUNT APPLIED ------");
            this.itemPrice = calculatePrice();
        }
    }

    @Override
    public void setCategory(CategorizableType category) {
        if(category == null)
            throw new IllegalStateException(">! Category cannot be empty [AbstactItem, setCategory()].");

        this.category = category;
    }

    @Override
    public void setDiscount(double discount) {
        if(discount <= 0){
            this.itemDiscount = 0;
            return;
        }

        if(discount >= 1) {
            this.itemDiscount = 0;
            throw new IllegalStateException(">! Discount cannot be greater than 100%. [AbstactItem, setDiscount()].");
        }

        this.itemDiscount = discount;
    }

    @Override
    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    @Override
    public void setExpiry(String creationDate, String expirationDate) {
        if((creationDate == null || creationDate.isEmpty()) || (expirationDate == null || expirationDate.isEmpty()))
            throw new IllegalStateException(">! Date cannot be empty [AbstactItem, setExpiry()].");

        String format = "dd-MM-yyyy";                                           //format for the dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);      //creating the formatter

        LocalDate creation = null, expiration = null;
        try {
            creation = LocalDate.parse(creationDate, formatter);
            expiration = LocalDate.parse(expirationDate, formatter);
        } catch (DateTimeException e) {
            System.out.println(">! Invalid format [AbstactItem, setExpiry()].");
            System.out.println(e.getMessage());
            return;
        }

        if(creation.isAfter(expiration))
            throw new DateTimeException(">! Expiry date is before the one of the creation [AbstractItem, setExpiry()].");

        this.creationDate = creation.toString();
        this.expirationDate = expiration.toString();
    }

    @Override
    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    @Override
    public void setItemDetails(String itemDetails) {
        if(itemDetails == null || itemDetails.isEmpty())
            this.itemDetails = "[ No details for this item were provided ]\n";

        this.itemDetails = itemDetails.toUpperCase();
    }

    @Override
    public void setItemDescription(String itemDescription) {
        if(itemDescription == null || itemDescription.isEmpty())
            this.itemDescription = "[ No details for this item were provided ]\n";

        this.itemDescription = itemDescription.toUpperCase();
    }

    @Override
    public double calculatePrice() {
        return this.itemPrice -= (this.itemPrice * itemDiscount);
    }

    public AbstractItem(String name, double price, CategorizableType category) {
        this.setName(name);
        this.setPrice(price);
        this.setCategory(category);
    }

    public AbstractItem() {
        this.itemName = null;
        this.itemPrice = 0;
        this.category = null;
    }
}
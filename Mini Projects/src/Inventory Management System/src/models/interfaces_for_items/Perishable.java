package models.interfaces_for_items;

public interface Perishable {
//    boolean isExpired();
    String getCreationDate();
    String getExpiryDate();
    void setExpiry(String creationDate, String expiryDate);
}

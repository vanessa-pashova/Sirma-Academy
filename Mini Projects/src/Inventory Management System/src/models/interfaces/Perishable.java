package models.interfaces;

public interface Perishable {
//    boolean isExpired();
    String getCreationDate();
    String getExpiryDate();
    void setExpiry(String creationDate, String expiryDate);
}
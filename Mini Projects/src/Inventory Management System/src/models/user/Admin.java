package models.user;

public class Admin extends AbstractUser {
    public Admin(String firstName, String familyName, String email, String password, String role) {
        super(firstName, familyName, email, password, role);
    }

    public void updateInventory() {
        inventory.updateInventory();
    }

    public void clearAllItems() {
        this.getInventory().clearInventory();
    }
}

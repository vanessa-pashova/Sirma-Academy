package models.user;

public interface Role {
    enum RoleType {
        CUSTOMER,
        ADMIN
    }

    public String getRole();
    public void setRole(String role);
}

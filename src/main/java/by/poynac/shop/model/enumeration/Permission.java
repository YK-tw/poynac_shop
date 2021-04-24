package by.poynac.shop.model.enumeration;

public enum Permission {

    PERMISSION_READ("permission:read"),
    PERMISSION_WRITE("permission:write");

    private final String permission;

    private Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}

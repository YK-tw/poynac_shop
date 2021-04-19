package by.poynac.shop.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {

    PERMISSION_READ("permission:read"),
    PERMISSION_WRITE("permission:write");

    private final String permission;

}

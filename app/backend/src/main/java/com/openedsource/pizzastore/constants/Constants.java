package com.openedsource.pizzastore.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Constants {

    @AllArgsConstructor
    @Getter
    public enum MessageString{
        PARAMETER_ERROR("Invalid Parameter"),

        CONFLICT_ERROR("It has already been registered"),

        AUTH_ENTRY_ERROR("No register permission"),

        AUTH_UPDATE_ERROR("No update permission"),

        AUTH_DELETE_ERROR("No delete permission");

        private final String message;
    }
}

package qaappium.mobileEnums;

import core.enums.BaseEnum;

public enum HomePills implements BaseEnum {

    Sellings("SELLINGS"),
    Deals("DEALS"),
    Categories("CATEGORIES"),
    Saved("SAVED");

    private String value;

    HomePills(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.getValue();
    }

    public HomePills getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException("Provide correct value for Enum");
        for(HomePills v : values())
            if(value.equalsIgnoreCase(v.getValue())) return v;
        throw new IllegalArgumentException("Provide correct value for Enum");
    }

}

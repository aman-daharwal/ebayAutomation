package qaappium.mobileEnums;

import core.enums.BaseEnum;

public enum EbaySideBarItems implements BaseEnum {

    Home("Home"),
    Notification("Notification"),
    Messages("Messages"),
    Watching("Watching"),
    Purchased("Purchased"),
    BidsNOffers("Bids & Offers"),
    Settings("Settings"),
    HelpNContact("Help & Contact"),
    Sellings("Sellings"),
    Deals("Deals"),
    Categories("Categories"),
    Saved("Saved"),
    SignIn("SIGN IN");

    private String value;

    EbaySideBarItems(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.getValue();
    }

    public EbaySideBarItems getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException("Provide correct value for Enum");
        for(EbaySideBarItems v : values())
            if(value.equalsIgnoreCase(v.getValue())) return v;
        throw new IllegalArgumentException("Provide correct value for Enum");
    }
}

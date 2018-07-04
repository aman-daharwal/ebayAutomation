package core.enums;

public interface BaseEnum<T extends BaseEnum<T>> {

    public String getValue();
    @Override
    public String toString();
    public T getEnum(String value);

}

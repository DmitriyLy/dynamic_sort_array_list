package dmly.sorting.utils;

import dmly.sorting.model.SalesOrder;

import java.lang.reflect.Field;

public class SortingParam {
    private final String filedName;
    private final SortingDirection direction;

    public SortingParam(String filedName, SortingDirection direction) {
        this.filedName = filedName;
        this.direction = direction;
    }

    public String getFiledName() {
        return filedName;
    }

    public SortingDirection getDirection() {
        return direction;
    }

    public int compare(SalesOrder salesOrder1, SalesOrder salesOrder2) {

        try {
            Field field = SalesOrder.class.getDeclaredField(filedName);
            field.setAccessible(true);

            Comparable value1 = (Comparable) field.get(salesOrder1);
            Comparable value2 = (Comparable) field.get(salesOrder2);

            if (direction == SortingDirection.DESC) {
                return value2.compareTo(value1);
            } else {
                return value1.compareTo(value2);
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}

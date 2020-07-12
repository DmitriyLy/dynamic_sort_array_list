package dmly.sorting.model;

import java.math.BigInteger;
import java.util.Objects;

public class SalesOrder {

    private final BigInteger id;
    private final String status;
    private final String marketName;

    public SalesOrder(BigInteger id, String status, String marketName) {
        this.id = id;
        this.status = status;
        this.marketName = marketName;
    }

    public BigInteger getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getMarketName() {
        return marketName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesOrder that = (SalesOrder) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SalesOrder{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", marketName='" + marketName + '\'' +
                '}';
    }
}

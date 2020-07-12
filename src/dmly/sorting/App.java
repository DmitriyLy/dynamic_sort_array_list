package dmly.sorting;

import dmly.sorting.model.SalesOrder;
import dmly.sorting.utils.SortingDirection;
import dmly.sorting.utils.SortingParam;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

public class App {

    private static final String[] statuses = {"Entering", "Processing", "Submitted"};
    private static final String[] markets = {"Japan", "USA", "Canada", "UK", "Germany"};

    public static void main(String[] args) {

        List<SalesOrder> sourceSalesOrders = getSourceList();

        printSalesOrdersList(sourceSalesOrders, "Source List");

        List<SalesOrder> sortedById = new ArrayList<>(sourceSalesOrders);
        sortList(sortedById, Collections.emptyList());
        printSalesOrdersList(sortedById, "Sorted By ID");

        List<SortingParam> sortingParams = new ArrayList<>();
        sortingParams.add(new SortingParam("marketName", SortingDirection.DESC));

        List<SalesOrder> sortedByMarketNameAndId = new ArrayList<>(sourceSalesOrders);
        sortList(sortedByMarketNameAndId, sortingParams);
        printSalesOrdersList(sortedByMarketNameAndId, "Sorted By Market Name and ID");

        sortingParams.add(new SortingParam("status", SortingDirection.ASC));
        List<SalesOrder> sortedByMarketNameAndByStatusAndId = new ArrayList<>(sourceSalesOrders);
        sortList(sortedByMarketNameAndByStatusAndId, sortingParams);
        printSalesOrdersList(sortedByMarketNameAndByStatusAndId, "Sorted By Market Name and by Status and ID");
    }


    private static BigInteger getRandomBigInt() {

        BigInteger maxLimit = new BigInteger("5000000000000");
        BigInteger minLimit = new BigInteger("2500000000000");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);

        return res;

    }

    private static SalesOrder getSalesOrder() {
        return new SalesOrder(
                getRandomBigInt(),
                getRandomStatus(),
                getRandomMarket()
        );
    }

    private static int getRandomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static String getRandomStatus() {
        return statuses[getRandomInt(0, statuses.length - 1)];
    }

    private static String getRandomMarket() {
        return markets[getRandomInt(0, markets.length - 1)];
    }

    private static void printSalesOrdersList(final List<SalesOrder> salesOrders, final String header) {
        System.out.println("=============================================================================");
        System.out.println("~~~~~~~" + header + "~~~~~~~" + "\n");
        salesOrders.forEach(System.out::println);
        System.out.println("=============================================================================");
    }

    private static List<SalesOrder> getSourceList() {
        List<SalesOrder> sourceSalesOrders = new ArrayList<>();

        Stream.iterate(0, n -> n + 1)
                .limit(20)
                .forEach(x -> sourceSalesOrders.add(getSalesOrder()));

        return sourceSalesOrders;
    }


    private static void sortList(List<SalesOrder> salesOrders, List<SortingParam> sortingParams) {
        Collections.sort(salesOrders, (s1, s2) -> {
            for (SortingParam sortingParam: sortingParams) {
                int compareResult = sortingParam.compare(s1, s2);

                if (compareResult != 0) {
                    return compareResult;
                }
            }

            return s2.getId().compareTo(s1.getId());
        });
    }


}

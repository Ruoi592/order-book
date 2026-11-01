public class Main {
    public static void main(String[] args) {
        Order buy = new Order(1, Order.Side.BUY, 5025, 100, 1);
        Order sell = new Order(2, Order.Side.SELL, 5000, 40, 2);
        System.out.println(buy);
        System.out.println(sell);

        try {
            new Order(3, Order.Side.BUY, 5025, 0, 3);
            System.out.println("ERROR: should have thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK, exception: " + e.getMessage());
        }

        System.out.println("--- OrderBook tests ---");
        OrderBook book = new OrderBook();
        //first case
        book.addOrder(new Order(1, Order.Side.BUY, 5025, 100, 1));
        book.addOrder(new Order(2, Order.Side.SELL, 5000, 40, 2));

        //second case
        book.addOrder(new Order(3, Order.Side.SELL, 5100, 10, 3));

        //third case
        book.addOrder(new Order(4, Order.Side.SELL, 5030, 30, 4));
        book.addOrder(new Order(5, Order.Side.SELL, 5040, 30, 5));
        book.addOrder(new Order(6, Order.Side.BUY, 5040, 50, 6));
    }
}

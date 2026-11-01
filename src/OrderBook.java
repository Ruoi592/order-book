import java.util.ArrayList;
public class OrderBook {
    private final ArrayList<Order> bids = new ArrayList<>(); 
    private final ArrayList<Order> asks = new ArrayList<>(); 

    public void addOrder(Order incoming) {
        while (incoming.getQuantity() > 0) {
            Order best = findBestMatch(incoming);
            if (best == null) {
                break;
            }

            long qty = Math.min(incoming.getQuantity(), best.getQuantity());
            System.out.println("TRADE " + qty + " @ " + best.getPrice());
            incoming.reduceQuantity(qty);
            best.reduceQuantity(qty);

            if (best.getQuantity() == 0) {
                if (best.getSide() == Order.Side.BUY) {
                    bids.remove(best);
                } else {
                    asks.remove(best);
                }
            }
        }
        if (incoming.getQuantity() > 0) {
            if (incoming.getSide() == Order.Side.BUY) {
                bids.add(incoming);
            } else {
                asks.add(incoming);
            }
        }
    }

    public Order findBestMatch(Order incoming) {
        Order best = null;
        if (incoming.getSide() == Order.Side.BUY) {
            for (Order o : asks) {
                if (o.getPrice() <= incoming.getPrice()) {
                    if (best == null || o.getPrice() < best.getPrice() || (o.getPrice() == best.getPrice() && o.getSequence() < best.getSequence())) {
                        best = o;
                    }
                }
            }
        } else {
            for (Order o : bids) {
                if (o.getPrice() >= incoming.getPrice()) {
                    if (best == null || o.getPrice() > best.getPrice() || (o.getPrice() == best.getPrice() && o.getSequence() < best.getSequence())) {
                        best = o;
                    }
                }
            }
        }
        return best;
    }
}

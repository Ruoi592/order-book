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

    public boolean cancelOrder(long id) {
        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).getId() == id) {
                bids.remove(i);
                return true;
            }
        }

        for (int i = 0; i < asks.size(); i++) {
            if (asks.get(i).getId() == id) {
                asks.remove(i);
                return true;
            }
        }

        return false;
    }

    public void printBook() {
        ArrayList<Order> sortedBids = new ArrayList<>(bids);
        ArrayList<Order> sortedAsks = new ArrayList<>(asks);

        sortedAsks.sort((a, b) -> {
            if (a.getPrice() != b.getPrice()) {
                return Long.compare(a.getPrice(), b.getPrice());
            }

            return Long.compare(a.getSequence(), b.getSequence());
        });

        sortedBids.sort((a,b) -> {
            if (a.getPrice() != b.getPrice()) {
                return Long.compare(b.getPrice(), a.getPrice());
            }

            return Long.compare(a.getSequence(), b.getSequence());
        });

        System.out.println("=== ASKS ===");
        for (Order o : sortedAsks) {
            System.out.println(o.getId() + " " + o.getPrice()+ " " + o.getQuantity());
        }
        System.out.println("=== BIDS ===");
        for (Order o : sortedBids) {
            System.out.println(o.getId() + " " + o.getPrice() + " " + o.getQuantity());
        }
    }
}

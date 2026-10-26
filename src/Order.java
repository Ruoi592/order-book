public class Order {
    public enum Side { BUY, SELL}

    private final long id;
    private final Side side;
    private final long price;
    private long quantity;
    private final long sequence;

    public Order(long id, Side side, long price, long quantity, long sequence) {
        if (price <= 0 || quantity <= 0) {
            throw new IllegalArgumentException("price and quantity must be positive");
        }
        this.id = id;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.sequence = sequence;
    }

    public long getId() {return id; }
    public Side getSide() {return side;}
    public long getPrice() {return price;} 
    public long getQuantity() {return quantity;}
    public long getSequence() {return sequence;}

    public void reduceQuantity(long amount) {quantity -= amount;}

    @Override public String toString() {
        return side + " " + quantity + " @ " + price +" (id =" + id + ")";
    }
}
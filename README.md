# order-book

Order book / matching engine. Fall: Java. Winter break: rewrite in C++.

## Log

- 2026-10-25: Order class done; prices stored as long cents to avoid floating-point error.
- 2026-10-31: MyArrayList (dynamic array from scratch, add amortized O(1)) in datastructures/arraylist
- 2026-11-01: OrderBook with price-time matching (linear scan); tested partial fill and multi-level fill.
- 2026-11-21: OrderBook with cancelOrder and printBook; tested cancel of bid, ask, already-filled and unknown id.
- 2026-11-22: MyLinkedList (doubly linked, int) in datastructures/linkedlist; fuzz-tested against java.util.LinkedList (2,000 random sequences). removeFirst is O(1): 400,000 removals took under 1 ms vs 6,776 ms for ArrayList.remove(0). Codeforces part skipped this session.

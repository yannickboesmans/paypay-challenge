# Immutable Queue

## Language choice
I chose to implement the immutable Queue in Java because I haven't used scala much so far. Most of my experience with functional programming comes from Haskell. However, with strong knowledge in both Java and Haskell, I think I'm ideally equipped to learn Scala in a short timeframe.

## Implementation
I followed a traditional pattern for implementing an immutable queue which uses 2 stacks to keep incoming messages and outgoing messages.
Enqueued messages are added to the incoming stack and dequeued messages are taken from the outgoing queue. If the outgoing queue is empty, the incoming messages are "flipped" onto the outgoing queue. This preserves the FIFO nature of the queue while avoiding having to copy an entire list every time a new item is enqueued.

The only deviation I made from a traditional implementation is when to apply the flipping operation. Traditionally, this is done when dequeuing an item. However, because in the provided interface the `head()` and `deQueue()` methods are separated, this would lead to a lot of double flips (when `head()` and `dequeue()` are called in succession). 
To avoid this I moved the flipping operation to the Queue constructor.

## Tests
I provided a small test suite for the `ImmutableQueue` class, but not for the `ImmutableStack` class. This was done in the interest of time. In a real project I would strive to write a comprehensive test suite for both classes if time constraints allow it.

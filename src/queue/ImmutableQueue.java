package queue;

public class ImmutableQueue<T> implements Queue<T> {

    private final ImmutableStack<T> incoming;
    private final ImmutableStack<T> outgoing;

    // private because new queues should be created with empty() and enQueue()
    private ImmutableQueue(ImmutableStack<T> incoming,
                           ImmutableStack<T> outgoing) {

        // We flip the incoming stack onto the outgoing if we have
        // no more items in the outgoing stack.
        // This is done in the constructor instead of deQueue/head to
        // avoid a double flip in the common case where somebody calls
        // first head() and then deQueue()
        if (outgoing.isEmpty() && !incoming.isEmpty()) {
            this.incoming = ImmutableStack.empty();
            this.outgoing = incoming.flip();
        } else {
            this.incoming = incoming;
            this.outgoing = outgoing;
        }
    }

    static <TNew> Queue<TNew> empty() {
        return new ImmutableQueue<>(ImmutableStack.empty(), ImmutableStack.empty());
    }

    @Override
    public Queue<T> enQueue(T value) {
        return new ImmutableQueue<>(incoming.push(value), outgoing);
    }

    @Override
    public Queue<T> deQueue() {
        if (!outgoing.isEmpty()) {
            return new ImmutableQueue<>(incoming, outgoing.pop());
        } else {
            // If outgoing is empty, incoming is also empty
            return this;
        }
    }

    @Override
    public T head() {
        if (!outgoing.isEmpty()) {
            return outgoing.peek();
        } else {
            // If outgoing is empty, incoming is also empty
            throw new IllegalStateException("Queue is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return incoming.isEmpty() && outgoing.isEmpty();
    }
}



package queue;

/**
 * Immutable stack with support for reversing (flip)
 */
class ImmutableStack<T> {

    private final StackItem top;

    // Stacks should be created using empty() and push()
    private ImmutableStack(StackItem head) {
        this.top = head;
    }

    static <TNew> ImmutableStack<TNew> empty() {
        return new ImmutableStack<>(null);
    }

    ImmutableStack<T> push(T value) {
        StackItem newHead = new StackItem(value, top);
        return new ImmutableStack<>(newHead);
    }

    // Because of lack of tuple support in Java, it's easiest to just
    // return the new stack and move responsibility to the user to peek
    // before calling pop
    ImmutableStack<T> pop() {
        return new ImmutableStack<>(top.next);
    }

    T peek() {
        return top.value;
    }

    ImmutableStack<T> flip() {
        ImmutableStack<T> original = this;
        ImmutableStack<T> reversed = empty();

        while(!original.isEmpty()) {
            reversed = reversed.push(original.peek());
            original = original.pop();
        }

        return reversed;
    }

    boolean isEmpty() {
        return top == null;
    }

    private class StackItem {

        private final T value;
        private final StackItem next;

        StackItem(T value, StackItem next) {
            this.value = value;
            this.next = next;

        }
    }
}

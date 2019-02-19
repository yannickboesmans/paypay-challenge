package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableQueueTest {

    private <T> Queue<T> createQueue(T... values) {
        Queue<T> queue = ImmutableQueue.empty();

        for (T value : values) {
            queue = queue.enQueue(value);
        }

        return queue;
    }

    @Test
    void isEmpty_emptyQueue_true() {
        assertTrue(createQueue().isEmpty());
    }

    @Test
    void isEmpty_nonEmptyQueue_false() {
        assertFalse(createQueue(1,2,3).isEmpty());
    }

    @Test
    void isEmpty_afterDequeueOne_false() {
        Queue<Integer> queue =
                createQueue(1, 2, 3)
                        .deQueue()
                        .deQueue();

        assertFalse(queue.isEmpty());
    }

    @Test
    void isEmpty_afterFullDequeue_true() {
        Queue<Integer> queue =
                createQueue(1, 2, 3)
                    .deQueue()
                    .deQueue()
                    .deQueue();

        assertTrue(queue.isEmpty());
    }

    @Test
    void enqueue_base_dequeuesInOrder() {
        Queue<Integer> queue = createQueue(1,2,3);

        assertEquals(1, queue.head());

        queue = queue.deQueue();
        assertEquals(2, queue.head());

        queue = queue.deQueue();
        assertEquals(3, queue.head());
    }

    @Test
    void enqueue_base_immutable() {
        Queue<Integer> queue = createQueue(1);

        queue.enQueue(2);

        assertEquals(1, queue.head());
        assertTrue(queue.deQueue().isEmpty());
    }

    @Test
    void dequeue_base_immutable() {
        Queue<Integer> queue = createQueue(1);

        assertEquals(1, queue.head());

        queue.deQueue();

        assertEquals(1, queue.head());
    }

    @Test
    void enqueueDequeue_interleaved_works() {
        Queue<Integer> queue = createQueue();

        queue = queue.enQueue(1).enQueue(2);
        assertEquals(1, queue.head());

        queue = queue.deQueue();
        assertEquals(2, queue.head());

        queue = queue.enQueue(3).enQueue(4);
        assertEquals(2, queue.head());

        queue = queue.deQueue();
        assertEquals(3, queue.head());
    }

    @Test
    void head_emptyQueue_throwsIllegalStateException() {
        assertThrows(IllegalStateException.class,
                () -> createQueue().head());
    }
}

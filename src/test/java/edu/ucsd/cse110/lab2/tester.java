package edu.ucsd.cse110.lab2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertThrows;

public class tester {
    private Animal animal1;
    private Animal animal2;
    
    @BeforeEach
    void setUp() {
        animal1 = new Cat();
        animal2 = new Dog();
    }
    
    @Test
    void testGetName() {
        animal1.setName();
        animal2.setName();
        assertEquals("cat", animal1.getName());
        assertEquals("dog", animal2.getName());
    }
    
    // @Test
    // void testDequeue() {
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     assertEquals(1, queue.dequeue());
    //     assertEquals(2, queue.dequeue());
    //     assertEquals(1, queue.size());
    // }
    
    // @Test
    // void testPeek() {
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     assertEquals(1, queue.peek());
    //     assertEquals(3, queue.size());
    // }
    
    // @Test
    // void testIsEmpty() {
    //     assertTrue(queue.isEmpty());
    //     queue.enqueue(1);
    //     assertFalse(queue.isEmpty());
    // }
    
    // @Test
    // void testIsFull() {
    //     assertFalse(queue.isFull());
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     queue.enqueue(4);
    //     queue.enqueue(5);
    //     assertTrue(queue.isFull());
    // }
    
    // @Test
    // void testEnqueueWhenFull() {
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     queue.enqueue(4);
    //     queue.enqueue(5);
    //     assertThrows(IllegalStateException.class, () -> queue.enqueue(6));
    // }
    
    // @Test
    // void testDequeueWhenEmpty() {
    //     assertThrows(IllegalStateException.class, () -> queue.dequeue());
    // }
}

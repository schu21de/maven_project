package de.hfu;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test(expected = IllegalArgumentException.class)
    public void istErstesHalbjahrTest() {
        assertTrue(Util.istErstesHalbjahr(1));
        assertTrue(Util.istErstesHalbjahr(6));
        assertFalse(Util.istErstesHalbjahr(7));
        assertFalse(Util.istErstesHalbjahr(12));

        Util.istErstesHalbjahr(-1);
        Util.istErstesHalbjahr(13);
        /*try {
            Util.istErstesHalbjahr(-1);
            fail();
        } catch (Exception e) { }
        try {
            Util.istErstesHalbjahr(13);
            fail();
        } catch (Exception e) { }*/
    }

    @Test
    public void queueTest() {
        try {
            Queue queue = new Queue(0);
            fail();
        } catch (Exception e) { }

        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 2);
        assertEquals(queue.dequeue(), 4);

        try {
            queue.dequeue();
            fail();
        } catch (Exception e) { }
    }
}

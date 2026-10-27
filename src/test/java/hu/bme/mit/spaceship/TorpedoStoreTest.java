package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Multiple_Success() {
        TorpedoStore store = new TorpedoStore(10, 0.0);

        boolean result = store.fire(4);

        assertTrue(result);
        assertEquals(6, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_FailureRateIsOne() {
        TorpedoStore store = new TorpedoStore(5, 1.0);

        boolean result = store.fire(2);

        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_FireZeroNegative() {
        TorpedoStore store = new TorpedoStore(5);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_MoreThanAvailable() {
        TorpedoStore store = new TorpedoStore(5);

        assertThrows(IllegalArgumentException.class, () -> store.fire(6));
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void isEmptyStates() {
        TorpedoStore emptyStore = new TorpedoStore(0);
        TorpedoStore nonEmptyStore = new TorpedoStore(1);

        assertTrue(emptyStore.isEmpty());
        assertEquals(0, emptyStore.getTorpedoCount());
        assertFalse(nonEmptyStore.isEmpty());
        assertEquals(1, nonEmptyStore.getTorpedoCount());
    }
}

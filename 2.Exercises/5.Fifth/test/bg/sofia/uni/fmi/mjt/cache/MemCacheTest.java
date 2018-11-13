package bg.sofia.uni.fmi.mjt.cache;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemCacheTest {

    private Cache<String, Integer> cache;

    @Before
    public void setup() {
        cache = new MemCache<>();
    }

    @Test
    public void getExpiration_Null_KeyMissing() {
        assertNull(cache.getExpiration("asd"));
    }

    @Test
    public void getExpiration_NotNull_KeyExisting() throws CapacityExceededException {
        String key = "key";
        Integer value = 1;
        LocalDateTime date = LocalDateTime.of(2020, 12, 12, 12, 12);

        cache.set(key, value, date);

        assertEquals(date, cache.getExpiration(key));
    }

    @Test
    public void remove_False_KeyNotExisting() {
        assertFalse(cache.remove("non existing"));
    }

    @Test
    public void remove_True_KeyExisting() throws CapacityExceededException {
        String key = "key";
        Integer value = 1;
        LocalDateTime date = LocalDateTime.of(2020, 12, 12, 12, 12);

        cache.set(key, value, date);

        assertTrue(cache.remove(key));

        assertFalse(cache.remove(key));
    }

    @Test
    public void size_Correct_KeysAdded() throws CapacityExceededException {
        int numberOfKeysAdded = 10;

        addKeysToCacheFutureDate(numberOfKeysAdded);

        assertEquals(numberOfKeysAdded, cache.size());
    }

    @Test
    public void clear_ClearsAllKey_KeysAdded() throws CapacityExceededException {
        String[] keys = addKeysToCacheFutureDate(10);

        cache.clear();

        for (String key : keys) {
            assertFalse(cache.remove(key));
            assertNull(cache.getExpiration(key));
            assertNull(cache.getExpiration(key));
        }

        assertEquals(0, cache.size());
    }

    private String[] addKeysToCacheFutureDate(int n) throws CapacityExceededException {
        String[] keys = new String[n];
        Integer[] values = new Integer[n];
        LocalDateTime[] dates = new LocalDateTime[n];

        for (int i = 0; i < n; i++) {
            keys[i] = "key" + i;
            values[i] = i + 1;
            dates[i] = LocalDateTime.of(2020, 12, 12, 12, 12);
            cache.set(keys[i], values[i], dates[i]);
        }

        return keys;
    }
}

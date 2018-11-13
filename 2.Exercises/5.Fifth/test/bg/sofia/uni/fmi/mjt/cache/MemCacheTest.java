package bg.sofia.uni.fmi.mjt.cache;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemCacheTest {
    private static final int CACHE_MAXIMUM_CAPACITY = 1000;

    private Cache<String, Integer> cache;

    @Before
    public void setup() {
        cache = new MemCache<>(CACHE_MAXIMUM_CAPACITY);
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

        addKeysToCacheNotExpiredDate(numberOfKeysAdded, "key");

        assertEquals(numberOfKeysAdded, cache.size());
    }

    @Test
    public void clear_ClearsAllKey_KeysAdded() throws CapacityExceededException {
        String[] keys = addKeysToCacheNotExpiredDate(10, "key");

        cache.clear();

        for (String key : keys) {
            assertFalse(cache.remove(key));
            assertNull(cache.getExpiration(key));
            assertNull(cache.getExpiration(key));
        }

        assertEquals(0, cache.size());
    }

    @Test(expected = CapacityExceededException.class)
    public void set_CapacityExceededException_CacheFull() throws CapacityExceededException {
        String[] keys = addKeysToCacheNotExpiredDate(CACHE_MAXIMUM_CAPACITY, "key");

        addKeysToCacheNotExpiredDate(2, "keys");
    }

    @Test
    public void set_KeyUpdated_CapacityExceeded() throws CapacityExceededException {
        String base = "key";

        String[] keys = addKeysToCacheNotExpiredDate(CACHE_MAXIMUM_CAPACITY, base);

        addKeysToCacheNotExpiredDate(CACHE_MAXIMUM_CAPACITY, base);

        for (String key : keys) {
            assertNotNull(cache.get(key));
        }
    }

    @Test
    public void getHitRate_NoHitsMade() throws CapacityExceededException {
        String base = "key";

        addKeysToCacheNotExpiredDate(100, base);

        assertEquals(0.0, cache.getHitRate(), 0.0);
    }

    @Test
    public void getHitRate_AllHitsNotValid() throws CapacityExceededException {
        String base = "key";

        String[] keys = addKeysToCacheNotExpiredDate(10, base);

        for (String key : keys) {
            cache.get(key + "asd");
        }

        assertEquals(0.0, cache.getHitRate(), 0.00);
    }


    @Test
    public void getHitRate_AllHitsValid() throws CapacityExceededException {
        String base = "key";

        String[] keys = addKeysToCacheNotExpiredDate(10, base);

        for (String key : keys) {
            cache.get(key);
        }

        assertEquals(1, cache.getHitRate(), 0.00);
    }

    @Test
    public void getHitRate_HalfHitsValid() throws CapacityExceededException {
        String base1 = "key";

        int elements = 20;

        String[] keys = addKeysToCacheNotExpiredDate(elements / 2, base1);

        for (String key : keys) {
            cache.get(key);
        }

        String base2 = "invalidKeys";
        String[] keys2 = addKeysToCacheExpiredDate(elements / 2, base2);

        for (String key : keys2) {
            cache.get(key);
        }

        assertEquals(0.5, cache.getHitRate(), 0.00);

        assertEquals(elements / 2, cache.size());
    }

    @Test
    public void set_FullCacheOfExpired_RemovedElement() throws CapacityExceededException {
        String base1 = "key";

        addKeysToCacheExpiredDate(CACHE_MAXIMUM_CAPACITY, base1);

        String base2 = "valid";

        addKeysToCacheNotExpiredDate(2, base2);

        assertNotNull(cache.get("valid1"));
    }

    @Test
    public void set_KeyUpdated_CapacityExceeded_ExpiredDates() throws CapacityExceededException {
        String base = "key";

        addKeysToCacheExpiredDate(CACHE_MAXIMUM_CAPACITY, base);
        addKeysToCacheNotExpiredDate(CACHE_MAXIMUM_CAPACITY, base);
    }

    private String[] addKeysToCacheNotExpiredDate(int n, String keyBase) throws CapacityExceededException {
        String[] keys = new String[n];
        Integer[] values = new Integer[n];
        LocalDateTime[] dates = new LocalDateTime[n];

        for (int i = 0; i < n; i++) {
            keys[i] = keyBase + i;
            values[i] = i + 1;
            dates[i] = LocalDateTime.of(2020, 12, 12, 12, 12);
            cache.set(keys[i], values[i], dates[i]);
        }

        return keys;
    }

    private String[] addKeysToCacheExpiredDate(int n, String keyBase) throws CapacityExceededException {
        String[] keys = new String[n];
        Integer[] values = new Integer[n];
        LocalDateTime[] dates = new LocalDateTime[n];

        for (int i = 0; i < n; i++) {
            keys[i] = keyBase + i;
            values[i] = i + 1;
            dates[i] = LocalDateTime.of(2000, 12, 12, 12, 12);
            cache.set(keys[i], values[i], dates[i]);
        }

        return keys;
    }
}

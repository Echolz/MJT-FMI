package bg.sofia.uni.fmi.mjt.cache;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MemCache<K, V> implements Cache<K, V> {

    private Map<K, V> genericCache;
    private Map<K, LocalDateTime> expirationCache;
    private long capacity;
    private int hit;
    private int miss;

    public MemCache() {
        this(10000);
    }

    public MemCache(long capacity) {
        this.capacity = capacity;
        this.genericCache = new HashMap<>();
        this.expirationCache = new HashMap<>();
        this.hit = 0;
        this.miss = 0;
    }

    @Override
    public V get(K key) {
        LocalDateTime now = LocalDateTime.now();

        if (this.genericCache.containsKey(key)) {
            //if value is present and expiration is null
            if (this.expirationCache.get(key) == null) {
                this.hit++;
                return this.genericCache.get(key);
            }

            //if value is present and expiration is after the local time
            if (this.expirationCache.get(key).isAfter(now)) {
                this.hit++;
                return this.genericCache.get(key);
            }

            //if value is present but expiration is before the local time
            this.miss++;
            genericCache.remove(key);
            expirationCache.remove(key);
            return null;
        }

        this.miss++;
        return null;
    }

    @Override
    public void set(K key, V value, LocalDateTime expiresAt) throws CapacityExceededException {
        LocalDateTime now = LocalDateTime.now();

        if (key == null || value == null) {
            return;
        }

        //cache is not full or has the given key already
        if (this.size() < this.capacity || (this.genericCache.containsKey(key) && this.expirationCache.containsKey(key))) {
            this.genericCache.put(key, value);
            this.expirationCache.put(key, expiresAt);
            return;
        }

        //cache is full and the key does not exist in the cache.
        boolean removedKey = false;
        for (K currentKey : genericCache.keySet()) {
            //if the element never expires
            if (expirationCache.get(currentKey) == null) {
                continue;
            }

            if (expirationCache.get(currentKey).isAfter(now)) {
                continue;
            }

            removedKey = true;
            genericCache.remove(currentKey);
            expirationCache.remove(currentKey);
            break;
        }

        //there were no expired keys removed
        if (!removedKey) {
            throw new CapacityExceededException();
        }

        //there was a key removed
        this.genericCache.put(key, value);
        this.expirationCache.put(key, expiresAt);
    }

    @Override
    public LocalDateTime getExpiration(K key) {
        if (this.genericCache.containsKey(key)) {
            return this.expirationCache.get(key);
        } else {
            return null;
        }
    }

    @Override
    public boolean remove(K key) {
        if (!this.genericCache.containsKey(key)) {
            return false;
        }

        this.genericCache.remove(key);
        this.expirationCache.remove(key);
        return true;
    }

    @Override
    public long size() {
        return this.genericCache.size();
    }

    @Override
    public void clear() {
        this.genericCache.clear();
        this.expirationCache.clear();
        this.hit = 0;
        this.miss = 0;
    }

    @Override
    public double getHitRate() {
        if (hit == 0) {
            return 0.0;
        } else if (miss == 0) {
            return 1.0;
        } else {
            return (double) hit / (double) miss;
        }
    }
}
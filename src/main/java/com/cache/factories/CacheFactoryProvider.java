package com.cache.factories;
import com.cache.policies.Cache;
public class CacheFactoryProvider<K, V> {
    public CacheFactory<K, V> getCacheFactory(String policy) {
        switch (policy.toLowerCase()) {
            case "lru":
                return new LRUCacheFactory<>();
            case "lfu":
                return new LFUCacheFactory<>();
            case "fifo":
                return new FIFOCacheFactory<>();
            default:
                throw new IllegalArgumentException("Unknown cache policy: " + policy);
        }
    }
}

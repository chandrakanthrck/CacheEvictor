package com.cache.factories;

import com.cache.policies.Cache;
import com.cache.policies.FIFOCache;
public class FIFOCacheFactory<K, V> implements CacheFactory<K, V> {
    @Override
    public Cache<K, V> createCache(int capacity) {
        return new FIFOCache<>(capacity);
    }
}

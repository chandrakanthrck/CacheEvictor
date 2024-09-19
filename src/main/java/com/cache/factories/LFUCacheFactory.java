package com.cache.factories;

import com.cache.policies.Cache;
import com.cache.policies.LFUCache;
public class LFUCacheFactory<K, V> implements CacheFactory<K, V> {
    @Override
    public Cache<K, V> createCache(int capacity) {
        return new LFUCache<>(capacity);
    }
}

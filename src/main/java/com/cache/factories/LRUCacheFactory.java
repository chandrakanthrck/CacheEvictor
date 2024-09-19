package com.cache.factories;

import com.cache.policies.Cache;
import com.cache.policies.LRUCache;
public class LRUCacheFactory<K, V> implements CacheFactory<K, V> {
    @Override
    public Cache<K, V> createCache(int capacity) {
        return new LRUCache<>(capacity);
    }
}

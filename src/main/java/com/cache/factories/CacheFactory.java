package com.cache.factories;
import com.cache.policies.Cache;

public interface CacheFactory<K, V> {
    Cache<K, V> createCache(int capacity);
}

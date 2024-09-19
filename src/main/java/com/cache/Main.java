package com.cache;

import com.cache.policies.Cache;
import com.cache.factories.CacheFactoryProvider;

public class Main {
    public static void main(String[] args) {
        CacheFactoryProvider<Integer, String> provider = new CacheFactoryProvider<>();

        System.out.println("=== Testing LRU Cache ===");
        Cache<Integer, String> lruCache = provider.getCacheFactory("lru").createCache(3);
        testCache(lruCache);

        System.out.println("\n=== Testing FIFO Cache ===");
        Cache<Integer, String> fifoCache = provider.getCacheFactory("fifo").createCache(3);
        testCache(fifoCache);

        System.out.println("\n=== Testing LFU Cache ===");
        Cache<Integer, String> lfuCache = provider.getCacheFactory("lfu").createCache(3);
        testCache(lfuCache);
    }

    private static void testCache(Cache<Integer, String> cache) {
        System.out.println("Adding entries to the cache: (1, One), (2, Two), (3, Three)");
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        System.out.println("Cache state after adding 3 entries:");
        System.out.println("Accessing key 1: " + cache.get(1)); // Accessing key 1 to modify order in LRU and LFU
        System.out.println("Accessing key 2: " + cache.get(2)); // Accessing key 2 to modify order in LRU and LFU

        System.out.println("Adding another entry (4, Four), should evict one item based on the policy.");
        cache.put(4, "Four"); // This should trigger an eviction based on the cache policy

        System.out.println("Cache state after eviction:");
        System.out.println("Accessing key 1 (expected: null if evicted): " + cache.get(1));
        System.out.println("Accessing key 2: " + cache.get(2));
        System.out.println("Accessing key 3: " + cache.get(3));
        System.out.println("Accessing key 4: " + cache.get(4));
    }
}
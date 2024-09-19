package com.cache.policies;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FIFOCache<K, V> implements Cache<K, V>{
    private final int capacity;
    private final HashMap<K,V> map;
    private final Queue<K> queue;

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.queue = new LinkedList<>();
    }
    public void put(K key, V value){
        if(map.containsKey(key)){
            map.put(key, value);
            return;
        }
        if(map.size() >= capacity){
            //remove oldest key
            K oldestKey = queue.poll();
            map.remove(oldestKey);
        }
        //add the key
        queue.offer(key);
        map.put(key, value);
    }
    public V get(K key){
        //return if key exists; else null
        return  map.getOrDefault(key, null);
    }
}
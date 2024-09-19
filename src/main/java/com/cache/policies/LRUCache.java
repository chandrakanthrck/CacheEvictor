package com.cache.policies;

import java.util.HashMap;

//complexity is (O(1)) keeping track of the access order for eviction when cache exceeds its size
public class LRUCache<K, V> implements Cache<K, V>{
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public class Node{
        K key;
        V value;
        Node prev;
        Node next;
        Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private final int capacity;
    private final HashMap<K, Node> map;
    Node head, tail;
    public void addNode(Node node){
        if(tail!=null){
            tail.next = node;
            node.prev = tail;
            node.next = null;
            tail = node;
        }else{
            head = tail = node;
        }
    }
    public void removeNode(Node node){
        if(node.prev!=null){
            node.prev.next = node.next;
        }else{
            head = node.next;
        }
        if(node.next!=null){
            node.next.prev = node.prev;
        }else{
            tail = node.prev;
        }
    }
    public void moveToTail(Node node){
        removeNode(node);
        addNode(node);
    }
    public V get(K key){
        if(!map.containsKey(key)){
            return null;
        }
        Node node = map.get(key);
        moveToTail(node);
        return  node.value;
    }
    public void put(K key, V value){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            moveToTail(node);
        }else{
            Node newNode = new Node(key,value);
            if(map.size()>=capacity){
                map.remove(head.key);
                removeNode(head);
            }
            addNode(newNode);
            map.put(key, newNode);
        }
    }
}
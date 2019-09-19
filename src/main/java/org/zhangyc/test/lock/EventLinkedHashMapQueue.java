package org.zhangyc.test.lock;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: yichu.zhang
 * @Date: 2019-09-18 19:12
 */

public class EventLinkedHashMapQueue<K, E> {
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private final int capacity;
    private LinkedHashMap<K, List<E>> linkedHashMap;

    public EventLinkedHashMapQueue(final int capacity){
        this(capacity, false);
    }

    public EventLinkedHashMapQueue(final int capacity, final boolean fair){
        if(capacity <= 0){
            throw new IllegalArgumentException();
        }
        this.lock = new ReentrantLock(fair);
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
        this.lock.newCondition();
        this.capacity = capacity;
        linkedHashMap = new LinkedHashMap<K, List<E>>(capacity);
    }

    public boolean add(final K key, final E value, final long timeout, final TimeUnit unit) throws InterruptedException {
        lock.lockInterruptibly();
        long nanos = unit.toNanos(timeout);
        System.out.println("nanos -> " + nanos);
        try{
            while (this.linkedHashMap.size() == this.capacity){
                if (nanos <= 0) {
                    return false;
                }
                nanos = this.notFull.awaitNanos(nanos);
            }

            this.enqueue(key, value);
            return true;
        }finally {
            lock.unlock();
        }
    }

    private void enqueue(final K key, final E value) {
        List<E> list = this.linkedHashMap.get(key);
        if (list == null) {
            list = new ArrayList<>();
            this.linkedHashMap.put(key, list);
        }
        list.add(value);

        this.notEmpty.signal();
    }

    public Map.Entry<K, List<E>> get(final long timeout, final TimeUnit unit) throws InterruptedException{
        long nanos = unit.toNanos(timeout);
        System.out.println("nanos -> " + nanos);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();

        try {
            while (this.linkedHashMap.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.notEmpty.awaitNanos(nanos);
            }
            return this.dequeue();
        } finally {
            lock.unlock();
        }
    }

    private Map.Entry<K, List<E>> dequeue() {
        final Iterator<Map.Entry<K, List<E>>> it = this.linkedHashMap.entrySet().iterator();
        final Map.Entry<K, List<E>> x = it.next();
        it.remove();
        this.notFull.signal();
        return x;
    }

}

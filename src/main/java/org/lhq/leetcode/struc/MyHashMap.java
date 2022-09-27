package org.lhq.leetcode.struc;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashMap {
    private class Entry {
        private int key;
        private int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 257;
    private LinkedList[] data;

    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Entry>();
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }

    public void put(int key, int value) {
        int hash = hash(key);
        Iterator<Entry> iterator = data[hash].iterator();
        while (iterator.hasNext()) {
            Entry next = iterator.next();
            if (next.getKey() == key) {
                next.setValue(value);
                return;
            }
        }
        data[hash].offerLast(new Entry(key,value));
    }

    public int get(int key) {
        int hash = hash(key);
        Iterator<Entry> iterator = data[hash].iterator();
        while (iterator.hasNext()) {
            Entry next = iterator.next();
            if (next.getKey() == key) {
                return next.getValue();
            }
        }
        return -1;
    }

    public void remove(int key) {
        int hash = hash(key);
        Iterator<Entry> iterator = data[hash].iterator();
        while (iterator.hasNext()) {
            Entry next = iterator.next();
            if (next.getKey() == key) {
                data[hash].remove(next);
            }
        }
    }
}

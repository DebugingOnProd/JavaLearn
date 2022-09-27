package org.lhq.leetcode.struc;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet {

    private static final int BASE = 256;
    private LinkedList[] data;

    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int hash = hash(key);
        Iterator<Integer> iterator = data[hash].iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next == key){
                return;
            }
        }
        data[hash].offerLast(key);
    }

    public void remove(int key) {
        int hash = hash(key);
        Iterator<Integer> iterator = data[hash].iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next == key){
                data[hash].remove(next);
                return;
            }
        }
    }

    public boolean contains(int key) {
        int hash = hash(key);
        Iterator<Integer> iterator = data[hash].iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next == key){
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

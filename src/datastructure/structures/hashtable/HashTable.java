package datastructure.structures.hashtable;

import java.util.TreeMap;

public class HashTable<K,V> {

    private static final int UPPERLOR = 10;
    private static final int LOWERLOR = 2;
    private static final int INITCAPACITY = 7;

    private TreeMap<K,V>[] hashTable;
    private int M;
    private int size;


    public HashTable(int M){
        this.M = M;
        this.size = 0;
        this.hashTable = new TreeMap[M];
        for(int i = 0;i < M;i++)
            hashTable[i] = new TreeMap<>();
    }

    public HashTable(){
        this(INITCAPACITY);
    }

    /**
     * 哈希函数
     * @param key
     * @return
     */
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if(map.containsKey(key))
            map.put(key,value);
        else{
            map.put(key,value);
            size++;
            if(size >= UPPERLOR * M)
                resize( M * 2);
        }
    }

    public V remove(K key){
        TreeMap<K,V> map = hashTable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size--;
            if(size <= LOWERLOR * M && M/2 >= INITCAPACITY)
                resize( M / 2);
        }
        return ret;
    }

    public void set(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException("the key is not exist");
        map.put(key, value);

    }

    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashTable[hash(key)].get(key);
    }
    
    private void resize(int newM){
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) 
            newHashTable[i] = new TreeMap<>();

        int oldM = M;
        M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K,V> map = hashTable[i];
            for(K key:map.keySet())
                newHashTable[hash(key)].put(key,map.get(key));
        }
        this.hashTable = newHashTable;
        
    }


}

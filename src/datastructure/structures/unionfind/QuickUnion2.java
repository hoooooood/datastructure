package datastructure.structures.unionfind;

public class QuickUnion2 implements UnionFind {

    private int[] parent;
    private int[] sz;

    public QuickUnion2(int size){
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < parent.length; i++){
            parent[i] = i;
            sz[i] = 1;
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int index){
        if(index < 0 || index >= parent.length)
            throw new IllegalArgumentException("index is illegal");

        while(parent[index] != index){
            index = parent[index];
        }
        return index;
    }

    @Override
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot)
            return;
        if(sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}

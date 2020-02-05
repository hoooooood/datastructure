package datastructure.structures.unionfind;

public class QuickUnion4 implements UnionFind {

    private int[] parent;
    private int[] rank;

    public QuickUnion4(int size){
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++){
            parent[i] = i;
            rank[i] = 1;
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
            parent[index] = parent[parent[index]];
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
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if(rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else{
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }


    }
}

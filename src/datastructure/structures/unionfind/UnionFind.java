package datastructure.structures.unionfind;

public interface UnionFind {
    int getSize();
    boolean isConnect(int p,int q);
    void unionElements(int p,int q);
}

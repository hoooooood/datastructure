package datastructure.structures.unionfind;

public class QuickFind implements UnionFind {

    private int[] id;

    public QuickFind(int size){
        id = new int[size];
        for (int i = 0; i < id.length; i++)
            id[i] = i;
    }


    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int index){
        if(index < 0 || index >= id.length)
            throw new IllegalArgumentException("index is not illegal");
        return id[index];
    }

    @Override
    public boolean isConnect(int p, int q) {
        if(find(p) == find(q))
            return true;
        return false;
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if(pId == qId)
            return;

        for (int i = 0; i < id.length; i++) {
            if(id[i] == pId)
                id[i] = qId;
        }
    }
}

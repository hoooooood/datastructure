package unionfind;

import datastructure.structures.unionfind.*;

import java.util.Random;

public class TestUnionFind {
    private static double testUnionFind(UnionFind unionFind,int m){
        int size = unionFind.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            unionFind.unionElements(a,b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            unionFind.isConnect(a,b);
        }
        long endTime = System.nanoTime();

        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;

//        QuickFind qf = new QuickFind(size);
//        System.out.println("quickfind :"+ testUnionFind(qf,m) + "s");
//
//        QuickUnion qu = new QuickUnion((size));
//        System.out.println("quickunion :"+ testUnionFind(qu,m) + "s");

        QuickUnion2 qu2 = new QuickUnion2((size));
        System.out.println("quickunion2 :"+ testUnionFind(qu2,m) + "s");

        QuickUnion3 qu3 = new QuickUnion3((size));
        System.out.println("quickunion3 :"+ testUnionFind(qu3,m) + "s");

        QuickUnion4 qu4 = new QuickUnion4((size));
        System.out.println("quickunion4 :"+ testUnionFind(qu4,m) + "s");

    }
}

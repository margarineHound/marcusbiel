package com.marcusbiel.javacourse.lesson2;

public class UnionFind {
    //number of elements
    private final int size;

    //number of each components
    private int[] setSize;

    private int[] parents;

    private int numSets;

    public UnionFind(int size){

        if (size <1)
            throw new IllegalArgumentException();
        this.size = size;
        this.numSets = size;
        this.setSize = new int[size];
        this.parents = new int[size];

        for (int i=0; i<size; i++){
            setSize[i] =1;
            parents[i] = i;

        }
    }

    public int find(int k){
        if(this.parents[k] == k)
            return k;

        int root = find(k);
        parents[k] = root;
        return root;
    }

    public boolean connected(int p, int q){
        return (find(p) == find(q));
    }

    public int componentSize(int k){
        return setSize[k];
    }

    public int size(){
        return this.size;
    }

    public int components(){
        return this.numSets;
    }

    public void unify(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        int sizeP = this.setSize[p];
        int sizeQ = this.setSize[q];

        if (rootP == rootQ)
            return;

        if (sizeP> sizeQ){
            this.parents[q] = rootP;
            this.setSize[p] += this.setSize[q];
        }else{
            this.parents[p] = rootQ;
            this.setSize[q] += this.setSize[p];

        }
        this.numSets--;
    }
}

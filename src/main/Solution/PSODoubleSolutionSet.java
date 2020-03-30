package main.Solution;

import java.util.ArrayList;

public class PSODoubleSolutionSet extends solutionSet {
    //PSO粒子群算法的解集类
    public ArrayList<PSODoubleSolution> array;
    public int whichbest;//全局最优算子的位置
    public double best;//全局最优的值
    public int realsize;
    public int size;
    public void add(PSODoubleSolution s){
        if (realsize<size){

            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(PSODoubleSolution s){
        array.remove(s);
        realsize--;
    }//删除

    public int size(){
        return array.size();
    }//显示大小

    public boolean isFull(){
        if (realsize==size){
            return true;
        }else{
            return false;
        }
    }

    public void replace(PSODoubleSolution s1,PSODoubleSolution s2){
        int i=0;
        for (PSODoubleSolution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

    public PSODoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<PSODoubleSolution>();
        this.size=n;
        realsize=0;
    }
}

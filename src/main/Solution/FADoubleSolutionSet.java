package main.Solution;

import java.util.ArrayList;

public class FADoubleSolutionSet extends solutionSet {
    public ArrayList<FADoubleSolution> array;
    public int realsize;
    public int size;
    //double attr_max;//最大吸引度
    public double best;
    public int whichbest;
    public void add(FADoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(FADoubleSolution s){
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

    public void replace(FADoubleSolution s1,FADoubleSolution s2){
        int i=0;
        for (FADoubleSolution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

    public FADoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<FADoubleSolution>();
        this.size=n;
        realsize=0;
    }
    //松鼠算法浮点数解集。
}

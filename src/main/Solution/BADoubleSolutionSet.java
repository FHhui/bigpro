package main.Solution;

import java.util.ArrayList;

public class BADoubleSolutionSet extends solutionSet {
    public ArrayList<BADoubleSolution> array;
    public int realsize;
    public int size;
    public double best;
    public int whichbest;
    public void add(BADoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(BADoubleSolution s){
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

    public void replace(BADoubleSolution s1,BADoubleSolution s2){
        int i=0;
        for (BADoubleSolution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

    public BADoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<BADoubleSolution>();
        this.size=n;
        realsize=0;
    }
    //松鼠算法浮点数解集。
}

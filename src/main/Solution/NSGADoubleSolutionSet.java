package main.Solution;

import java.util.ArrayList;

public class NSGADoubleSolutionSet extends solutionSet{
    public ArrayList<NSGADoubleSolution> array;
    public int realsize;
    public int size;

    public void add(NSGADoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(NSGADoubleSolution s){
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

    public void replace(NSGADoubleSolution s1,NSGADoubleSolution s2){
        int i=0;
        for (solution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

    public NSGADoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<NSGADoubleSolution>();
        this.size=n;
        realsize=0;
    }
    //松鼠算法浮点数解集。
}

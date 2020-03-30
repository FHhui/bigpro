package main.Solution;

import java.util.ArrayList;

public class NSGAIIIDoubleSolutionSet extends solutionSet {
    //NSGAIII的解集类
    public NSGAIIIDoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<>();
    }
    public ArrayList<NSGAIIIDoubleSolution> array;
    public void add(NSGAIIIDoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(NSGAIIIDoubleSolution s){
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

    public void replace(NSGAIIIDoubleSolution s1,NSGAIIIDoubleSolution s2){
        int i=0;
        for (NSGAIIIDoubleSolution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

}

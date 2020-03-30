package main.Solution;

import java.util.ArrayList;

public class MaShOADoubleSolutionSet extends solutionSet{

    public MaShOADoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<>();
    }
    public ArrayList<MaShOADoubleSolution> array;
    public void add(MaShOADoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(MaShOADoubleSolution s){
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

    public void replace(MaShOADoubleSolution s1,MaShOADoubleSolution s2){
        int i=0;
        for (MaShOADoubleSolution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

}

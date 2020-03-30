package main.Solution;

import java.util.ArrayList;

public class SSADoubleSolutionSet extends  solutionSet{
    public ArrayList<SSADoubleSolution> array;
    int realsize;
    int size;

    public void add(SSADoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(SSADoubleSolution s){
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

    public void replace(SSADoubleSolution s1,SSADoubleSolution s2){
        int i=0;
        for (solution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

    public SSADoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<SSADoubleSolution>();
        this.size=n;
        realsize=0;
    }
    //松鼠算法浮点数解集。


}

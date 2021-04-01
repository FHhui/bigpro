package main.Solution;

import main.problem.Multiproblem;

import java.util.ArrayList;
//多目标松鼠的解集类
public class NSSSADoubleSolutionSet extends solutionSet implements Cloneable{
    public ArrayList<NSSSADoubleSolution> array;
    public int realsize;
    public int size;
    double best;
    int whichbest;
    public NSSSADoubleSolutionSet copy(Multiproblem p){
        NSSSADoubleSolutionSet ans=new NSSSADoubleSolutionSet(array.size());

        for (int i=0;i< this.array.size();i++){
            ans.add(this.array.get(i).copy(this.array.get(i),p));
        }
        return ans;
    }
    public void add(NSSSADoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(NSSSADoubleSolution s){
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

    public void replace(NSSSADoubleSolution s1,NSSSADoubleSolution s2){
        int i=0;
        for (NSSSADoubleSolution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

    public NSSSADoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<NSSSADoubleSolution>();
        this.size=n;
        realsize=0;
    }
    //松鼠算法浮点数解集。

    public Object clone() throws CloneNotSupportedException
    {
        //这一步返回的这个student2还只是一个浅克隆，
        NSSSADoubleSolutionSet student2 = new NSSSADoubleSolutionSet(this.array.size());
        //然后克隆的过程中获得这个克隆的student2，然后调用这个getTeacher这个方方法得到这个Teacher对象。然后实现克隆。在设置到这个student2中的Teacher。
        //这样实现了双层克隆使得那个teacher对象也得到了复制。
        for (int i=0;i<this.array.size();i++){
            student2.array.add((NSSSADoubleSolution) this.array.get(i).clone());
        }
        //双层克隆使得那个teacher对象也得到了复制
        return student2;
    }
}

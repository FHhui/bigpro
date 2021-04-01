package main.Solution;

import main.problem.Hyperproblem;

import java.util.ArrayList;

public class MaShOADoubleSolutionSet extends solutionSet implements Cloneable{
    public ArrayList<MaShOADoubleSolution> array;
    public MaShOADoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<>();
    }

    public void add(MaShOADoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加
    public MaShOADoubleSolutionSet copy(Hyperproblem p){
        MaShOADoubleSolutionSet ans=new MaShOADoubleSolutionSet(this.array.size());
        System.out.println(this.array.size());
        for (int i=0;i< this.array.size();i++){
            ans.array.add(this.array.get(i).copy(this.array.get(i),p));
        }
        return ans;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        //这一步返回的这个student2还只是一个浅克隆，
        MaShOADoubleSolutionSet student2 = new MaShOADoubleSolutionSet(this.array.size());
        //然后克隆的过程中获得这个克隆的student2，然后调用这个getTeacher这个方方法得到这个Teacher对象。然后实现克隆。在设置到这个student2中的Teacher。
        //这样实现了双层克隆使得那个teacher对象也得到了复制。
        for (int i=0;i<this.array.size();i++){
            student2.array.add((MaShOADoubleSolution) this.array.get(i).clone());
        }
        //双层克隆使得那个teacher对象也得到了复制
        return student2;
    }
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

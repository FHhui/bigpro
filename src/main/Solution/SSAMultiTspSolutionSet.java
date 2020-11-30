package main.Solution;

import java.io.*;
import java.util.ArrayList;

public class SSAMultiTspSolutionSet extends solutionSet implements Cloneable {
    //松鼠算法多目标tsp类
    public ArrayList<SSAMultiTspSolution> array;

    public SSAMultiTspSolutionSet(int n){
        super(n);
        this.array=new ArrayList<>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        //这一步返回的这个student2还只是一个浅克隆，
        SSAMultiTspSolutionSet student2 = new SSAMultiTspSolutionSet(this.array.size());
        //然后克隆的过程中获得这个克隆的student2，然后调用这个getTeacher这个方方法得到这个Teacher对象。然后实现克隆。在设置到这个student2中的Teacher。
        //这样实现了双层克隆使得那个teacher对象也得到了复制。
        for (int i=0;i<this.array.size();i++){
            student2.array.add((SSAMultiTspSolution) this.array.get(i).clone());
        }
        //双层克隆使得那个teacher对象也得到了复制
        return student2;
    }

}

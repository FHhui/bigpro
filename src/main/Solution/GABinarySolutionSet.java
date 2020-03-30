package main.Solution;

import java.util.ArrayList;

public class GABinarySolutionSet extends solutionSet{

    public ArrayList<GABinarySolution> array;
    public int realsize;
    public int size;

    public void add(GABinarySolution s){
        //System.out.println(s.variables+"111");
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    }//增加
    public void remove(GABinarySolution s){
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
    public void replace(GABinarySolution s1,GABinarySolution s2)
    {
        int i=0;
        for (solution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1
    public GABinarySolutionSet(int n) {
        super(n);
        this.array= new ArrayList<GABinarySolution>();
        this.size=n;
        realsize=0;
    }
}

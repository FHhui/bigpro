package main.Operator;

import main.Solution.NSGADoubleSolution;
import main.Solution.NSGADoubleSolutionSet;
import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.solution;

import java.util.HashMap;
import java.util.Iterator;

public class NSSSASort extends Sort{
    //多目标松鼠算子的排序操作
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s){
        for(int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;j++){
                //System.out.println(j);
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    NSSSADoubleSolution ss= solution.clone(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    if(s.array.get(j).distance<s.array.get(j).distance){
                        //拥挤值距离小的往后排
                        NSSSADoubleSolution ss=solution.clone(s.array.get(j));
                        s.array.set(j,s.array.get(j+1));
                        s.array.set((j+1),ss);
                    }
                }
            }
        }
        return s;
    }
    public int getlevel(NSSSADoubleSolution ss,HashMap<int[],Integer> map){
        //获取区间密度的方法，这是为了网格法的应用才添加的
        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            int[] a=(int[])it.next();
            if (a[0]==ss.location[0]&&a[1]==ss.location[1]){
                return map.get(a);
            }
        }
        return Integer.MAX_VALUE;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, HashMap<int[],Integer> map){
        for(int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;j++){
                //System.out.println(j);
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    NSSSADoubleSolution ss=solution.clone(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    if(getlevel((s.array.get(j)),map)>getlevel((s.array.get(j+1)),map)){
                        //网格内粒子数目多的往后排，这里因为网格法的应用所以这里有部分改变
                        NSSSADoubleSolution ss=solution.clone(s.array.get(j));
                        s.array.set(j,s.array.get(j+1));
                        s.array.set((j+1),ss);
                    }
                }
            }
        }
        return s;
    }
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s){
        for(int i=0;i<s.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    NSGADoubleSolution ss=solution.clone(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    NSGADoubleSolution ss=solution.clone(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }
            }
        }
        return s;
    }

}

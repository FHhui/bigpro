package main.Operator;

import main.Solution.*;
import main.problem.*;
import main.problem.ZDT3problem;

import java.util.HashMap;
import java.util.Iterator;

public class NSSSASort extends Sort{
    //多目标松鼠算子使用拥挤度的排序操作
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s){
        for(int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;j++){
                //System.out.println(j);
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    NSSSADoubleSolution ss= s.array.get(j).copy(s.array.get(j),new ZDT2problem());
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    if(s.array.get(j).distance<s.array.get(j+1).distance){
                        //拥挤值距离小的往后排
                        NSSSADoubleSolution ss=s.array.get(j).copy(s.array.get(j),new ZDT2problem());
                        s.array.set(j,s.array.get(j+1));
                        s.array.set((j+1),ss);
                    }
                }
            }
        }
        return s;
    }
    //多目标松鼠使用网格密度的排序操作
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
                    NSSSADoubleSolution ss=s.array.get(j).copy(s.array.get(j),new ZDT2problem());
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
    //多目标遗传算法使用拥挤度排序
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s){
        for(int i=0;i<s.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    NSGADoubleSolution ss=s.array.get(j).copy(s.array.get(j),new ZDT4problem());
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    NSGADoubleSolution ss=s.array.get(j).copy(s.array.get(j),new ZDT4problem());
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }
            }
        }
        return s;
    }
    public NSSSADoubleSolutionSet execute_gird(NSSSADoubleSolutionSet s){
        for(int i=0;i<s.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    NSSSADoubleSolution ss=s.array.get(j).copy(s.array.get(j),new ZDT2problem());
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    if(s.array.get(j).distance<(s.array.get(j+1).distance)){
                        //网格内粒子数目多的往后排，这里因为网格法的应用所以这里有部分改变
                        NSSSADoubleSolution ss=s.array.get(j).copy(s.array.get(j),new ZDT2problem());
                        s.array.set(j,s.array.get(j+1));
                        s.array.set((j+1),ss);
                    }
            }
        }
        }
        return s;
    }
    //使用多目标tsp松鼠的排序算子
    public SSAMultiTspSolutionSet execute(SSAMultiTspSolutionSet s){
        //利用序列化与反序列化的操作非常容易导致运行时间变长，超级无敌长
        //可以手动实现copy方法来弥补这一不足
        //那么问题来了序列化与反序列化的操作为什么会那么的慢呢？

        for(int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;j++){
                //System.out.println(j);
                if (s.array.get(j).evafitness>s.array.get(j+1).evafitness){
                    SSAMultiTspSolution ss=SSAMultiTspSolution.copy(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }
            }
        }
        return s;
    }
    public int getlevel(SSAMultiTspSolution ss,HashMap<int[],Integer> map){
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
    public NSSSADoubleSolutionSet execute_evalfitness(NSSSADoubleSolutionSet s)
    {
        for(int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;j++){
                //System.out.println(j);
                if (s.array.get(j).evafitness>s.array.get(j+1).evafitness){
                    //evafitness大往后排
                    NSSSADoubleSolution ss=solution.clone(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }
            }
        }
        return s;
    }
    public SSAMultiTspSolutionSet execute_gird(SSAMultiTspSolutionSet s){
        for(int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                if (s.array.get(j).rank>s.array.get(j+1).rank){
                    //帕累托等级大往后排
                    SSAMultiTspSolution ss=s.array.get(j).copy(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set((j+1),ss);
                }else if (s.array.get(j).rank==s.array.get(j+1).rank){
                    //帕累托等级相等的情况
                    if(s.array.get(j).distance<(s.array.get(j+1).distance)){
                        //distance小的往后排
                        SSAMultiTspSolution ss=s.array.get(j).copy(s.array.get(j));
                        s.array.set(j,s.array.get(j+1));
                        s.array.set((j+1),ss);
                    }
                }
            }
        }
        return s;
    }
}

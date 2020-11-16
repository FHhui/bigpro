package main.Operator;

import main.Algorithm.MoSSA;
import main.Solution.NSGADoubleSolutionSet;
import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.solution;
import main.problem.Multiproblem;
import main.problem.ZDT1problem;
import main.problem.ZDT2problem;
import main.problem.ZDT6problem;

import java.util.HashMap;

public class NSGADoubleGeneration extends Selection {
    //author:FHhui
    //2020.1.31(nsga最后一个算子留念)
    //2020.2.21nsssa最后一个算子
    //nsga算法的迭代选择算子
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, NSSSADoubleSolutionSet s1, HashMap<int[],Integer> map){
        NSSSADoubleSolutionSet news=new NSSSADoubleSolutionSet(s.size());
        NSSSADoubleSolutionSet totalS=new NSSSADoubleSolutionSet(2*s.size());

        ZDT2problem p=new ZDT2problem();

        for (int i=0;i<2*s.size();i++){
                if (i<s.size()){
                    s.array.set(i,p.evalute(s.array.get(i)));
                    totalS.add(s.array.get(i));
            }else{
                int n=i-s.size();
                    s.array.set(n,p.evalute(s.array.get(n)));
                    totalS.add(s1.array.get(n));
            }
        }
        NSGAFastNonSort Nfns=new NSGAFastNonSort();
        totalS=Nfns.execute(totalS);
        MoSSA Nscd=new MoSSA(100,100);
        totalS=Nscd.calLocation(totalS);//懒得改了，直接把位置计算方法写在了主类里，所以出现这种情况千万别慌
        map=Nscd.map;
        //System.out.println(map);
        while (true){
            int  a = (int)(Math.random()  * totalS.array.size());
            int  b = (int)(Math.random()  * totalS.array.size());
            int Intensity_a=0;
            int Intensity_b=0;
            for (int[] key:map.keySet()){
                if (key[0]==totalS.array.get(a).location[0] && key[1]==totalS.array.get(a).location[1]){
                    Intensity_a=map.get(key);
                }
                if (key[0]==totalS.array.get(b).location[0] && key[1]==totalS.array.get(b).location[1]){
                    Intensity_b=map.get(key);
                }
            }
            if (Intensity_a>Intensity_b){
                news.add(totalS.array.get(b));
            }else{
                news.add(totalS.array.get(a));
            }
            if (news.array.size()==s.array.size()) break;
        }
        return news;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, NSSSADoubleSolutionSet s1){
        NSSSADoubleSolutionSet news=new NSSSADoubleSolutionSet(s.size());
        NSSSADoubleSolutionSet totalS=new NSSSADoubleSolutionSet(2*s.size());
        for (int i=0;i<2*s.size();i++){
            if (i<s.size()){
                totalS.add(s.array.get(i));
            }else{
                int n=i-s.size();
                totalS.add(s1.array.get(n));
            }
        }
        NSGAFastNonSort Nfns=new NSGAFastNonSort();
        totalS=Nfns.execute(totalS);
        CalDistance Nscd=new CalDistance();
        totalS=Nscd.execute(totalS);//懒得改了，直接把位置计算方法写在了主类里，所以出现这种情况千万别慌
        NSSSASort NS=new NSSSASort();
        totalS=NS.execute(totalS);//重新进行排序,这里的map发生了改变应该有一个重新计算的过程
        for (int i=0;i<s.size();i++){
            news.add(totalS.array.get(i));
        }
        return news;
    }
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s, NSGADoubleSolutionSet s1, Multiproblem p){
        NSGADoubleSolutionSet news=new NSGADoubleSolutionSet(s.size());
        NSGADoubleSolutionSet totalS=new NSGADoubleSolutionSet(2*s.size());
        for (int i=0;i<2*s.size();i++){
            if (i<s.size()){
                totalS.add(s.array.get(i));
            }else{
                int n=i-s.size();
                totalS.add(s1.array.get(n));
            }
        }
        ZDT1problem p1=(ZDT1problem)p;
        for (int i=0;i<totalS.size();i++){
            totalS.array.set(i,p1.evalute(totalS.array.get(i)));
            System.out.println("["+totalS.array.get(i).fitness[0]+","+totalS.array.get(i).fitness[1]+"],");
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhxxxxxxxxxxxxxxxxxxxhhhhhhhhhhhhhhhhh");
        NSGAFastNonSort Nfns=new NSGAFastNonSort();
        totalS=Nfns.execute(totalS);
        CalDistance Nscd=new CalDistance();
        totalS=Nscd.execute(totalS);
        NSSSASort NS=new NSSSASort();
        totalS=NS.execute(totalS);//重新进行排序
        for (int i=0;i<s.size();i++){
            news.add(totalS.array.get(i));
        }
        return news;
    }
}

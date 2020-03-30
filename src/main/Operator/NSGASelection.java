package main.Operator;

import main.Solution.NSGADoubleSolutionSet;

public class NSGASelection extends Selection{
    //NSGA中的选择算子，使用的是锦标赛算法
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s){
        NSGADoubleSolutionSet news=new NSGADoubleSolutionSet(s.size);
        for (int i=0;i<s.array.size();i++){
            int m,n;
            m=(int)(Math.random()*s.array.size());
            n=(int)(Math.random()*s.array.size());
            if(s.array.get(m).rank<(s.array.get(n).rank)){
                news.array.add(s.array.get(m));//取帕累托等级小的
            }else if(s.array.get(m).rank==s.array.get(n).rank){
                //如果帕累托等级相同的话，就比较距离
                news.array.add(s.array.get(m).distance>s.array.get(n).distance?s.array.get(m):s.array.get(n));
            }else{
                news.array.set(i,s.array.get(n));
            }
        }
        return news;
    }

}

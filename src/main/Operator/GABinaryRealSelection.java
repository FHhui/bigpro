package main.Operator;

import main.Solution.GABinarySolution;
import main.Solution.GABinarySolutionSet;

public class GABinaryRealSelection extends Selection{
    //遗传算法二进制选择算子,使用了二进制锦标赛选择方法，
    //在迭代种群的时候，选择算子与生成交配种群的算子不一样。
    //保留最优解，删除最差解
    public GABinarySolutionSet execute(GABinarySolutionSet s) {
        GABinarySolutionSet newset=new GABinarySolutionSet(s.size());

        for (int i=0;i<s.size();i++) {

            int a=(int)Math.random()*s.size();
            int b=(int)Math.random()*s.size();
            GABinarySolution s1 = (GABinarySolution) s.array.get(a);
            GABinarySolution s2 = (GABinarySolution) s.array.get(b);
            if (s1.fitness[0] < s2.fitness[0]) {
                newset.add(s1);
            } else {
                newset.add(s2);
            }
        }
        return newset;

    }
}

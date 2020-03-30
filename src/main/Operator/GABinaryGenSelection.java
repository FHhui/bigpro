package main.Operator;

import main.Solution.GABinarySolutionSet;

public class GABinaryGenSelection extends Selection{
    //遗传算法二进制迭代时选择算子，通过比较子代与父代最优解
    //如果子代最优不如父代最优，那么将父代最优添加到子代当中，取代子代的最差解
    public GABinarySolutionSet execute(GABinarySolutionSet s, GABinarySolutionSet child) {
        GABinaryFindLocal GABF=new GABinaryFindLocal();
        int sbest=GABF.findbest(s);//父代最佳所在位置
        int sworst=GABF.findworst(s);//父代最差所在位置
        int cbest=GABF.findbest(child);//子代最佳所在位置
        int cworst=GABF.findworst(child);//子代最差所在位置
        if(child.array.get(cbest).fitness[0]<s.array.get(sbest).fitness[0]){
            return child;
        }else if (child.array.get(cbest).fitness[0]>s.array.get(sbest).fitness[0]){
            //child.replace(child.array.get(cworst),s.array.get(sbest));
            return s;
        }
        return child;
    }
}

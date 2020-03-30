package main.Operator;

import main.Solution.GABinarySolution;
import main.Solution.GABinarySolutionSet;

public class GABinaryCrossover extends Crossover{
    //遗传算法二进制交叉算子
    public GABinaryCrossover(double pc){
        super(pc);
    }
    public GABinarySolutionSet execute(GABinarySolutionSet s) {
        //二进制锦标赛选择方法
        GABinaryRealSelection GABRS=new GABinaryRealSelection();
        s=GABRS.execute(s);//二进制锦标赛选择
        for (int i=0;i<s.array.size()-1;i++){//i代表第i个个体
            double m=Math.random();


            for (int j=0;j<s.array.get(0).variables.length;j++){//j代表第j维的自变量
                //自变量数组的长度代表着目标问题的维度

                if (m<pc){
                    int pos=(int)(Math.random()* GABinarySolution.eLen);
                    String tmp=s.array.get(i).variables[j].getBinaryVariable().substring(pos);
                    s.array.get(i).variables[j].binaryVariable=s.array.get(i).variables[j].getBinaryVariable().substring(0,pos)+s.array.get(i+1).variables[j].getBinaryVariable().substring(pos);
                    s.array.get(i+1).variables[j].binaryVariable=s.array.get(i+1).variables[j].getBinaryVariable().substring(0,pos)+tmp;
                }

            }
        }
        return s;
    }
}

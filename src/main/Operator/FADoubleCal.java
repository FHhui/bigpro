package main.Operator;

import main.Solution.FADoubleSolutionSet;
import main.Solution.solutionSet;

public class FADoubleCal extends operator{
    //计算距离值算子
    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public FADoubleSolutionSet execute(FADoubleSolutionSet s, Double light_absorb, Double attr_max){
        for (int i=0;i<s.size();i++){
            for (int j=0;j<s.size();j++){
                double sum=0;
                for (int m=0;m<s.array.get(0).variables.length;m++)
                {
                    sum+=(s.array.get(i).variables[m].doubleVariable
                            -s.array.get(j).variables[m].doubleVariable)*
                            (s.array.get(i).variables[m].doubleVariable
                                    -s.array.get(j).variables[m].doubleVariable);
                }
                s.array.get(i).dis[j]=Math.sqrt(sum);//计算距离
                s.array.get(i).relative_light[j]=s.array.get(i).fitness[0]*
                        Math.exp(-light_absorb*s.array.get(i).dis[j]);//计算相对亮度
                s.array.get(i).attractiveness[j]=attr_max
                        *Math.exp(-light_absorb*s.array.get(i).dis[j]);//计算个体吸引度
            }
        }
        return s;
    }

}

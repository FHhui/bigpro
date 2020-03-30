package main.Operator;

import main.Solution.FADoubleSolutionSet;
import main.Solution.solutionSet;
import main.problem.RGAproblem;
import main.problem.problem;

public class FADoubleReplace extends operator{
    //萤火虫算法移动算子
    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public FADoubleSolutionSet execute(FADoubleSolutionSet s, problem p, double step){
        for (int i=0;i<s.array.size();i++){
            double[] replace=new double[s.array.get(i).variables.length];
            for (int j=0;j<s.array.size();j++) {
                if (s.array.get(i).relative_light[j] > s.array.get(j).relative_light[i]){
                    for (int m = 0; m < s.array.get(i).variables.length; m++) {
                        replace[m] += s.array.get(j).variables[m].doubleVariable
                                - s.array.get(i).variables[m].doubleVariable;
                    }
            }
            }
            //if ()
            for (int j=0;j<s.array.get(i).variables.length;j++){
                s.array.get(i).variables[j].doubleVariable+=s.array.get(i).attractiveness[j]
                        *replace[j]*step*(Math.random()*0.5);
            }
            //计算适应值
            RGAproblem p1=(RGAproblem)p;
            s.array.set(i,p1.evalute(s.array.get(i)));
        }
        return s;
    }
}

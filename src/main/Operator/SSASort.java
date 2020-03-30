package main.Operator;

import main.Algorithm.SSA;
import main.Solution.SSADoubleSolution;
import main.Solution.SSADoubleSolutionSet;
import main.Solution.solution;

public class SSASort extends Sort{
    //松鼠算法的排序算子，并且有声明的功能
    int is_best= SSA.is_best;
    int is_sec_best=SSA.is_sec_best;
    public SSADoubleSolutionSet execute(SSADoubleSolutionSet s) {

        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                //冒泡排序
                if (s.array.get(j).fitness[0]>s.array.get(j+1).fitness[0]){
                    SSADoubleSolution a= solution.clone(s.array.get(j));
                    SSADoubleSolution b=solution.clone(s.array.get(j+1));
                    s.array.set(j,b);
                    s.array.set(j+1,a);
                }
            }
        }
        //冒泡排序结束，开始声明程序
        for (int i=0;i<is_best;i++)
            s.array.get(i).is_best=true;
        //标记到达过最优解的松鼠
        for (int i=is_best;i<is_best+is_sec_best;i++)
            s.array.get(i).is_sec_best=true;
        //标记到达过次优解的松鼠
        return s;
    }
}

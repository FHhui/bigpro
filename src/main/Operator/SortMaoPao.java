package main.Operator;

import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.solution;

public class SortMaoPao extends Sort{
    public NSSSADoubleSolutionSet run(NSSSADoubleSolutionSet s){
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                //冒泡排序
                if (s.array.get(j).fitness[0]>s.array.get(j+1).fitness[0]){
                    NSSSADoubleSolution a= solution.clone(s.array.get(j));
                    NSSSADoubleSolution b=solution.clone(s.array.get(j+1));
                    s.array.set(j,b);
                    s.array.set(j+1,a);
                }
            }
        }
        return s;
    }
}

package main.Operator;

import main.Algorithm.MaShOA;
import main.Solution.MaShOADoubleSolution;
import main.Solution.MaShOADoubleSolutionSet;
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
    public MaShOADoubleSolutionSet run(MaShOADoubleSolutionSet s){
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                //冒泡排序
                if (s.array.get(j).JF>s.array.get(j+1).JF){
                    MaShOADoubleSolution a= solution.clone(s.array.get(j));
                    MaShOADoubleSolution b=solution.clone(s.array.get(j+1));
                    s.array.set(j,b);
                    s.array.set(j+1,a);
                }
            }
        }
        return s;
    }
}

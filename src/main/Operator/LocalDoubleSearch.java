package main.Operator;

import main.Solution.BADoubleSolutionSet;
import main.Solution.FADoubleSolutionSet;
import main.Solution.PSODoubleSolutionSet;

public class LocalDoubleSearch extends LocalSearch{
    public BADoubleSolutionSet execute(BADoubleSolutionSet s) {
        double best=Double.MAX_VALUE;
        for (int i=0;i<s.size();i++){
            //寻找最优解的操作
            if (s.array.get(i).fitness[0]<best){
                s.whichbest=i;
                s.best=s.array.get(i).fitness[0];
                best=s.best;
            }
        }
        return s;
    }
    public FADoubleSolutionSet execute(FADoubleSolutionSet s){
        double best=Double.MAX_VALUE;
        for (int i=0;i<s.size();i++){
            //寻找最优解的操作
            if (s.array.get(i).fitness[0]<best){
                s.whichbest=i;
                s.best=s.array.get(i).fitness[0];
                best=s.best;
            }

    }
        return s;
    }
    public PSODoubleSolutionSet execute(PSODoubleSolutionSet s){
        double best=Double.MAX_VALUE;
        for (int i=0;i<s.size();i++){
            //寻找最优解的操作
            if (s.array.get(i).fitness[0]<best){
                s.whichbest=i;
                s.best=s.array.get(i).fitness[0];
                best=s.best;
            }
        }
        return s;
    }
}

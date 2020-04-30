package main.Operator;

import main.Algorithm.MaShOA;
import main.Algorithm.MoSSA;
import main.Solution.MaShOADoubleSolution;
import main.Solution.MaShOADoubleSolutionSet;
import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.solution;
import main.problem.Hyperproblem;
import main.problem.Multiproblem;

public class SortMaoPao extends Sort{
    public NSSSADoubleSolutionSet run(NSSSADoubleSolutionSet s, Multiproblem p){
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                //冒泡排序
                if (s.array.get(j).evafitness>s.array.get(j+1).evafitness){
                    NSSSADoubleSolution a= s.array.get(j).copy(s.array.get(j),p);
                    NSSSADoubleSolution b= s.array.get(j).copy(s.array.get(j),p);
                    s.array.set(j,b);
                    s.array.set(j+1,a);
                }
            }
        }
        for (int i=0;i<s.array.size();i++){
            if (i< MoSSA.is_best){s.array.get(i).is_best=true;}
            else if(i<MoSSA.is_best+MoSSA.is_sec_best){
                s.array.get(i).is_sec_best=true;
            }
        }
        return s;
    }
    public MaShOADoubleSolutionSet run(MaShOADoubleSolutionSet s, Hyperproblem p){
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-1-i;j++){
                //冒泡排序
                if (s.array.get(j).JF>s.array.get(j+1).JF){

                    MaShOADoubleSolution a= s.array.get(j).copy(s.array.get(j),p);
                    MaShOADoubleSolution b=s.array.get(j).copy(s.array.get(j+1),p);

                    s.array.set(j,b);
                    s.array.set(j+1,a);
                }
            }
        }
        return s;
    }
}

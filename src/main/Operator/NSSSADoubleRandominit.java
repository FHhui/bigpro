package main.Operator;

import main.Solution.NSSSADoubleSolutionSet;
import main.problem.DTLZ1;
import main.problem.Hyperproblem;
import main.problem.Multiproblem;
import main.problem.ZDT4problem;

public class NSSSADoubleRandominit extends Randominit{
    //NSSSA算法的随机初始化算子
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet solutionS, Multiproblem p ){
        while (!solutionS.isFull()){
            NSSSADoubleSolution NDS=new NSSSADoubleSolution(p);
            for (int i=0;i<NDS.variables.length;i++){
                NDS.variables[i].setDoubleVariable(p.lowerlimit.get(i)+Math.random()*(p.upperlimit.get(i)-p.lowerlimit.get(i)));
            }
            ZDT4problem z1=(ZDT4problem) p;
            NDS=z1.evalute(NDS);
            solutionS.add(NDS);
        }
        return solutionS;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet solutionS, Hyperproblem p ){
        while (!solutionS.isFull()){
            NSSSADoubleSolution NDS=new NSSSADoubleSolution(p);
            for (int i=0;i<NDS.variables.length;i++){
                NDS.variables[i].setDoubleVariable(p.lowerlimit.get(i)+Math.random()*(p.upperlimit.get(i)-p.lowerlimit.get(i)));
            }
            DTLZ1 z1=(DTLZ1) p;
            NDS=z1.evalute(NDS);
            solutionS.add(NDS);
        }
        return solutionS;
    }
}

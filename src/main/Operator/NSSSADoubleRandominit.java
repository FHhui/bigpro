package main.Operator;

import main.Solution.NSSSADoubleSolutionSet;
import main.problem.*;

public class NSSSADoubleRandominit extends Randominit{
    //NSSSA算法的随机初始化算子
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet solutionS, Multiproblem p ){
        while (!solutionS.isFull()){
            NSSSADoubleSolution NDS=new NSSSADoubleSolution(p);
            for (int i=0;i<NDS.variables.length;i++){
                NDS.variables[i].setDoubleVariable(p.lowerlimit.get(i)+Math.random()*(p.upperlimit.get(i)-p.lowerlimit.get(i)));
            }
            ZDT6problem z1=(ZDT6problem) p;
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

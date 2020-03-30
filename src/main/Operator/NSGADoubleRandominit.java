package main.Operator;

import main.Solution.NSGADoubleSolution;
import main.Solution.NSGADoubleSolutionSet;
import main.problem.Multiproblem;
import main.problem.ZDT1problem;

public class NSGADoubleRandominit extends Randominit{
    //继承了随机初始化的NSGA算法的随机初始化类
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet solutionS, Multiproblem p ){
        while (!solutionS.isFull()){
            NSGADoubleSolution NDS=new NSGADoubleSolution(p);
            for (int i=0;i<NDS.variables.length;i++){
                NDS.variables[i].setDoubleVariable(p.lowerlimit.get(i)+Math.random()*(p.upperlimit.get(i)-p.lowerlimit.get(i)));
            }
            ZDT1problem z1=(ZDT1problem) p;
            NDS=z1.evalute(NDS);
            solutionS.add(NDS);
        }
        return solutionS;
    }
}

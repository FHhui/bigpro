package main.Operator;

import main.Solution.NSGAIIIDoubleSolution;
import main.Solution.NSGAIIIDoubleSolutionSet;
import main.problem.DTLZ1;
import main.problem.Hyperproblem;

public class NSGAIIIDoubleRandominit extends Randominit {
    //NSGAIII算法的随机初始化算子
    public NSGAIIIDoubleSolutionSet execute(NSGAIIIDoubleSolutionSet solutionS, Hyperproblem p) {
        while (!solutionS.isFull()) {
            NSGAIIIDoubleSolution NDS = new NSGAIIIDoubleSolution(p);

            for (int i = 0; i < NDS.variables.length; i++) {
                NDS.variables[i].setDoubleVariable(p.lowerlimit.get(i) + Math.random() * (p.upperlimit.get(i) - p.lowerlimit.get(i)));
            }
            DTLZ1 z1 = (DTLZ1) p;
            NDS = z1.evalute(NDS);
            solutionS.add(NDS);
        }
        return solutionS;
    }

}

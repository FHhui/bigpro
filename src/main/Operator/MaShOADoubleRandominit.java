package main.Operator;

import main.Solution.MaShOADoubleSolution;
import main.Solution.MaShOADoubleSolutionSet;
import main.Solution.NSGAIIIDoubleSolution;
import main.Solution.NSGAIIIDoubleSolutionSet;
import main.problem.*;

public class MaShOADoubleRandominit extends Randominit{
    //MaShOA算法的随机初始化算子
    public MaShOADoubleSolutionSet execute(MaShOADoubleSolutionSet solutionS, Hyperproblem p) {
        DTLZ1 z1 = (DTLZ1) p;
        while (!solutionS.isFull()) {
            MaShOADoubleSolution NDS = new MaShOADoubleSolution(z1);
               // System.out.println(z1.lowerlimit.get(0));
            for (int i = 0; i < NDS.variables.length; i++) {
                NDS.variables[i].setDoubleVariable(z1.lowerlimit.get(i) + Math.random() * (z1.upperlimit.get(i) - z1.lowerlimit.get(i)));
            }

            NDS = z1.eval(NDS);
            solutionS.add(NDS);
        }
        return solutionS;
    }
}

package main.Operator;

import main.Solution.SSADoubleSolution;
import main.Solution.SSADoubleSolutionSet;
import main.problem.RGAproblem;
import main.problem.Singleproblem;

public class SSADoubleRandominit extends Randominit{
    //单目标松鼠算法的随机初始化算子,相比于遗传算法应该有一个寻找最优，次优的操作。
    public SSADoubleSolutionSet execute(SSADoubleSolutionSet solutionS, Singleproblem p) {
        while (!solutionS.isFull()) {
            SSADoubleSolution SDS = new SSADoubleSolution(p);
            for (int i = 0; i < SDS.variables.length; i++) {
                SDS.variables[i].setDoubleVariable( p.lowerlimit.get(i) + Math.random() * (p.upperlimit.get(i) - p.lowerlimit.get(i)));
            }
            RGAproblem p1=(RGAproblem) p;
            SDS=p1.evalute(SDS);
            solutionS.add(SDS);
        }

        return solutionS;
    }
}


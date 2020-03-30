package main.Operator;

import main.Solution.BADoubleSolution;
import main.Solution.BADoubleSolutionSet;
import main.problem.RGAproblem;
import main.problem.Singleproblem;

public class BADoubleRandominit extends Randominit{
    //BA算法的随机初始化算子

    public BADoubleSolutionSet execute(BADoubleSolutionSet solutionS, Singleproblem p) {
        while (!solutionS.isFull()) {
            BADoubleSolution SDS = new BADoubleSolution(p);
            for (int i = 0; i < SDS.variables.length; i++) {
                SDS.variables[i].setDoubleVariable( p.lowerlimit.get(i) + Math.random() * (p.upperlimit.get(i) - p.lowerlimit.get(i)));
            }
            RGAproblem p1=(RGAproblem) p;
            SDS=p1.evalute(SDS);
            solutionS.add(SDS);
        }
       //寻找本地最优解算子
        LocalDoubleSearch BALDS=new LocalDoubleSearch();
        solutionS=BALDS.execute(solutionS);
        return solutionS;
    }
}

package main.Operator;

import main.Solution.FADoubleSolution;
import main.Solution.FADoubleSolutionSet;
import main.problem.RGAproblem;
import main.problem.problem;

public class FADoubleRandominit extends Randominit {
    //实现fa算法随机初始化

    public FADoubleSolutionSet execute(FADoubleSolutionSet solutionS, problem p) {
        while (!solutionS.isFull()) {
            FADoubleSolution SDS = new FADoubleSolution(p);
            for (int i = 0; i < SDS.variables.length; i++) {
                SDS.variables[i].setDoubleVariable( p.lowerlimit.get(i) + Math.random() * (p.upperlimit.get(i) - p.lowerlimit.get(i)));
            }
            RGAproblem p1=(RGAproblem) p;
            SDS=p1.evalute(SDS);

            solutionS.add(SDS);
        }
        //寻找本地最优解算子
        LocalDoubleSearch FALDS=new LocalDoubleSearch();
        solutionS=FALDS.execute(solutionS);

        return solutionS;

    }
}

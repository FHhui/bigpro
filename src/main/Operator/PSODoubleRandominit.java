package main.Operator;

import main.Solution.PSODoubleSolution;
import main.Solution.PSODoubleSolutionSet;
import main.problem.RGAproblem;
import main.problem.Singleproblem;

public class PSODoubleRandominit extends Randominit {
    public PSODoubleSolutionSet execute(PSODoubleSolutionSet solutionS, Singleproblem p ){
        while (!solutionS.isFull()) {
            PSODoubleSolution SDS = new PSODoubleSolution(p);
            for (int i = 0; i < SDS.variables.length; i++) {
                SDS.variables[i].setDoubleVariable( p.lowerlimit.get(i) + Math.random() * (p.upperlimit.get(i) - p.lowerlimit.get(i)));
                SDS.variables_best[i].doubleVariable=SDS.variables[i].doubleVariable;//初始化历史最优解的位置
                SDS.v[i]=Math.random()*SDS.vmax[i];//
            }

            RGAproblem p1=(RGAproblem) p;
            SDS=p1.evalute(SDS);
            SDS.fitness_best[0]=SDS.fitness[0];//初始化历史最优解
            solutionS.add(SDS);
        }
        //寻找本地最优解算子
        LocalDoubleSearch LDS=new LocalDoubleSearch();
        solutionS=LDS.execute(solutionS);
        return solutionS;
    }
}

package main.Algorithm;

import main.Solution.solutionSet;
import main.problem.problem;

public class SingleAlogorithm extends algorithm {

    @Override
    public solutionSet run(problem p) {
        //具体实现这里应该使用的是Singleproblem
        return getResult(p);
    }

    @Override
    public solutionSet getResult(problem p ) {
        //具体实现这里应该是使用的Singleproblem
        return null;
    }

    //单目标算法
}

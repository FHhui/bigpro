package main.Algorithm;

import main.Solution.solutionSet;
import main.problem.problem;

public abstract class algorithm {
    //算法类
    public abstract solutionSet run(problem p);//运行方法,将一个问题导入进去
    public abstract solutionSet getResult(problem p);
    //得到结果集，也就是我们常说的帕累托解集
}

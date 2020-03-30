package main.Solution;

import main.problem.Singleproblem;

public class SSADoubleSolution extends solution{
    //松鼠浮点数解集
    public DoubleVariable[] variables;//自变量集合
    public boolean is_best;//是否到达过最优解
    public boolean is_sec_best;//是否到达过次优解

    public SSADoubleSolution(Singleproblem p) {
        super(p);
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
    }

}

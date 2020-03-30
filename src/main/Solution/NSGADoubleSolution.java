package main.Solution;

import main.problem.Multiproblem;

import java.util.ArrayList;

public class NSGADoubleSolution extends solution {
    //nsga算法的浮点数解集,
    public DoubleVariable[] variables;//自变量集合
    public int nq;//个体被支配的次数
    public double distance;//拥挤度
    public int rank;//帕累托等级
    public ArrayList<NSGADoubleSolution> sp;//支配集合
    public NSGADoubleSolution(Multiproblem p) {
        super(p);
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
        this.sp=new ArrayList<>();
        this.nq=0;
        this.distance=0.0;
    }
}

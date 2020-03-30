package main.Operator;

import main.Solution.DoubleVariable;
import main.Solution.solution;
import main.Solution.variable;
import main.problem.Hyperproblem;
import main.problem.Multiproblem;

import java.util.ArrayList;

public class NSSSADoubleSolution extends solution {
    //多目标松鼠的解类
    public DoubleVariable[] variables;//自变量集合
    public int[] location;//
    public int nq;//个体被支配的次数
    public double distance;//拥挤度
    public int rank;//帕累托等级
    public boolean is_best;//最优解标记
    public boolean is_sec_best;//次优解标记
    public ArrayList<NSSSADoubleSolution> sp;//支配集合
    public NSSSADoubleSolution(Multiproblem p) {
        //构造方法
        super(p);
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
        location=new int[2];
        this.sp=new ArrayList<>();
        this.nq=0;
        this.distance=0.0;
    }
    public NSSSADoubleSolution(Hyperproblem p){
        super(p);
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
        location=new int[3];
        this.sp=new ArrayList<>();
        this.nq=0;
        this.distance=0.0;
    }
}

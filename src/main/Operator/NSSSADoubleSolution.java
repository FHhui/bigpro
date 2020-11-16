package main.Operator;

import main.Solution.DoubleVariable;
import main.Solution.MaShOADoubleSolution;
import main.Solution.solution;
import main.Solution.variable;
import main.problem.Hyperproblem;
import main.problem.Multiproblem;

import java.util.ArrayList;

public class NSSSADoubleSolution extends solution {
    //多目标松鼠的解类
    public double evafitness;//映射函数
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
        evafitness=0;
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
        location=new int[p.getNumberOfObjectives()];
        this.sp=new ArrayList<>();
        this.nq=0;
        this.distance=0.0;
    }

    public NSSSADoubleSolution copy(NSSSADoubleSolution s, Multiproblem p){
        NSSSADoubleSolution newS = new NSSSADoubleSolution(p);
        newS.location=s.location;
        newS.fitness=s.fitness;
        newS.evafitness=s.evafitness;
        newS.nq=s.nq;
        newS.distance=s.distance;
        newS.rank=s.rank;
        newS.is_best=s.is_best;
        newS.is_sec_best=s.is_sec_best;
        newS.sp=s.sp;
        for (int i=0;i<variables.length;i++){
            newS.variables[i].doubleVariable=s.variables[i].doubleVariable;
        }
        return  newS;
    }

    public NSSSADoubleSolution(Hyperproblem p){
        super(p);
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
        location=new int[p.getNumberOfObjectives()];
        this.evafitness=0;
        this.sp=new ArrayList<>();
        this.nq=0;
        this.distance=0.0;
    }
}

package main.Solution;

import main.Algorithm.MaShOA;
import main.problem.Hyperproblem;
import main.problem.problem;

import java.util.ArrayList;

public class MaShOADoubleSolution extends solution{
    public boolean is_select;
    public double se;
    public double JF;
    public boolean is_best;
    public  boolean is_sec_best;
    public DoubleVariable[] variables;//自变量集合
    public double distance;//距离值
    public int nq;//个体被支配数
    public int rank;//帕累托等级
    public double RPAA;//个体关联参考点成就值
    public ReferencePoint<MaShOADoubleSolution> referencePoint;//个体关联的最近的参考点
    public ArrayList<MaShOADoubleSolution> sp;//支配集合
    public MaShOADoubleSolution(Hyperproblem p){
        super(p);
        this.is_select=false;
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
        this.sp=new ArrayList<>();
        this.nq=0;
        this.is_sec_best=false;
        this.is_best=false;
    }
    public void setReferencePoint(ReferencePoint<MaShOADoubleSolution> referencePoint){
        this.referencePoint=referencePoint;
    }
    public MaShOADoubleSolution copy(MaShOADoubleSolution s,Hyperproblem p){
        MaShOADoubleSolution newS = new MaShOADoubleSolution(p);

        for (int i=0;i<variables.length;i++){
            newS.variables[i].doubleVariable=s.variables[i].doubleVariable;
        }
        return  newS;

    }
}

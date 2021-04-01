package main.Solution;

import main.Algorithm.MaShOA;
import main.problem.DTLZ1;
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
        MaShOADoubleSolution b = new MaShOADoubleSolution(new DTLZ1());
        b.is_select=this.is_select;
        b.is_sec_best=this.is_sec_best;
        b.is_best=this.is_best;
        b.se=this.se;
        b.JF=this.JF;
        double fitness0=this.fitness[0];
        double fitness1=this.fitness[1];
        b.fitness[0]=fitness0;
        b.fitness[1]=fitness1;

        for(int i=0;i<this.variables.length;i++){
            b.variables[i].doubleVariable=
                    (this.variables[i].getDoubleVariable());
        }

        b.is_best=this.is_best;
        b.is_sec_best=this.is_sec_best;

        b.nq=this.nq;
        b.rank=this.rank;
        b.sp=this.sp;
        b.distance=this.distance;
        //b.evafitness =this.evafitness;
        return b;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        MaShOADoubleSolution b = new MaShOADoubleSolution(new Hyperproblem());
        b.is_select=this.is_select;
        b.is_sec_best=this.is_sec_best;
        b.is_best=this.is_best;
        b.se=this.se;
        b.JF=this.JF;
        double fitness0=this.fitness[0];
        double fitness1=this.fitness[1];
        b.fitness[0]=fitness0;
        b.fitness[1]=fitness1;

        for(int i=0;i<this.variables.length;i++){
            b.variables[i].doubleVariable=(this.variables[i].getDoubleVariable());
        }

        b.is_best=this.is_best;
        b.is_sec_best=this.is_sec_best;

        b.nq=this.nq;
        b.rank=this.rank;
        b.sp=this.sp;
        b.distance=this.distance;
        //b.evafitness =this.evafitness;
        return b;
    }
}

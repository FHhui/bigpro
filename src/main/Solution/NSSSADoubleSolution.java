package main.Solution;

import main.problem.Hyperproblem;
import main.problem.Multiproblem;
import main.problem.*;

import java.util.ArrayList;

public class NSSSADoubleSolution  extends solution implements Cloneable{
    //多目标松鼠的解类
    public double evafitness;//映射函数
    public DoubleVariable[] variables;//自变量集合
    public int[] location;//
    public double di;//密度
    public int nq;//个体被支配的次数
    public double distance;//拥挤度
    public int rank;//帕累托等级
    public double neighbor_distance;//最近的欧式距离
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
        newS.di=s.di;
        newS.neighbor_distance=s.neighbor_distance;
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
        this.neighbor_distance=Double.MAX_VALUE;
        this.di=0.0;
        this.distance=0.0;
    }
    public Object clone() throws CloneNotSupportedException
    {
        NSSSADoubleSolution b = new NSSSADoubleSolution(new ZDT1problem());

        double fitness0=this.fitness[0];
        double fitness1=this.fitness[1];
        b.fitness[0]=fitness0;
        b.fitness[1]=fitness1;


        b.location[0]=this.location[0];
        b.location[1]=this.location[1];

        b.evafitness=this.evafitness;
        b.nq=this.nq;
        b.distance=this.distance;
        b.rank=this.rank;
        b.is_best=this.is_best;
        b.is_sec_best=this.is_sec_best;
        b.sp=this.sp;
        b.di=this.di;
        b.neighbor_distance=this.neighbor_distance;
        for (int i=0;i<variables.length;i++){
            b.variables[i].doubleVariable=
                    this.variables[i].doubleVariable;
        }
        return b;
    }

}

package main.Solution;

import main.problem.Multi_Tsp;

import java.io.*;
import java.util.ArrayList;

public class SSAMultiTspSolution  extends solution implements Cloneable {
    //多目标Tsp问题
    public double[] fitness;//适应度评价标准1
    public ArrayList<Integer> city_cycle;
    public int[] location;//
    public boolean is_best;//是否到达过最优解
    public boolean is_sec_best;//是否到达过次优解
    public int nq;
    public int rank;//帕累托等级
    public double evafitness;
    public double distance;
    public ArrayList<SSAMultiTspSolution> sp;
    public SSAMultiTspSolution(){
        super();
        this.city_cycle=new ArrayList<>();
        this.nq=0;
        this.distance=0;
        this.rank=0;
        location=new int[2];
        fitness=new double[2];
        evafitness =0.0;
        this.sp=new ArrayList<>();
    }
    //clone方法虽然是一个好方法，但是会导致运行速度变慢等等不利于使用的操作，所以我们要重新写一个copy方法
    public static SSAMultiTspSolution copy(SSAMultiTspSolution a){
        SSAMultiTspSolution b=new SSAMultiTspSolution();
        b.fitness=a.fitness;
        b.city_cycle=a.city_cycle;
        b.location=a.location;
        b.is_best=a.is_best;
        b.is_sec_best=a.is_sec_best;
        b.nq=a.nq;
        b.rank=a.rank;
        b.sp=a.sp;
        b.distance=a.distance;
        b.evafitness =a.evafitness;
        //将A的所有属性全部赋值给b
        return b;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SSAMultiTspSolution b = new SSAMultiTspSolution();

        double fitness0=this.fitness[0];
        double fitness1=this.fitness[1];
        b.fitness[0]=fitness0;
        b.fitness[1]=fitness1;

        for(int i=0;i<this.city_cycle.size();i++){
            b.city_cycle.add(this.city_cycle.get(i));
        }

        b.location[0]=this.location[0];
        b.location[1]=this.location[1];

        b.is_best=this.is_best;
        b.is_sec_best=this.is_sec_best;

        b.nq=this.nq;
        b.rank=this.rank;
        b.sp=this.sp;
        b.distance=this.distance;
        b.evafitness =this.evafitness;

        return b;
    }

}

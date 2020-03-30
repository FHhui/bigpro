package main.Solution;

import main.problem.Singleproblem;

public class BADoubleSolution extends solution{
    //蝙蝠算法的浮点数解集
    public DoubleVariable[] variables;//自变量集合
    public double r0=0.7;
    public double A=0.9;
    public double Rf=0.9;
    public double fmin=-1.00;//最小频率
    public double fmax=1.5;//最大频率
    public double v;//速度数组
    public double v_n;//以前的速度数组
    //DoubleVariable[] x_n;//以前的位置数组
    public double f;//频率数组
    public BADoubleSolution(Singleproblem p) {
        super(p);
        f=0.0;
        v_n=0;
        //x_n
        v=0.0;
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
      //      x_n[i]=new DoubleVariable();
        }
    }
}

package main.Operator;

import main.Algorithm.NSSSA;
import main.Algorithm.SSA;
import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.SSADoubleSolutionSet;
import main.Solution.solutionSet;
import main.problem.*;

public class SSASeasonChange extends operator{
    //季节变化算子
    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, Multiproblem p, int t, int generation){
        //具体操作,s是解集，t是当前迭代次数,generation是迭代次数,问题p

        double Sct = 0;
        double Smin = 1 / Math.pow(365, 2.5 * t / generation);
        for (int i = NSSSA.is_best; i < NSSSA.is_best+NSSSA.is_sec_best; i++) {
            for (int j = 0; j < s.array.get(0).variables.length; j++) {
                Sct += (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable)
                        * (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable);
            }
            Sct = Math.sqrt(Sct);
        }
        if (Sct < Smin) {
            System.out.println("2121321321321");
            for (int i = NSSSA.is_best+NSSSA.is_sec_best; i < s.array.size(); i++) {
                if (s.array.get(i).is_best == false) {
                    double levy=Levy();
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].doubleVariable=  p.lowerlimit.get(j)+ levy * (p.upperlimit.get(j) - p.lowerlimit.get(j));
                    }
                } else
                    continue;
            }
        }
        //季节变化条件结束代表着所有的自变量改变结束，因此在这里更新适应值
        for (int i=0;i<s.array.size();i++){
            ZDT4problem p1=(ZDT4problem) p;
            s.array.set(i,p1.evalute(s.array.get(i)));
        }
        return s;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s, Hyperproblem p, int t, int generation){
        //具体操作,s是解集，t是当前迭代次数,generation是迭代次数,问题p
        double Sct = 0;
        double Smin = 1 / Math.pow(365, 2.5 * t / generation);
        for (int i = NSSSA.is_best; i < NSSSA.is_best+NSSSA.is_sec_best; i++) {
            for (int j = 0; j < s.array.get(0).variables.length; j++) {
                Sct += (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable)
                        * (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable);
            }
            Sct = Math.sqrt(Sct);
        }
        if (Sct < Smin) {
            for (int i = NSSSA.is_best+NSSSA.is_sec_best; i < s.array.size(); i++) {
                if (s.array.get(i).is_best == false) {
                    double levy=Levy();
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].doubleVariable=  p.lowerlimit.get(j)+ levy * (p.upperlimit.get(j) - p.lowerlimit.get(j));
                    }
                } else
                    continue;
            }
        }
        //季节变化条件结束代表着所有的自变量改变结束，因此在这里更新适应值
        for (int i=0;i<s.array.size();i++){
            DTLZ1 p1=(DTLZ1) p;
            s.array.set(i,p1.evalute(s.array.get(i)));
        }
        return s;
    }
    public SSADoubleSolutionSet execute(SSADoubleSolutionSet s, Singleproblem p , int t, int generation){
        //具体操作,s是解集，t是当前迭代次数,generation是迭代次数,问题p
        double Sct = 0;
        double Smin = 1 / Math.pow(365, 2.5 * t / generation);
        for (int i = SSA.is_best; i < SSA.is_best+SSA.is_sec_best; i++) {
            for (int j = 0; j < s.array.get(0).variables.length; j++) {
                Sct += (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable)
                        * (s.array.get(i).variables[j].doubleVariable - s.array.get(0).variables[j].doubleVariable);
            }
            Sct = Math.sqrt(Sct);
        }
        if (Sct < Smin) {
            for (int i = SSA.is_best+ SSA.is_sec_best; i < s.array.size(); i++) {
                if (s.array.get(i).is_best == false) {
                    double levy=Levy();
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].doubleVariable=  p.lowerlimit.get(j)+ levy * (p.upperlimit.get(j) - p.lowerlimit.get(j));
                    }
                } else
                    continue;
            }
        }
        for (int i=0;i<s.array.size();i++){
            RGAproblem p1=(RGAproblem) p;
            s.array.set(i,p1.evalute(s.array.get(i)));
        }
        return s;
    }
    private double Factorial(double n) // 阶乘
    {
        int m = 10000;
        double An = 1;
        for (int i = 1; i <= m - 1; i++) {
            An *= i / (i + n);
        }
        An = An * m * Math.pow((m + n / 2), n - 1);
        return An;
    }

    private double Levy() // 列维函数
    {
        double ra = Math.random();
        ra=Math.exp(-ra*ra/2)/Math.sqrt(2*Math.PI);//[0,1]区间上的高斯函数r1
        double rb = Math.random();
        rb=Math.exp(-rb*rb/2)/Math.sqrt(2*Math.PI);//[0,1]区间上的高斯函数r2
        double beta = 1.5;
        double sigma = Math.pow(((Factorial(beta) * Math.sin(Math.PI * beta / 2))
                / (Factorial((beta - 1) / 2) * beta * Math.pow(2, ((beta - 1) / 2)))), 1 / beta);
        double levy = 1 * ra * sigma / (Math.pow((Math.abs(rb)), 1 / beta));
        return levy;
    }
}

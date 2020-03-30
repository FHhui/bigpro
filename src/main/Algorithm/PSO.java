package main.Algorithm;

import main.Operator.LocalDoubleSearch;
import main.Operator.PSODoubleRandominit;
import main.Solution.PSODoubleSolutionSet;
import main.problem.RGAproblem;
import main.problem.Singleproblem;

public class PSO extends SingleAlogorithm {
    int generation;
    int humans;
    double c1;//学习银子
    double c2;//
    double w;//惯性因子
    double xh;//随机跳动因子
    public PSODoubleSolutionSet run(Singleproblem p) {
        return getResult(p);
    }

    public PSODoubleSolutionSet getResult(Singleproblem p) {
        PSODoubleSolutionSet s=new PSODoubleSolutionSet(humans);
        PSODoubleRandominit PSDR=new PSODoubleRandominit();
        s=PSDR.execute(s,p);
        RGAproblem p1=(RGAproblem) p;
        for (int i=0;i<generation;i++){
            //更新速度的公式
            for (int j=0;j<p.getNumberOfVariables();j++){
                    double v =w * s.array.get(i).v[j]+
                            c1*Math.random()* (s.array.get(i).variables_best[j].doubleVariable-s.array.get(i).variables[j].doubleVariable)
                            +c2*Math.random()*(s.array.get(s.whichbest).variables_best[j].doubleVariable-s.array.get(i).variables[j].doubleVariable);

                    if (v>s.array.get(i).vmax[j]){
                        v=s.array.get(i).vmax[j];
                    }else if (v<-s.array.get(i).vmax[j]){
                        v=-s.array.get(i).vmax[j];
                    }
                    s.array.get(i).v[j]=v;
                    s.array.get(i).variables[j].doubleVariable+=s.array.get(i).v[j];
//                    if (Math.random()<xh){
//                    s.array.get(i).variables[j].doubleVariable=p.lower+(p.upper-p.lower)*Math.random();
//                }
                    if (s.array.get(i).variables[j].doubleVariable>p.upperlimit.get(j)){
                        s.array.get(i).variables[j].doubleVariable=p.upperlimit.get(j)-0.01;
                }else if (s.array.get(i).variables[j].doubleVariable<p.lowerlimit.get(j)){
                        s.array.get(i).variables[j].doubleVariable=p.lowerlimit.get(j)+0.01;
                }
                    s.array.set(i,p1.evalute(s.array.get(i)));
                    if (s.array.get(i).fitness[0]<s.array.get(i).fitness_best[0]){
                        s.array.get(i).fitness_best[0]=s.array.get(i).fitness[0];
                        s.array.get(i).variables_best=s.array.get(i).variables;
                    }

            }
            LocalDoubleSearch lds=new LocalDoubleSearch();
            s=lds.execute(s);
            System.out.println(s.array.get(s.whichbest).fitness_best[0]);
        }
        return s;
    }
    public PSO(int generation,int humans){
        this.generation=generation;
        this.humans=humans;
        this.w=0.01;
        this.c1=10;
        this.c2=10;
        this.xh=0.9;  }
    public static void main(String args[]){
        PSO test=new PSO(50,50);
        RGAproblem p=new RGAproblem();
        test.getResult(p);
    }
}

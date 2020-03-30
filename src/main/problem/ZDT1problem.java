package main.problem;

import main.Operator.NSSSADoubleSolution;
import main.Solution.NSGADoubleSolution;

import java.util.ArrayList;

public class ZDT1problem extends Multiproblem{
    //zdt1问题，多目标问题
    public ZDT1problem(){
        super();
        this.numberOfObjectives=2;
        this.numberOfVariables=30;
        this.lowerlimit=new ArrayList<>();
        this.upperlimit=new ArrayList<>();
        for (int i=0;i<this.numberOfVariables;i++){
            lowerlimit.add(0.0);
            upperlimit.add(1.0);
        }
    }
    public NSSSADoubleSolution evalute(NSSSADoubleSolution s){
        double dou =s.variables[0].getDoubleVariable();
        s.fitness[0]=dou;
        double g=1,sum=0;
        for (int i=1;i<s.variables.length;i++){
            g+=s.variables[i].getDoubleVariable();
        }
        sum=9/ (s.variables.length-1);
        g=sum*g;
        g=1.0+g;
        double h=1.0-Math.sqrt(dou/g);
        s.fitness[1]=h*g;
        return s;
    }
    public NSGADoubleSolution evalute(NSGADoubleSolution s){
        double dou =s.variables[0].getDoubleVariable();
        s.fitness[0]=dou;
        double g=1,sum=0;
        for (int i=1;i<s.variables.length;i++){
            g+=s.variables[i].getDoubleVariable();
        }
        sum=9/ (s.variables.length-1);
        g=sum*g;
        g=1.0+g;
        double h=1.0-Math.sqrt(dou/g);
        s.fitness[1]=h*g;
        return s;
    }
}

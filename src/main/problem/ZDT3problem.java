package main.problem;

import main.Solution.NSSSADoubleSolution;

import java.util.ArrayList;

public class ZDT3problem extends Multiproblem {
    //ZDT3
    public ZDT3problem(){
        super();
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
        double h= 1.0 - Math.sqrt(dou / g)
                - (dou / g) * Math.sin(10.0 * Math.PI * dou);;
        s.fitness[1]=h*g;
        return s;
    }

}

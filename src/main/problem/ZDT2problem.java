package main.problem;

import main.Solution.NSGADoubleSolution;
import main.Solution.NSSSADoubleSolution;

import java.util.ArrayList;

public class ZDT2problem extends Multiproblem{
    public ZDT2problem(){
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
        double h=1.0-Math.pow(dou/g,2.0);
        s.fitness[1]=h*g;
        return s;
    }
    public NSGADoubleSolution evalute(NSGADoubleSolution s){
        double[] f = new double[this.numberOfObjectives];
        //System.out.println(s.variables[0].doubleVariable);
        f[0] = s.variables[0].getDoubleVariable();
        double g = 0.0;
        for (int i = 1; i < s.variables.length; i++) {
            g += s.variables[i].getDoubleVariable();
        }
        double constant = 9.0 / (s.variables.length - 1);

        g= constant * g + 1.0;
        double h = 1.0 - Math.pow(f[0] / g, 2.0);;
        f[1] = h * g;

        s.fitness[0] = f[0];
        s.fitness[1] = f[1];
        return s;
    }

    public double evalH(double f, double g) {
        return 1.0 - Math.pow(f / g, 2.0);
    }
}

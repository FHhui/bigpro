package main.problem;

import main.Operator.NSSSADoubleSolution;

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
        double[] f = new double[getNumberOfObjectives()];

        f[0] = s.variables[0].getDoubleVariable();
        double g = 0.0;
        for (int i = 1; i < s.variables.length; i++) {
            g += s.variables[i].getDoubleVariable();
        }
        double constant = 9.0 / (s.variables.length - 1);

        g= constant * g + 1.0;
         double h = 1.0 - Math.sqrt(f[0] / g)
                - (f[0] / g) * Math.sin(10.0 * Math.PI * f[0]);
        f[1] = h * g;

        s.fitness[0] = f[0];
        s.fitness[1] = f[1];
        return s;
    }

}

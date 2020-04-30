package main.problem;

import main.Operator.NSSSADoubleSolution;

import java.util.ArrayList;

public class ZDT4problem extends Multiproblem{
    //多目标问题zdt4
    public ZDT4problem(){
        super();
        this.numberOfObjectives=2;
        this.numberOfVariables=10;
        this.lowerlimit=new ArrayList<>();
        lowerlimit.add(0.0);
        this.upperlimit=new ArrayList<>();
        upperlimit.add(1.0);
        for (int i=1;i<this.numberOfVariables;i++){
            lowerlimit.add(-5.0);
            upperlimit.add(5.0);
        }
    }
    public NSSSADoubleSolution evalute(NSSSADoubleSolution solution) {
        double[] f = new double[getNumberOfObjectives()];

        f[0] = solution.variables[0].getDoubleVariable();
        double g = this.evalG(solution);
        double h = this.evalH(f[0], g);
        f[1] = h * g;

        solution.fitness[0]= f[0];
        solution.fitness[1]= f[1];
        return solution;
    }
    public double evalG(NSSSADoubleSolution solution) {
        double g = 0.0;
        for (int var = 1; var < solution.variables.length; var++) {
            g += Math.pow(solution.variables[var].getDoubleVariable(), 2.0) +
                    -10.0 * Math.cos(4.0 * Math.PI * solution.variables[var].getDoubleVariable());
        }

        double constant = 1.0 + 10.0 * (solution.variables.length - 1);
        return g + constant;
    }

    /**
     * Returns the value of the ZDT4 function H.
     *
     * @param f First argument of the function H.
     * @param g Second argument of the function H.
     */
    public double evalH(double f, double g) {
        return 1.0 - Math.sqrt(f / g);
    }

}



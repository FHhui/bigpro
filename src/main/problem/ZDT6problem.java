package main.problem;

import main.Operator.NSSSADoubleSolution;

import java.util.ArrayList;

public class ZDT6problem extends Multiproblem{
    public ZDT6problem() {
        super();
        this.numberOfObjectives=2;
        this.numberOfVariables=10;
        this.lowerlimit=new ArrayList<>();
        this.upperlimit=new ArrayList<>();
        for (int i=0;i<this.numberOfVariables;i++){
            lowerlimit.add(0.0);
            upperlimit.add(1.0);
        }
    }
    public NSSSADoubleSolution evalute(NSSSADoubleSolution solution) {
        double[] f = new double[getNumberOfObjectives()];
        f[0] = solution.variables[0].getDoubleVariable();
        double g = this.evalG(solution);
        double h = this.evalH(f[0], g);
        f[1] = h * g;
        solution.fitness[0] = f[0];
        solution.fitness[1] = f[1];
        return solution;
    }

    /**
     * Returns the value of the ZDT1 function G.
     *
     * @param solution Solution
     */
    protected double evalG(NSSSADoubleSolution solution) {
        double g = 0.0;
        for (int i = 1; i < solution.variables.length; i++) {
            g += solution.variables[i].getDoubleVariable();
        }
        double constant = 9.0 / (solution.variables.length - 1);

        return constant * g + 1.0;
    }

    /**
     * Returns the value of the ZDT1 function H.
     *
     * @param f First argument of the function H.
     * @param g Second argument of the function H.
     */
    protected double evalH(double f, double g) {
        double h ;
        h = 1.0 - Math.sqrt(f / g);
        return h;
    }
}

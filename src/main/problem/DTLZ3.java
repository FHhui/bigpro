package main.problem;

import main.Solution.MaShOADoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class DTLZ3 extends Hyperproblem{
    public DTLZ3() {
        super();
        this.numberOfVariables=12;
        this.numberOfObjectives=3;
        setName("DTLZ3");
        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
        List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;
        for (int i = 0; i < getNumberOfVariables(); i++) {
            lowerLimit.add(0.0);
            upperLimit.add(1.0);
        }
    }

    /** Evaluate() method */
    public MaShOADoubleSolution evaluate(MaShOADoubleSolution solution) {
        int numberOfVariables = getNumberOfVariables();
        int numberOfObjectives = getNumberOfObjectives();
        double[] f = new double[numberOfObjectives];
        double[] x = new double[numberOfVariables] ;

        for (int i = 0; i < numberOfVariables; i++) {
            x[i] = solution.variables[i].getDoubleVariable() ;
        }

        int k = getNumberOfVariables() - getNumberOfObjectives() + 1;

        double g = 0.0;
        for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
            g += (x[i] - 0.5) * (x[i] - 0.5) - Math.cos(20.0 * Math.PI * (x[i] - 0.5));
        }

        g = 100.0 * (k + g);
        for (int i = 0; i < numberOfObjectives; i++) {
            f[i] = 1.0 + g;
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                f[i] *= java.lang.Math.cos(x[j] * 0.5 * java.lang.Math.PI);
            }
            if (i != 0) {
                int aux = numberOfObjectives - (i + 1);
                f[i] *= java.lang.Math.sin(x[aux] * 0.5 * java.lang.Math.PI);
            }
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            solution.fitness[i]= f[i];
        }
        return solution;
    }
}

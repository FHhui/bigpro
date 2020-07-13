package main.problem;

import main.Solution.MaShOADoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class DTLZ6 extends Hyperproblem{

    public DTLZ6() {
        this(12, 3);
    }

    /**
     * Creates a DTLZ6 problem instance
     *
     * @param numberOfVariables  Number of variables
     * @param numberOfObjectives Number of objective functions
     */
    public DTLZ6(Integer numberOfVariables, Integer numberOfObjectives)  {
        setNumberOfVariables(numberOfVariables);
        setNumberOfObjectives(numberOfObjectives);
        setName("DTLZ6");

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
        int numberOfObjectives = getNumberOfObjectives() ;
        double[] theta = new double[numberOfObjectives - 1];

        double[] f = new double[numberOfObjectives];
        double[] x = new double[numberOfVariables] ;

        int k = getNumberOfVariables() - getNumberOfObjectives() + 1;

        for (int i = 0; i < numberOfVariables; i++) {
            x[i] = solution.variables[i].getDoubleVariable() ;
        }

        double g = 0.0;
        for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
            g += java.lang.Math.pow(x[i], 0.1);
        }

        double t = java.lang.Math.PI / (4.0 * (1.0 + g));
        theta[0] = x[0] * java.lang.Math.PI / 2;
        for (int i = 1; i < (numberOfObjectives - 1); i++) {
            theta[i] = t * (1.0 + 2.0 * g * x[i]);
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            f[i] = 1.0 + g;
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                f[i] *= java.lang.Math.cos(theta[j]);
            }
            if (i != 0) {
                int aux = numberOfObjectives - (i + 1);
                f[i] *= java.lang.Math.sin(theta[aux]);
            }
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            solution.fitness[i] = f[i];
        }
        return solution;
    }
}


package main.problem;

import main.Solution.MaShOADoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class DTLZ7 extends Hyperproblem{

    public DTLZ7() {
        this(22, 3);
    }

    /**
     * Creates a DTLZ7 problem instance
     *
     * @param numberOfVariables  Number of variables
     * @param numberOfObjectives Number of objective functions
     */
    public DTLZ7(Integer numberOfVariables, Integer numberOfObjectives){
        setNumberOfVariables(numberOfVariables);
        setNumberOfObjectives(numberOfObjectives);
        setName("DTLZ7");

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

        double[] f = new double[numberOfObjectives];
        double[] x = new double[numberOfVariables] ;

        int k = getNumberOfVariables() - getNumberOfObjectives() + 1;

        for (int i = 0; i < numberOfVariables; i++) {
            x[i] = solution.variables[i].getDoubleVariable() ;
        }

        double g = 0.0;
        for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
            g += x[i];
        }

        g = 1 + (9.0 * g) / k;

        System.arraycopy(x, 0, f, 0, numberOfObjectives - 1);

        double h = 0.0;
        for (int i = 0; i < numberOfObjectives - 1; i++) {
            h += (f[i] / (1.0 + g)) * (1 + Math.sin(3.0 * Math.PI * f[i]));
        }

        h = numberOfObjectives - h;

        f[numberOfObjectives - 1] = (1 + g) * h;

        for (int i = 0; i < numberOfObjectives; i++) {
            solution.fitness[i] = f[i];
        }
        return solution;
    }
}

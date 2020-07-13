package main.problem;
import main.Solution.MaShOADoubleSolution;
import java.util.ArrayList;
import java.util.List;

public class DTLZ2 extends Hyperproblem{
    public DTLZ2(){
        super();
        this.numberOfObjectives=3;
        this.numberOfVariables=12;
        setNumberOfVariables(numberOfVariables);
        setNumberOfObjectives(numberOfObjectives);
        setName("DTLZ2");
        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
        List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;



        for (int i=0;i<this.numberOfVariables;i++){
            lowerlimit.add(0.0);
            upperlimit.add(1.0);
        }
    }
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
            g += (x[i] - 0.5) * (x[i] - 0.5);
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            f[i] = 1.0 + g;
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                f[i] *= Math.cos(x[j] * 0.5 * Math.PI);
            }
            if (i != 0) {
                int aux = numberOfObjectives - (i + 1);
                f[i] *= Math.sin(x[aux] * 0.5 * Math.PI);
            }
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            solution.fitness[i] = f[i];
        }
        return solution;
    }
}

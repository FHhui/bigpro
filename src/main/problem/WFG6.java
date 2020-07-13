package main.problem;

import main.Solution.MaShOADoubleSolution;
import main.Solution.solution;

public class WFG6 extends WFG{
    public WFG6() {
        this(2, 4, 2);
    }

    /**
     * Creates a WFG6 problem instance
     *
     * @param k            Number of position parameters
     * @param l            Number of distance parameters
     * @param m            Number of objective functions
     */
    public WFG6(Integer k, Integer l, Integer m) {
        super(k, l, m);
        setName("WFG6");

        s = new int[m];
        for (int i = 0; i < m; i++) {
            s[i] = 2 * (i + 1);
        }

        a = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            a[i] = 1;
        }
    }

    /** Evaluate() method */
    public float[] evaluate(float[] z) {
        float[] y;

        y = normalise(z);
        y = t1(y, k);
        y = t2(y, k, m);

        float[] result = new float[m];
        float[] x = calculateX(y);
        for (int m = 1; m <= this.m; m++) {
            result[m - 1] = d * x[this.m - 1] + s[m - 1] * (new Shapes()).concave(x, m);
        }

        return result;
    }

    /**
     * WFG6 t1 transformation
     */
    public float[] t1(float[] z, int k) {
        float[] result = new float[z.length];

        System.arraycopy(z, 0, result, 0, k);

        for (int i = k; i < z.length; i++) {
            result[i] = (new Transformations()).sLinear(z[i], (float) 0.35);
        }

        return result;
    }

    /**
     * WFG6 t2 transformation
     */
    public float[] t2(float[] z, int k, int M) {
        float[] result = new float[M];

        for (int i = 1; i <= M - 1; i++) {
            int head = (i - 1) * k / (M - 1) + 1;
            int tail = i * k / (M - 1);
            float[] subZ = subVector(z, head - 1, tail - 1);

            result[i - 1] = (new Transformations()).rNonsep(subZ, k / (M - 1));
        }

        int head = k + 1;
        int tail = z.length;
        int l = z.length - k;

        float[] subZ = subVector(z, head - 1, tail - 1);
        result[M - 1] = (new Transformations()).rNonsep(subZ, l);

        return result;
    }

    public MaShOADoubleSolution evaluate(MaShOADoubleSolution solution) {
        float[] variables = new float[getNumberOfVariables()];
        double[] x = new double[getNumberOfVariables()];

        for (int i = 0; i < getNumberOfVariables(); i++) {
            x[i] = solution.variables[i].getDoubleVariable();
        }

        for (int i = 0; i < getNumberOfVariables(); i++) {
            variables[i] = (float) x[i] ;
        }

        float[] sol2 = evaluate(variables);

        for (int i = 0; i < sol2.length; i++) {
            solution.fitness[i] = sol2[i];
        }
        return solution;
    }
}

package main.problem;

import main.Solution.MaShOADoubleSolution;
import main.Solution.solution;

public class WFG4 extends WFG{
    public WFG4() {
        this(2, 4, 2);
    }

    /**
     * Creates a WFG4 problem instance
     *
     * @param k            Number of position parameters
     * @param l            Number of distance parameters
     * @param m            Number of objective functions
     */
    public WFG4(Integer k, Integer l, Integer m) {
        super(k, l, m);
        setName("WFG4");

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
     * WFG4 t1 transformation
     */
    public float[] t1(float[] z, int k) {
        float[] result = new float[z.length];

        for (int i = 0; i < z.length; i++) {
            result[i] = (new Transformations()).sMulti(z[i], 30, 10, (float) 0.35);
        }

        return result;
    }

    /**
     * WFG4 t2 transformation
     */
    public float[] t2(float[] z, int k, int M) {
        float[] result = new float[M];
        float[] w = new float[z.length];

        for (int i = 0; i < z.length; i++) {
            w[i] = (float) 1.0;
        }

        for (int i = 1; i <= M - 1; i++) {
            int head = (i - 1) * k / (M - 1) + 1;
            int tail = i * k / (M - 1);
            float[] subZ = subVector(z, head - 1, tail - 1);
            float[] subW = subVector(w, head - 1, tail - 1);

            result[i - 1] = (new Transformations()).rSum(subZ, subW);
        }

        int head = k + 1;
        int tail = z.length;

        float[] subZ = subVector(z, head - 1, tail - 1);
        float[] subW = subVector(w, head - 1, tail - 1);
        result[M - 1] = (new Transformations()).rSum(subZ, subW);

        return result;
    }

    public MaShOADoubleSolution evaluate(MaShOADoubleSolution solution) {
        float[] variables = new float[this.getNumberOfVariables()];
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

package main.problem;

import main.Algorithm.MaShOA;
import main.Solution.MaShOADoubleSolution;
import main.Solution.solution;

public class WFG2 extends WFG{
    public WFG2() {
        this(2, 4, 2);
    }
    public WFG2(Integer k, Integer l, Integer m) {
        super(k, l, m);
        setName("WFG2");

        s = new int[m];
        for (int i = 0; i < m; i++) {
            s[i] = 2 * (i + 1);
        }

        a = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            a[i] = 1;
        }
    }
    public float[] evaluate(float[] z) {
        float[] y;

        y = normalise(z);
        y = t1(y, k);
        y = t2(y, k);
        y = t3(y, k, m);

        float[] result = new float[m];
        float[] x = calculateX(y);
        for (int m = 1; m <= this.m - 1; m++) {
            result[m - 1] = d * x[this.m - 1] + s[m - 1] * (new Shapes()).convex(x, m);
        }
        result[m - 1] =
                d * x[m - 1] + s[m - 1] * (new Shapes()).disc(x, 5, (float) 1.0, (float) 1.0);

        return result;
    }
    public float[] t1(float[] z, int k) {
        float[] result = new float[z.length];

        System.arraycopy(z, 0, result, 0, k);

        for (int i = k; i < z.length; i++) {
            result[i] = (new Transformations()).sLinear(z[i], (float) 0.35);
        }

        return result;
    }
    public float[] t2(float[] z, int k) {
        float[] result = new float[z.length];

        System.arraycopy(z, 0, result, 0, k);

        int l = z.length - k;

        for (int i = k + 1; i <= k + l / 2; i++) {
            int head = k + 2 * (i - k) - 1;
            int tail = k + 2 * (i - k);
            float[] subZ = subVector(z, head - 1, tail - 1);

            result[i - 1] = (new Transformations()).rNonsep(subZ, 2);
        }

        return result;
    }
    public float[] t3(float[] z, int k, int M) {
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

        int l = z.length - k;
        int head = k + 1;
        int tail = k + l / 2;

        float[] subZ = subVector(z, head - 1, tail - 1);
        float[] subW = subVector(w, head - 1, tail - 1);
        result[M - 1] = (new Transformations()).rSum(subZ, subW);

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

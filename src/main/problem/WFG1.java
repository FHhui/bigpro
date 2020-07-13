package main.problem;

import main.Algorithm.MaShOA;
import main.Solution.MaShOADoubleSolution;
import main.Solution.solution;

import java.util.logging.Level;

public class WFG1 extends WFG{
    public WFG1(Integer k, Integer l, Integer m) {
        super(k, l, m);
        setName("WFG1");
        setNumberOfVariables(this.k + this.l);
        this.numberOfVariables=k+l;
        setNumberOfObjectives(this.m);
        this.numberOfObjectives=m;
        setNumberOfConstraints(0);
        this.numberOfConstraints=0;

        s = new int[m];
        for (int i = 0; i < m; i++) {
            s[i] = 2 * (i + 1);
        }

        a = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            a[i] = 1;
        }
    }
    @Override
    public solution evalute(solution s) {
        return null;
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

        for (int i = k; i < z.length; i++) {
            result[i] = (new Transformations()).bFlat(z[i], (float) 0.8, (float) 0.75, (float) 0.85);
        }

        return result;
    }
    public float[] t3(float[] z){
        float[] result = new float[z.length];

        for (int i = 0; i < z.length; i++) {
            result[i] = (new Transformations()).bPoly(z[i], (float) 0.02);
        }

        return result;
    }
    public float[] t4(float[] z, int k, int M) {
        float[] result = new float[M];
        float[] w = new float[z.length];

        for (int i = 0; i < z.length; i++) {
            w[i] = (float) 2.0 * (i + 1);
        }

        for (int i = 1; i <= M - 1; i++) {
            int head = (i - 1) * k / (M - 1) + 1;
            int tail = i * k / (M - 1);
            float[] subZ = subVector(z, head - 1, tail - 1);
            float[] subW = subVector(w, head - 1, tail - 1);

            result[i - 1] = (new Transformations()).rSum(subZ, subW);
        }

        int head = k + 1 - 1;
        int tail = z.length - 1;
        float[] subZ = subVector(z, head, tail);
        float[] subW = subVector(w, head, tail);
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
        float[] f = evaluate(variables);
        for (int i = 0; i < f.length; i++) {
            solution.fitness[i]= f[i];
        }
        return solution;
    }
    public float[] evaluate(float[] z) {
        float[] y;
        y = normalise(z);
        y = t1(y, k);
        y = t2(y, k);
        y = t3(y);
        y = t4(y, k, m);
        float[] result = new float[m];
        float[] x = calculateX(y);
        for (int m = 1; m <= this.m - 1; m++) {
            result[m - 1] = d * x[this.m - 1] + s[m - 1] * (new Shapes()).convex(x, m);
        }
        result[m - 1] = d * x[m - 1] + s[m - 1] * (new Shapes()).mixed(x, 5, (float) 1.0);
        return result;
    }
}

package main.problem;

import main.Solution.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WFG extends Hyperproblem{
    private final float epsilon = (float) 1e-7;
    protected int k;
    protected int m;
    protected int l;
    protected int[] a;
    protected int[] s;
    protected int d = 1;
    protected Random random = new Random();

    public WFG(Integer k, Integer l, Integer M) {
        this.k = k;
        this.l = l;
        this.m = M;

        setNumberOfVariables(this.k + this.l);
        this.numberOfVariables=k+l;
        setNumberOfObjectives(this.m);
        this.numberOfObjectives=m;
        setNumberOfConstraints(0);
        this.numberOfConstraints=0;

        this.lowerlimit = new ArrayList<>(getNumberOfVariables()) ;
        this.upperlimit = new ArrayList<>(getNumberOfVariables()) ;

        for (int i = 0; i < getNumberOfVariables(); i++) {
            lowerlimit.add(0.0);
            upperlimit.add(2.0*(i+1));
        }
    }
    public float[] calculateX(float[] t) {
        float[] x = new float[m];

        for (int i = 0; i < m - 1; i++) {
            x[i] = Math.max(t[m - 1], a[i]) * (t[i] - (float) 0.5) + (float) 0.5;
        }

        x[m - 1] = t[m - 1];

        return x;
    }

    public float[] normalise(float[] z) {
        float[] result = new float[z.length];

        for (int i = 0; i < z.length; i++) {
            float bound = (float) 2.0 * (i + 1);
            result[i] = z[i] / bound;
            result[i] = correctTo01(result[i]);
        }

        return result;
    }
    public float correctTo01(float a) {
        float min = (float) 0.0;
        float max = (float) 1.0;

        float minEpsilon = min - epsilon;
        float maxEpsilon = max + epsilon;

        if ((a <= min && a >= minEpsilon) || (a >= min && a <= minEpsilon)) {
            return min;
        } else if ((a >= max && a <= maxEpsilon) || (a <= max && a >= maxEpsilon)) {
            return max;
        } else {
            return a;
        }
    }
    public float[] subVector(float[] z, int head, int tail) {
        int size = tail - head + 1;
        float[] result = new float[size];

        System.arraycopy(z, head, result, head - head, tail + 1 - head);

        return result;
    }
    public solution createSolution() {
        return new solution(this)  ;
    }

}

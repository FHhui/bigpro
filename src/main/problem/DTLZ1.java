package main.problem;

import main.Operator.NSSSADoubleSolution;
import main.Solution.DoubleVariable;
import main.Solution.NSGAIIIDoubleSolution;
import main.Solution.solution;

import java.util.ArrayList;

public class DTLZ1 extends Hyperproblem{
    //DTLZ1测试函数，没有错这就是一个三维函数
    public DTLZ1(){
        super();
        this.numberOfVariables=10;
        this.lowerlimit=new ArrayList<>();

        this.upperlimit=new ArrayList<>();

        for (int i=0;i<this.numberOfVariables;i++){
            lowerlimit.add(0.0);
            upperlimit.add(1.0);
        }
    }
    //看着很怪异也不要紧，反正是抄的jmetal的，hhhhh
    public NSSSADoubleSolution evalute(NSSSADoubleSolution solution){
        //计算多目标松鼠适应值,改泛型改泛型改泛型重要的事情说三遍
            int numberOfVariables = solution.variables.length;
            int numberOfObjectives = solution.fitness.length ;

            double[] f = new double[numberOfObjectives];
            DoubleVariable[] x = new DoubleVariable[numberOfVariables] ;

            int k =  numberOfVariables  - numberOfObjectives + 1;
           // System.out.println(numberOfVariables+"         "+numberOfObjectives);
            for (int i = 0; i < numberOfVariables; i++) {

                x[i] = (DoubleVariable) solution.variables[i];
                //System.out.println(x[i]);
            }

            double g = 0.0;
            for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
                g += (x[i].getDoubleVariable() - 0.5) * (x[i].getDoubleVariable() - 0.5) - Math.cos(20.0 * Math.PI * (x[i].getDoubleVariable() - 0.5));
            }

            g = 100 * (k + g);
            for (int i = 0; i < numberOfObjectives; i++) {
                f[i] = (1.0 + g) * 0.5;
            }

            for (int i = 0; i < numberOfObjectives; i++) {
                for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                    f[i] *= x[j].getDoubleVariable();
                }
                if (i != 0) {
                    int aux = numberOfObjectives - (i + 1);
                    f[i] *= 1 - x[aux].getDoubleVariable();
                }
            }

            for (int i = 0; i < numberOfObjectives; i++) {
                solution.fitness[i]= f[i];
            }
        return solution;
    }
    public <S extends solution> S eval(S solution){
        int numberOfVariables = solution.variables.length;
        int numberOfObjectives = solution.fitness.length ;

        double[] f = new double[numberOfObjectives];
        DoubleVariable[] x = new DoubleVariable[numberOfVariables] ;

        int k =  numberOfVariables  - numberOfObjectives + 1;
        // System.out.println(numberOfVariables+"         "+numberOfObjectives);
        for (int i = 0; i < numberOfVariables; i++) {

            x[i] = (DoubleVariable) solution.variables[i];
            //System.out.println(x[i]);
        }

        double g = 0.0;
        for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
            g += (x[i].getDoubleVariable() - 0.5) * (x[i].getDoubleVariable() - 0.5) - Math.cos(20.0 * Math.PI * (x[i].getDoubleVariable() - 0.5));
        }

        g = 100 * (k + g);
        for (int i = 0; i < numberOfObjectives; i++) {
            f[i] = (1.0 + g) * 0.5;
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                f[i] *= x[j].getDoubleVariable();
            }
            if (i != 0) {
                int aux = numberOfObjectives - (i + 1);
                f[i] *= 1 - x[aux].getDoubleVariable();
            }
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            solution.fitness[i]= f[i];
        }
        return (S)solution;
    }
    public NSGAIIIDoubleSolution evalute(NSGAIIIDoubleSolution solution){
        //计算多目标松鼠适应值,改泛型改泛型改泛型重要的事情说三遍
        int numberOfVariables = solution.variables.length;
        int numberOfObjectives = solution.fitness.length ;

        double[] f = new double[numberOfObjectives];
        DoubleVariable[] x = new DoubleVariable[numberOfVariables] ;

        int k =  numberOfVariables  - numberOfObjectives + 1;
        // System.out.println(numberOfVariables+"         "+numberOfObjectives);
        for (int i = 0; i < numberOfVariables; i++) {

            x[i] = solution.variables[i] ;
            //System.out.println(x[i]);
        }

        double g = 0.0;
        for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
            g += (x[i].getDoubleVariable() - 0.5) * (x[i].getDoubleVariable() - 0.5) - Math.cos(20.0 * Math.PI * (x[i].getDoubleVariable() - 0.5));
        }

        g = 100 * (k + g);
        for (int i = 0; i < numberOfObjectives; i++) {
            f[i] = (1.0 + g) * 0.5;
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                f[i] *= x[j].getDoubleVariable();
            }
            if (i != 0) {
                int aux = numberOfObjectives - (i + 1);
                f[i] *= 1 - x[aux].getDoubleVariable();
            }
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            solution.fitness[i]= f[i];
        }
        return solution;
    }
}

package main.Operator;

import main.Solution.solutionSet;

public class Mutation extends operator {
    double pm;//变异概率
    public Mutation(double pm){
        this.pm=pm;
    }
    @Override
    public void execute() {
    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
}

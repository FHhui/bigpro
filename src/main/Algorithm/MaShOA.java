package main.Algorithm;

import main.Operator.MaShOADoubleRandominit;
import main.Solution.MaShOADoubleSolution;
import main.Solution.MaShOADoubleSolutionSet;
import main.Solution.ReferencePoint;
import main.Solution.solutionSet;
import main.problem.Hyperproblem;
import main.problem.problem;

import java.util.List;
import java.util.Vector;

public class MaShOA extends HyperAlgorithm{
    //超多目标松鼠算法
    int generation;
    int popsize;
    Hyperproblem p;
    Vector<Integer> numberofDivisions;
    List<ReferencePoint<MaShOADoubleSolution>> referencePoints=new Vector<>();
    public MaShOA(int generation,int popsize,Hyperproblem p){
        this.generation=generation;
        this.popsize=popsize;
        this.numberofDivisions=new Vector<>(1);
        numberofDivisions.add(12);
        (new ReferencePoint<MaShOADoubleSolution>()).generateReferencePoints(referencePoints,p.getNumberOfObjectives(), numberofDivisions);
    }
    public MaShOADoubleSolutionSet getResult() {
        MaShOADoubleSolutionSet MDS=new MaShOADoubleSolutionSet(popsize);
        MaShOADoubleRandominit MDR=new MaShOADoubleRandominit();
        MDS=MDR.execute(MDS,p);
        for (int i=0;i<generation;i++){

        }
        return null;

    }

    @Override
    public solutionSet run(problem p) {
        return super.run(p);
    }
    public static void main(String args[]){

    }
}
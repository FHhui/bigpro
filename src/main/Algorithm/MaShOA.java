package main.Algorithm;

import main.Operator.*;
import main.Solution.MaShOADoubleSolution;
import main.Solution.MaShOADoubleSolutionSet;
import main.Solution.ReferencePoint;
import main.Solution.solutionSet;
import main.problem.DTLZ1;
import main.problem.Hyperproblem;
import main.problem.problem;

import java.util.List;
import java.util.Vector;

public class MaShOA extends HyperAlgorithm{
    //超多目标松鼠算法
    int generation;
    int popsize;

    public  static  int is_best;
    public static int is_sec_best;

    Hyperproblem p;
    Vector<Integer> numberofDivisions;
    List<ReferencePoint<MaShOADoubleSolution>> referencePoints=new Vector<>();
    public MaShOA(int generation,int popsize,Hyperproblem p){
        this.generation=generation;
        this.popsize=popsize;
        this.is_best=(int)popsize/50;
        this.is_sec_best=(int)3*popsize/50;
        this.numberofDivisions=new Vector<>(1);
        numberofDivisions.add(12);
        (new ReferencePoint<MaShOADoubleSolution>()).generateReferencePoints(referencePoints,p.getNumberOfObjectives(), numberofDivisions);
    }
    public MaShOADoubleSolutionSet getResult() {
        MaShOADoubleSolutionSet MDS=new MaShOADoubleSolutionSet(popsize);
        MaShOADoubleRandominit MDR=new MaShOADoubleRandominit();
        MDS=MDR.execute(MDS,p);
        for (int i=0;i<generation;i++){
            MaShOADoubleSolutionSet parent=MDS.copy(p);
            MaShOAinitJF MSR=new MaShOAinitJF(MDS);
            MDS=MSR.execute(this.referencePoints,i);
            //这里是计算完JF的种群
            //利用JF实现冒泡排序
            SortMaoPao sm=new SortMaoPao();
            sm.execute(MDS);
            //实际上接下来就是利用JF实现的单目标松鼠
            for (int j=0;j<MDS.array.size();j++){
                if (i<is_best){
                    MDS.array.get(j).is_best=true;
                }else if (i<is_best+is_sec_best){
                    MDS.array.get(j).is_sec_best=true;
                }
            }
            SSANewDisplacePlace SDP=new SSANewDisplacePlace();
            MDS=SDP.execute(MDS,p);
            SSASeasonChange SC=new SSASeasonChange();
            MDS=SC.execute(MDS,p,i,generation);
            //松鼠跳跃部分实现完毕
            //父子代迭代得到正确的解
            MaShOAGeneration MOG=new MaShOAGeneration();
            MDS=MOG.execute(parent,MDS,p,this.referencePoints,i);
            //MDS就是解
            //参考点迭代的部分
            DTLZ1 mp=(DTLZ1)p;
            for (int g=0;g<MDS.size;g++){
                MaShOADoubleSolution a=mp.eval(MDS.array.get(g));
                MDS.array.set(g,a);
            }
            //重新计算fitness
            MDS=new NSGAFastNonSort().execute(MDS);
            for (MaShOADoubleSolution a:MDS.array){
                if (a.rank==1){
                    for (int h=0;h<a.fitness.length;h++){
                        System.out.println(a.fitness[h]);
                    }
                    System.out.println(" ");
                }
            }
            RefGeneration RG=new RefGeneration();
            this.referencePoints=RG.run(MDS,referencePoints);
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

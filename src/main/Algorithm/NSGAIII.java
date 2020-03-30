package main.Algorithm;

import main.Operator.*;
import main.Solution.NSGAIIIDoubleSolution;
import main.Solution.NSGAIIIDoubleSolutionSet;
import main.Solution.ReferencePoint;
import main.Solution.solutionSet;
import main.problem.DTLZ1;
import main.problem.Hyperproblem;

import java.util.List;
import java.util.Vector;

/*author：FHhui
* description: NSGAIII by java
* */
public class NSGAIII extends HyperAlgorithm{
    //NSGAIII算法
    int generation;
    int popsize;
    double pc;
    double r;
    double pm;
    double k;
    Hyperproblem p;
    Vector<Integer> numberofDivisions;
    List<ReferencePoint<NSGAIIIDoubleSolution>> referencePoints = new Vector<>() ;
    public NSGAIII(int generation, int popsize, Hyperproblem p){
        this.generation=generation;
        this.popsize=popsize;
        this.p=p;
        this.pc=0.8;
        this.r=0.6;
        this.k=0.4;
        this.pm=0.5;

        this.numberofDivisions=new Vector<>(1);
        numberofDivisions.add(12);//划分数
        (new ReferencePoint<NSGAIIIDoubleSolution>()).generateReferencePoints(referencePoints,p.getNumberOfObjectives(), numberofDivisions);
    }

    public solutionSet getResult() {
        //随机初始化
        NSGAIIIDoubleSolutionSet NDS=new NSGAIIIDoubleSolutionSet(popsize);
        NSGAIIIDoubleRandominit NDR=new NSGAIIIDoubleRandominit();
        NDS=NDR.execute(NDS,p);
        for (int i=0;i<generation;i++){

            //生成子代，因为精英保留原则
            //交叉算子
            NSGADoubleCrossover NDC=new NSGADoubleCrossover(pc,r);
            NSGAIIIDoubleSolutionSet childs=NDC.execute(NDS,p);
            //变异算子
            NSGADoubleMutation NDM=new NSGADoubleMutation(pm,k);
            childs=NDM.execute(childs,p);
            //System.out.println(childs.size);
            //整合两个种群
            //System.out.println("3");
            NSGAIIIDoubleSolutionSet SUM=new NSGAIIIDoubleSolutionSet(popsize*2);
            for (int j=0;j<popsize*2;j++){
                if (j<popsize){
                    SUM.add(NDS.array.get(j));
                }else{
                    //System.out.println(j-popsize);
                    DTLZ1 mp=(DTLZ1)p;
                    for (int h=0;h<childs.array.size();h++)
                    childs.array.set(h,mp.evalute(childs.array.get(h)));
                    SUM.add(childs.array.get(j-popsize));
                }
            }
            //快速非支配排序
            NSGAFastNonSort NFNS=new NSGAFastNonSort();
            SUM=NFNS.execute(SUM);
            //System.out.println("4");
            //迭代操作
            NSGAIIIGeneration NG=new NSGAIIIGeneration(SUM);
            NDS=NG.execute(referencePoints);
            //System.out.println("5");
            System.out.println(i);
        }
        for (int i=0;i<NDS.size;i++){
            for (int j=0;j<NDS.array.get(i).fitness.length;j++)
                System.out.print(NDS.array.get(i).fitness[j]+",");
            System.out.println(" ");
        }

        return null;
    }

    public solutionSet run() {
        return getResult();
    }
    public  static  void main(String args[]){
        NSGAIII test=new NSGAIII(1000,92,new DTLZ1());
        test.run();

    }
}

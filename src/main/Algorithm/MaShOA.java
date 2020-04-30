package main.Algorithm;
/*
* author:FHhui
* description:MaShOA which has been changed something important
* date:2020.4.26
* */

import main.Operator.*;
import main.Solution.MaShOADoubleSolution;
import main.Solution.MaShOADoubleSolutionSet;
import main.Solution.ReferencePoint;
import main.Solution.solutionSet;
import main.problem.DTLZ1;
import main.problem.Hyperproblem;
import main.problem.problem;

import java.util.ArrayList;
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
        this.p=p;
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
        //随机初始化
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
                if (j<is_best){//最优部分
                    MDS.array.get(j).is_best=true;
                }else if (j<is_best+is_sec_best){//次优部分
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
            //参考点迭代这里，应该将其他所有的参考点都给更新起来，类似于jmetal的副本操作
            RefGeneration RG=new RefGeneration();
            this.referencePoints=RG.run(MDS,referencePoints);
            System.out.println(i);
        }
        DTLZ1 mp=(DTLZ1)p;
        for (int g=0;g<MDS.size;g++){
            MaShOADoubleSolution a=mp.eval(MDS.array.get(g));
            MDS.array.set(g,a);
        }
        MDS=new NSGAFastNonSort().execute(MDS);
        ArrayList<MaShOADoubleSolution> sss=new ArrayList<>();
        for (int i=0;i<MDS.size();i++){
            if (MDS.array.get(i).rank==1){
                sss.add(MDS.array.get(i));
            }
        }
        MDS=new MaShOADoubleSolutionSet(sss.size());
        for (MaShOADoubleSolution a:sss){
            MDS.add(a);
        }
        evalute e=new evalute();
        System.out.println(e.execute(MDS));
        return null;
    }

    @Override
    public solutionSet run(problem p) {
        return super.run(p);
    }
    public static void main(String args[]){
        DTLZ1 p=new DTLZ1();
        MaShOA test=new MaShOA(1000,92,p);
        test.getResult();
    }
}

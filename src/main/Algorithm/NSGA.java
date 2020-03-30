package main.Algorithm;

import main.Operator.*;
import main.Solution.NSGADoubleSolutionSet;
import main.Solution.solutionSet;
import main.problem.Multiproblem;
import main.problem.ZDT1problem;

public class NSGA extends MultiAlgorithm{
//多目标遗传算法，NSGA2算法类实现 2020.1.30
    int humans;//种群个数
    int generation;//迭代次数
    public solutionSet run(Multiproblem p) {
        return getResult(p);
    }

    public NSGA(int humans,int generation){
        this.humans=humans;
        this.generation=generation;
    }

    public solutionSet getResult(Multiproblem p) {
        ZDT1problem p1=(ZDT1problem) p ;
        NSGADoubleSolutionSet s=new NSGADoubleSolutionSet(humans);
        NSGADoubleRandominit NDR=new NSGADoubleRandominit();
        s=NDR.execute(s,p);
        for (int i=0;i<generation;i++){
            NSGADoubleSolutionSet child=solutionSet.clone(s);//child是子类，通过序列化深度克隆父类得到。
            //快速非支配排序
            NSGAFastNonSort NFNS=new NSGAFastNonSort();
            NFNS.execute(child);
            //计算种群拥挤度
            CalDistance NSCD=new CalDistance();
            NSCD.execute(child);
            //System.out.println("1111");
            //选择
            NSGASelection NSS=new NSGASelection();
            NSS.execute(child);
            System.out.println("交叉");
            //交叉
            NSGADoubleCrossover NSDC=new NSGADoubleCrossover(0.8,0.6);
            NSDC.execute(child,p);
            System.out.println("变异");
            //变异
            NSGADoubleMutation NSDM=new NSGADoubleMutation(0.3,0.4);
            NSDM.execute(child,p);
            System.out.println("迭代");
            //迭代
            NSGADoubleGeneration NSDG=new NSGADoubleGeneration();
            s=NSDG.execute(s,child,p);
            System.out.println("结果"+i+"代");
            for (int m=0;m<s.size();m++){
                if (s.array.get(m).rank==1){
                    System.out.println("["+s.array.get(m).fitness[0]+","+s.array.get(m).fitness[1]+"],");
                }
            }

        }
        return s;
    }
    public static void main(String args[]){
        NSGA test=new NSGA(50,100);
        ZDT1problem p=new ZDT1problem();
        test.run(p);
    }
}

package main.Algorithm;

import main.Operator.*;
import main.Solution.GABinarySolutionSet;
import main.Solution.solutionSet;
import main.problem.RGAproblem;
import main.problem.Singleproblem;

public class GA extends SingleAlogorithm {
    //单目标遗传算法继承单目标算法
    //遗传算法
    int generation;//迭代次数
    int humans;

    public GA(int gen,int hum){
        this.generation=gen;
        this.humans=hum;
    }

    public solutionSet run(Singleproblem p) {
        return getResult(p);
    }

    public solutionSet getResult(Singleproblem p) {
        GABinarySolutionSet s=new GABinarySolutionSet(humans);//父代种群
        GABinaryRandominit GABR=new GABinaryRandominit();//遗传算法二进制随机初始化算子
        s=GABR.execute(s,p);//随机初始化算子,对s进行初始化操作。
        for(int i=0;i<generation;i++){

            GABinarySolutionSet child=solutionSet.clone(s);
            //选择父本
//            GABinaryRealSelection GABRS=new GABinaryRealSelection();
//            GABRS.execute(child);
            //交叉
            GABinaryCrossover GABC=new GABinaryCrossover(0.8);
            GABC.execute(child);
            //遗传算法二进制变异算子使用
            GABinaryMutation GABM=new GABinaryMutation(0.3);
            GABM.execute(child);

            for (int a=0;a<s.array.size();a++){
                child.array.set (a,((RGAproblem) p).evalute(child.array.get(a)));
                s.array.set (a,((RGAproblem) p).evalute(s.array.get(a)));
                //System.out.println(s.array.get(a).fitness[0]);
                //为什么这里的父代与子代的值是一样的，是因为内存的原因么？
            }
            GABinaryGenSelection GABGS=new GABinaryGenSelection();
            s=GABGS.execute(s,child);
            GABinaryFindLocal GABF=new GABinaryFindLocal();
            int best=GABF.findbest(s);
            System.out.println(s.array.get(best).fitness[0]);
        }
        return s;
    }
    public static void main(String args[]){
        GA test=new GA(10000,200);
        RGAproblem rgap=new RGAproblem();
        test.getResult(rgap);
    }
}

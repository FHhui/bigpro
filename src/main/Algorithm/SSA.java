package main.Algorithm;

import main.Operator.SSADoubleRandominit;
import main.Operator.SSANewDisplacePlace;
import main.Operator.SSASeasonChange;
import main.Operator.SSASort;
import main.Solution.SSADoubleSolutionSet;
import main.Solution.solutionSet;
import main.problem.RGAproblem;
import main.problem.Singleproblem;

public class SSA extends SingleAlogorithm{
    //单目标松鼠算法
    int generation;//迭代次数
    int humans;//种群个体数
    public static final int is_best=1;
    public static final int is_sec_best=3;

    public SSA(int generation,int humans){
        this.generation=generation;
        this.humans=humans;
    }

    public solutionSet run(Singleproblem p){
        return getResult(p);
    }
    public solutionSet getResult(Singleproblem p ){
        SSADoubleSolutionSet child=new SSADoubleSolutionSet(humans);
        SSADoubleRandominit SDR=new SSADoubleRandominit();//初始化操作
        child=SDR.execute(child,p);
        SSASort SS=new SSASort();
        child=SS.execute(child);
        for (int i=0;i<generation;i++){



            //随机选择，生成新位置算子·
            SSANewDisplacePlace SND=new SSANewDisplacePlace();
            child=SND.execute(child,p);

            //季节变化条件算子
            SSASeasonChange SSC=new SSASeasonChange();
            child=SSC.execute(child,p,i,generation);

            //重新排列并计算
            child=SS.execute(child);
            System.out.println(child.array.get(0).fitness[0]);
//        }
        }
        return child;
    }
    public static void main (String args[]){
        SSA t=new SSA(100,50);
        t.getResult(new RGAproblem());
    }
}

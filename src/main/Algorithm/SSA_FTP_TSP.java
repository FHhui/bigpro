package main.Algorithm;

import main.Operator.*;
import main.Solution.SSATspSolutionSet;
import main.problem.Singleproblem;
import main.problem.TSP;

public class SSA_FTP_TSP {
    //单目标松鼠算法，使用了邻域搜索初始化的松鼠算法用于解决单目标Tsp问题
    int generation;//迭代次数
    int humans;//种群个体数
    int citynum;
    public static final int is_best=1;
    public static final int is_sec_best=3;

    public SSA_FTP_TSP(int generation,int humans,int citynum){
        this.generation=generation;
        this.humans=humans;
        this.citynum=citynum;
    }

    public SSATspSolutionSet run(Singleproblem p){
        return getResult(p);
    }
    public SSATspSolutionSet getResult(Singleproblem p ){
        double best=0;
        SSATspSolutionSet child=new SSATspSolutionSet();
        SSATspFtpRandominit SDR=new SSATspFtpRandominit();//初始化操作
        child=SDR.execute(child,p,humans,citynum);
        SSASort SS=new SSASort();

        child=SS.execute(child);
        best=child.array.get(0).fitness;
        for (int i=0;i<generation;i++){
            //随机选择，生成新位置算子·

            SSA_Tsp_NewDisplace SND=new SSA_Tsp_NewDisplace();

            child=SND.execute(child,p,citynum);

            for (int m=0;m<child.array.size();m++){
                TSP p1=(TSP) p;
                child.array.set(m,p1.evalute(child.array.get(m)));
            }
            child=SS.execute(child);

            //季节变化条件算子
            SSASeasonChange SSC=new SSASeasonChange();
            child=SSC.execute(child,p,i,generation,best,citynum);
            //重新排列并计算
            for (int m=0;m<child.array.size();m++){
                TSP p1=(TSP) p;
                child.array.set(m,p1.evalute(child.array.get(m)));
            }
            child=SS.execute(child);
            best=child.array.get(0).fitness;
            System.out.println(child.array.get(0).fitness);
        }
        return child;
    }
    public static void main (String args[]){
        SSA_FTP_TSP t=new SSA_FTP_TSP(10000,50,48);
        t.getResult(new TSP(48,"D://data.txt"));
    }
}

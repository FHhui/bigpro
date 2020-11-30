package main.Operator;

import main.Solution.*;
import main.problem.*;

import java.util.Random;

public class SSATspRandominit extends Randominit{
    //tsp的随机初始化
    public SSATspSolutionSet execute(SSATspSolutionSet solutionS, Singleproblem p,int population,int cityNum) {
        Random random=new Random();
        while (!(solutionS.array.size()==population)) {
            SSATspSolution SDS = new SSATspSolution();
            SDS.city_cycle.add(0,random.nextInt(65535) % cityNum);//这里就是想初始化一条路径而已，
                // 我也不知道那个看起来很玄学的65535是干嘛的
            for (int i = 1; i < cityNum;)// 染色体长度
                {
                    SDS.city_cycle.add(i,random.nextInt(65535) % cityNum);
                    int j=0;
                    for (j = 0; j < i; j++) {
                        if (SDS.city_cycle.get(i) == SDS.city_cycle.get(j)) {//如果相同,那就得重新来,路径不能重复
                            SDS.city_cycle.remove(i);
                            break;
                        }
                    }
                    if (j == i) {
                        i++;
                    }
                }
            TSP p1=(TSP) p;
            SDS=p1.evalute(SDS);
            //System.out.println(SDS.city_cycle.size());
            //System.out.println(SDS.city_cycle.size());
            solutionS.array.add(SDS);

        }

        return solutionS;
    }
    public SSAMultiTspSolutionSet execute(SSAMultiTspSolutionSet solutionS, Multi_Tsp p, int population, int cityNum) {
        Random random=new Random();
        while (!(solutionS.array.size()==population)) {
            SSAMultiTspSolution SDS = new SSAMultiTspSolution();
            SDS.city_cycle.add(0,random.nextInt(65535) % cityNum);//这里就是想初始化一条路径而已，
            // 我也不知道那个看起来很玄学的65535是干嘛的
            for (int i = 1; i < cityNum;)// 染色体长度
            {
                SDS.city_cycle.add(i,random.nextInt(65535) % cityNum);
                int j=0;
                for (j = 0; j < i; j++) {
                    if (SDS.city_cycle.get(i) == SDS.city_cycle.get(j)) {//如果相同,那就得重新来,路径不能重复
                        SDS.city_cycle.remove(i);
                        break;
                    }
                }
                if (j == i) {
                    i++;
                }
            }

            SDS=p.evalute(SDS);
            solutionS.array.add(SDS);
        }

        return solutionS;
    }
}

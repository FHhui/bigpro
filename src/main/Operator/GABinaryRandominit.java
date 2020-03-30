package main.Operator;

import main.Solution.GABinarySolution;
import main.Solution.GABinarySolutionSet;
import main.problem.RGAproblem;
import main.problem.Singleproblem;

public class GABinaryRandominit extends Randominit{
    //遗传算法的随机初始化算子

    public GABinarySolutionSet execute(GABinarySolutionSet solutionS, Singleproblem p) {
        while (!solutionS.isFull()){
            //System.out.println("111");
            GABinarySolution GABS=new GABinarySolution(p);//新建一个解
            for (int i=0;i<GABS.variables.length;i++){
                int in = (int) (Math.random() * GABinarySolution.InteNum);
                StringBuilder s = new StringBuilder(Integer.toBinaryString(in));
                //浮点数
                while (s.length() < GABinarySolution.eLen) {
                    s.insert(0, '0');
                }
                //补0操作
                GABS.variables[i].setBinaryVariable(s.toString());
                //System.out.println(GABS.variables[i].getBinaryVariable());
            }
            RGAproblem p1=(RGAproblem)p;
            GABS=p1.evalute(GABS);//计算适应值函数
            //System.out.println(GABS.fitness[0]);
            solutionS.add(GABS);
            }
        return solutionS;
    }

    }
    //随机初始化算子

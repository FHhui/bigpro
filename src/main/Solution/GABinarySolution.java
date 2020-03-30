package main.Solution;

import main.problem.Singleproblem;

public class GABinarySolution extends solution{
    public BinaryVariable[] variables;
    public static int InteNum=1024;//间隔数
    public static int eLen=10;//编码长度
    public GABinarySolution(Singleproblem p) {
        super(p);
        variables=new BinaryVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new BinaryVariable();
        }
    }
//GA算法的二级制解
}

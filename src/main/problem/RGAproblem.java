package main.problem;

import main.Solution.*;

import java.util.ArrayList;

public class RGAproblem extends Singleproblem{
    //拉斯特林函数

    public RGAproblem(){
        super();
        this.numberOfVariables=30;
        this.lowerlimit=new ArrayList<>();

        this.upperlimit=new ArrayList<>();

        for (int i=0;i<this.numberOfVariables;i++){
            lowerlimit.add(0.0);
            upperlimit.add(1.0);
        }
    }
    public FADoubleSolution evalute(FADoubleSolution s){
        //萤火虫算法适应值计算
        s.fitness[0]=0;
        for (int i=0;i<this.numberOfVariables;i++){
            double dou=s.variables[i].getDoubleVariable();
            s.fitness[0] += dou*dou-10*Math.cos(2*Math.PI*dou)+10;
        }
        return s;
    }
    public PSODoubleSolution evalute(PSODoubleSolution s){
        //粒子群算法适应值计算
        s.fitness[0]=0;
        for (int i=0;i<this.numberOfVariables;i++){
            double dou=s.variables[i].getDoubleVariable();
            s.fitness[0] += dou*dou-10*Math.cos(2*Math.PI*dou)+10;
        }
        return s;
    }
    public BADoubleSolution evalute(BADoubleSolution s){
        //蝙蝠算法适应值计算
        s.fitness[0]=0;
        for (int i=0;i<this.numberOfVariables;i++){
            double dou=s.variables[i].getDoubleVariable();
            s.fitness[0] += dou*dou-10*Math.cos(2*Math.PI*dou)+10;
        }
        return s;
    }
    public SSADoubleSolution evalute(SSADoubleSolution s){
        //拉斯特林函数适应值计算，松鼠算法
        s.fitness[0]=0;
        for (int i=0;i<this.numberOfVariables;i++){
            double dou=s.variables[i].getDoubleVariable();
            s.fitness[0] += dou*dou-10*Math.cos(2*Math.PI*dou)+10;
        }
        return s;
    }
    //static int numberOfVariables=30;

    public GABinarySolution evalute(GABinarySolution s){
        //拉斯特林函数适应值计算，遗传算法
        s.fitness[0]=0;
        for (int i=0;i<this.numberOfVariables;i++){
            double dou=-5.12+Integer.parseInt(s.variables[i].getBinaryVariable(),2)*10.24/1023;

            s.fitness[0] += dou*dou-10*Math.cos(2*Math.PI*dou)+10;//适应值就根据拉斯特林函数的表达式来计算，也就是evalute方法存在的意义
        }
        return s;
    }//适应值计算函数

}

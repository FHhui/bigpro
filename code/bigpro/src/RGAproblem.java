import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

public class RGAproblem extends Singleproblem{
    //拉斯特林函数
    public RGAproblem(){
        super();
        this.numberOfVariables=30;
    }
    //static int numberOfVariables=30;
    public  GABinarySolution evalute(GABinarySolution s){
        //拉斯特林函数适应值计算
        s.fitness[0]=0;
        for (int i=0;i<this.numberOfVariables;i++){
            double dou=-5.12+Integer.parseInt(s.variables[i].getBinaryVariable(),2)*10.24/1023;

            s.fitness[0] += dou*dou-10*Math.cos(2*Math.PI*dou)+10;//适应值就根据拉斯特林函数的表达式来计算，也就是evalute方法存在的意义
        }
        return s;
    }//适应值计算函数

}

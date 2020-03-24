public class FA extends SingleAlogorithm{
    //萤火虫算法，改进，步长可变版
    double light_absorb;//光强吸引系数
    int generation;//最大迭代代数
    int humans;//种群个数
    double attr_max;//最大吸引度
    double step;//步长
    public FA(int humans,int generation){
        this.humans=humans;
        this.generation=generation;
        this.step=0.2;
        this.attr_max=1;
        this.light_absorb=1;
    }
    public FADoubleSolutionSet run(problem p) {
        return getResult(p);
    }
    public static void main(String args[]){
        RGAproblem p=new RGAproblem();
        FA t=new FA(50,1000);
        t.run(p);
    }

    public FADoubleSolutionSet getResult(problem p) {
        FADoubleSolutionSet s=new FADoubleSolutionSet(humans);

        FADoubleRandominit FADR=new FADoubleRandominit();
        //随机初始化算子
        s= FADR.execute(s,p);
        for (int i=0;i<generation;i++){
            //迭代
            //计算距离,相对亮度，吸引度
            FADoubleCal FADC=new FADoubleCal();
            s=FADC.execute(s,light_absorb,attr_max);
            //移动向量
            FADoubleReplace FADRE=new FADoubleReplace();
            s=FADRE.execute(s,p,step);
            //更新最优点
            LocalDoubleSearch FALDS=new LocalDoubleSearch();
            s=FALDS.execute(s);
            System.out.println(s.array.get(s.whichbest).fitness[0]);
        }
        return s;
    }
}

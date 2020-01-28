public class SSA extends SingleAlogorithm{
    //单目标松鼠算法
    int generation;//迭代次数
    int humans;//种群个体数
    static final int is_best=1;
    static final int is_sec_best=3;

    public SSA(int generation,int humans){
        this.generation=generation;
        this.humans=humans;
    }

    public solutionSet run(Singleproblem p){
        return getResult(p);
    }
    public solutionSet getResult(Singleproblem p ){
        SSADoubleSolutionSet s=new SSADoubleSolutionSet(humans);
        SSADoubleRandominit SDR=new SSADoubleRandominit();//初始化操作
        s=SDR.execute(s,p);
        for (int i=0;i<generation;i++){
            SSADoubleSolutionSet child=solutionSet.clone(s);
            //子代有了，对子代进行相关操作。
            //排序，声明，分配树种算子·
            SSASort SS=new SSASort();
            child=SS.execute(child);
            //随机选择，生成新位置算子·
            SSANewDisplacePlace SND=new SSANewDisplacePlace();
            child=SND.execute(child,p);
            //季节变化条件算子
            SSASeasonChange SSC=new SSASeasonChange();
            child=SSC.execute(child,p,i,generation);
            System.out.println(child.array.get(0).fitness[0]);
            s=solutionSet.clone(child);
        }
        return s;
    }
}

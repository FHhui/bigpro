public class NSSSA extends MultiAlgorithm {
    //多目标松鼠算法，（未完成状态）
    //将松鼠算法写成多目标松鼠算法
    int generation=100;//迭代次数
    int human=50;//种群大小
    static int is_best=3;
    static int is_sec_best=9;
    public NSSSADoubleSolutionSet run(Multiproblem p) {
        return getResult(p);
    }
    public NSSSA(int generation,int humans){
        this.generation=100;
        this.human=humans;
    }

    public NSSSADoubleSolutionSet getResult(Multiproblem p) {
        NSSSADoubleSolutionSet s=new NSSSADoubleSolutionSet(human);
        NSSSADoubleRandominit NSDR=new NSSSADoubleRandominit();
        s=NSDR.execute(s,p);//随机初始化操作
        for (int i=0;i<this.generation;i++){
            //迭代操作
            //快速非支配排序
            NSGAFastNonSort NSFN=new NSGAFastNonSort();//快速非支配排序算子
            NSFN.execute(s);
            //计算距离值
            NSGACalDistance NSCD=new NSGACalDistance();//拥挤度计算算子
            NSCD.execute(s);
            //根据帕累托等级与距离值进行排序
            NSSSASort nss=new NSSSASort();
            s=nss.execute(s);
            //输出最优解
            for (int j=0;j<s.size();j++){
                if (s.array.get(j).rank==1){
                    System.out.println("["+s.array.get(j).fitness[0]+","+s.array.get(j).fitness[1]+"],");
                }

            }
            SSASeasonChange ssc=new SSASeasonChange();
            s=ssc.execute(s,p,i,generation);
            //根据季节等条件更新位置
            OutCsvFormat out=new OutCsvFormat();
            out.writeCSV(s);
        }

        return null;
    }
    public static void main(String args[]){
        NSSSA test=new NSSSA(100,50);
        ZDT1problem zdt=new ZDT1problem();
        test.run(zdt);
}

}

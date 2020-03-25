import java.util.List;
import java.util.Vector;

/*author：FHhui
* description: NSGAIII by java
* */
public class NSGAIII extends HyperAlgorithm{
    //NSGAIII算法
    int generation;
    int popsize;
    double pc;
    double r;
    double pm;
    double k;
    Hyperproblem p;
    Vector<Integer> numberofDivisions;
    List<ReferencePoint<NSGAIIIDoubleSolution>> referencePoints = new Vector<>() ;
    public NSGAIII(int generation,int popsize,Hyperproblem p){
        this.generation=generation;
        this.popsize=popsize;
        this.p=p;
        this.numberofDivisions=new Vector<>(1);
        numberofDivisions.add(12);//划分数
        (new ReferencePoint<NSGAIIIDoubleSolution>()).generateReferencePoints(referencePoints,p.getNumberOfObjectives(), numberofDivisions);
    }

    public solutionSet getResult() {
        //随机初始化
        NSGAIIIDoubleSolutionSet NDS=new NSGAIIIDoubleSolutionSet(popsize);
        NSGAIIIDoubleRandominit NDR=new NSGAIIIDoubleRandominit();
        NDS=NDR.execute(NDS,p);
        for (int i=0;i<generation;i++){
            NSGAIIIDoubleSolutionSet childs=solutionSet.clone(NDS);//生成子代，因为精英保留原则
            //交叉算子
            NSGADoubleCrossover NDC=new NSGADoubleCrossover(pc,r);
            childs=NDC.execute(NDS,p);
            //变异算子
            NSGADoubleMutation NDM=new NSGADoubleMutation(pm,k);
            //整合两个种群
            NSGAIIIDoubleSolutionSet SUM=new NSGAIIIDoubleSolutionSet(popsize*2);
            for (int j=0;j<popsize*2;j++){
                if (j<popsize){
                    SUM.add(NDS.array.get(j));
                }else{
                    SUM.add(childs.array.get(j-popsize));
                }
            }
            //快速非支配排序
            NSGAFastNonSort NFNS=new NSGAFastNonSort();
            SUM=NFNS.execute(SUM);
            //参考点的部署

        }
        return null;
    }

    public solutionSet run() {
        return getResult();
    }
    public  static  void main(String args[]){

    }
}

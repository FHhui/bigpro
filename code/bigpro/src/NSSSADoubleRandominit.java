public class NSSSADoubleRandominit extends Randominit{
    //NSSSA算法的随机初始化算子
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet solutionS,Multiproblem p ){
        while (!solutionS.isFull()){
            NSSSADoubleSolution NDS=new NSSSADoubleSolution(p);
            for (int i=0;i<NDS.variables.length;i++){
                NDS.variables[i].setDoubleVariable(p.lower+Math.random()*(p.upper-p.lower));
            }
            ZDT1problem z1=(ZDT1problem) p;
            NDS=z1.evalute(NDS);
            solutionS.add(NDS);
        }
        return solutionS;
    }
}

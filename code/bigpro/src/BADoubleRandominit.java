public class BADoubleRandominit extends Randominit{
    //BA算法的随机初始化算子

    public BADoubleSolutionSet execute(BADoubleSolutionSet solutionS,Singleproblem p) {
        while (!solutionS.isFull()) {
            BADoubleSolution SDS = new BADoubleSolution(p);
            for (int i = 0; i < SDS.variables.length; i++) {
                SDS.variables[i].setDoubleVariable( p.lower + Math.random() * (p.upper - p.lower));
            }
            RGAproblem p1=(RGAproblem) p;
            SDS=p1.evalute(SDS);

            solutionS.add(SDS);
        }
       //寻找本地最优解算子
        BALocalDoubleSearch BALDS=new BALocalDoubleSearch();
        solutionS=BALDS.execute(solutionS);


        return solutionS;
    }
}

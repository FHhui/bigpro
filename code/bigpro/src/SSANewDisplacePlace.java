public class SSANewDisplacePlace extends operator {
   //松鼠算法的交换位置算子
    double gc=1.9;

    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public SSADoubleSolutionSet execute(SSADoubleSolutionSet s,Singleproblem p){
        //具体的操作方法
        for (int i=SSA.is_best;i<s.array.size();i++){
            double dg=0.5+Math.random()*0.61;
            double r=Math.random();//是否遇到捕食者的标尺
            double pdp=0.1;//不遇到捕食者的最低限度
            if (r>=pdp)//产生新位置，没有遇到捕食者,如果存在捕食者就原地不动
            {
                if (i>=SSA.is_best&&i<(SSA.is_best+SSA.is_sec_best)){
                    //======>>对于次优解来说。<<========
                    for (int j=0;j<s.array.get(0).variables.length;j++){
                        //针对于每一个维度
                        double isMax= s.array.get(i).variables[j].doubleVariable + dg * gc * (s.array.get(0).variables[j].doubleVariable - s.array.get(i).variables[j].doubleVariable);
                        if(isMax>p.upper||isMax<p.lower) continue;
                        else s.array.get(i).variables[j].setDoubleVariable(isMax);
                    }
                }
                else if (i>=SSA.is_best+SSA.is_sec_best&&s.array.get(i).is_sec_best==false){
                    //对于普通解，但曾经去过ci优解的个体来说
                    int which_second_best = (int) (SSA.is_best + Math.random() * (SSA.is_sec_best));
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        double isMax= s.array.get(i).variables[j].doubleVariable + dg * gc * (s.array.get(which_second_best).variables[j].doubleVariable - s.array.get(i).variables[j].doubleVariable);
                        if(isMax>p.upper||isMax<p.lower) continue;
                        else s.array.get(i).variables[j].setDoubleVariable(isMax);
                    }
                }else if (i>=SSA.is_best+SSA.is_sec_best&&s.array.get(i).is_sec_best==true){
                    //对于普通解但是去过次优解的解来说
                    for (int j=0;j<s.array.get(0).variables.length;j++){
                        //针对于每一个维度
                        double isMax= s.array.get(i).variables[j].doubleVariable + dg * gc * (s.array.get(0).variables[j].doubleVariable - s.array.get(i).variables[j].doubleVariable);
                        if(isMax>p.upper||isMax<p.lower) continue;
                        else s.array.get(i).variables[j].setDoubleVariable(isMax);
                    }
                }else{
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].setDoubleVariable( p.lower+Math.random() * (p.upper - p.lower));
                    }
                }
            }

        }
        return s;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s,Multiproblem p){
        //多目标松鼠算法的位置改变算子
        for (int i=NSSSA.is_best;i<s.array.size();i++){
            double dg=0.5+Math.random()*0.61;
            double r=Math.random();//是否遇到捕食者的标尺
            double pdp=0.1;//不遇到捕食者的最低限度
            if (r>=pdp)//产生新位置，没有遇到捕食者,如果存在捕食者就原地不动
            {
                if (i>=NSSSA.is_best&&i<(NSSSA.is_best+NSSSA.is_sec_best)){
                    //======>>对于次优解来说。<<========
                    for (int j=0;j<s.array.get(0).variables.length;j++){
                        //针对于每一个维度
                        double isMax= s.array.get(i).variables[j].doubleVariable + dg * gc * (s.array.get(0).variables[j].doubleVariable - s.array.get(i).variables[j].doubleVariable);
                        if(isMax>p.upper||isMax<p.lower) continue;
                        else s.array.get(i).variables[j].setDoubleVariable(isMax);
                    }
                }
                else if (i>=NSSSA.is_best+NSSSA.is_sec_best&&s.array.get(i).is_sec_best==false){
                    //对于普通解，但曾经去过ci优解的个体来说
                    int which_second_best = (int) (SSA.is_best + Math.random() * (SSA.is_sec_best));
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        double isMax= s.array.get(i).variables[j].doubleVariable + dg * gc * (s.array.get(which_second_best).variables[j].doubleVariable - s.array.get(i).variables[j].doubleVariable);
                        if(isMax>p.upper||isMax<p.lower) continue;
                        else s.array.get(i).variables[j].setDoubleVariable(isMax);
                    }
                }else if (i>=NSSSA.is_best+SSA.is_sec_best&&s.array.get(i).is_sec_best==true){
                    //对于普通解但是去过次优解的解来说
                    for (int j=0;j<s.array.get(0).variables.length;j++){
                        //针对于每一个维度
                        double isMax= s.array.get(i).variables[j].doubleVariable + dg * gc * (s.array.get(0).variables[j].doubleVariable - s.array.get(i).variables[j].doubleVariable);
                        if(isMax>p.upper||isMax<p.lower) continue;
                        else s.array.get(i).variables[j].setDoubleVariable(isMax);
                    }
                }else{
                    for (int j = 0; j < s.array.get(0).variables.length; j++) {
                        s.array.get(i).variables[j].setDoubleVariable( p.lower+Math.random() * (p.upper - p.lower));
                    }
                }
            }

        }
        return s;
    }
}

import java.util.ArrayList;

public class NSGAIIIDoubleSolution extends solution{
    //NSGAIII的解类
    DoubleVariable[] variables;//自变量集合
    int nq;//个体被支配数
    int rank;//帕累托等级
    ArrayList<NSGAIIIDoubleSolution> sp;//支配集合
    public NSGAIIIDoubleSolution(Hyperproblem p){
        super(p);
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
        this.sp=new ArrayList<>();
        this.nq=0;
    }
    public NSGAIIIDoubleSolution copy(NSGAIIIDoubleSolution s,Hyperproblem p){
        NSGAIIIDoubleSolution newS = new NSGAIIIDoubleSolution(p);

        for (int i=0;i<variables.length;i++){
            newS.variables[i].doubleVariable=s.variables[i].doubleVariable;
        }
        return  newS;

    }
}

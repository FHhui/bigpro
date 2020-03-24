public class PSODoubleSolution extends solution{

    DoubleVariable[] variables;//自变量集合
    double v[];//粒子运动速度
    double vmax[];
    DoubleVariable[] variables_best;//历史最有自变量集合
    double[] fitness_best;//历史最优适应度
    public PSODoubleSolution(Singleproblem p) {
        super(p);
        fitness_best=new double[p.getNumberOfObjectives()];
        variables=new DoubleVariable[p.getNumberOfVariables()];
        variables_best=new DoubleVariable[p.getNumberOfVariables()];
        v=new double[p.getNumberOfVariables()];
        vmax=new double[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            vmax[i]=0.50;
            variables[i]=new DoubleVariable();//初始化解
            variables_best[i]=new DoubleVariable();
        }
    }
}

public class FADoubleSolution extends solution{
    double[] dis;//距离数组
    DoubleVariable[] variables;
    double[] relative_light;//相对亮度
    double[] attractiveness;//个体吸引度
    public FADoubleSolution(problem p) {
        super(p);
        this.attractiveness=new double[50];
        this.dis=new double[50];
        this.relative_light=new double[50];
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解

        }
    }
}

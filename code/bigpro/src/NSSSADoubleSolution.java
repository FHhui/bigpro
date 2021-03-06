import java.util.ArrayList;

public class NSSSADoubleSolution extends solution{
//多目标松鼠的解类
    DoubleVariable[] variables;//自变量集合
    int nq;//个体被支配的次数
    double distance;//拥挤度
    int rank;//帕累托等级
    boolean is_best;//最优解标记
    boolean is_sec_best;//次优解标记
    ArrayList<NSSSADoubleSolution> sp;//支配集合
    public NSSSADoubleSolution(Multiproblem p) {
        //构造方法
        super(p);
        variables=new DoubleVariable[p.getNumberOfVariables()];
        for (int i=0;i<p.getNumberOfVariables();i++){
            variables[i]=new DoubleVariable();//初始化解
        }
        this.sp=new ArrayList<>();
        this.nq=0;
        this.distance=0.0;
    }
}

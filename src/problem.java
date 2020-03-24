import javax.print.attribute.standard.PrinterResolution;
import java.util.ArrayList;

public abstract class problem {
    //问题类
    String name;//问题的名字

    ArrayList<Double> upperlimit;//当自变量取值有变化时使用集合
    ArrayList<Double> lowerlimit;//后期补丁，前面的upper与lower逐步废弃

    int numberOfVariables;//自变量维度
    int numberOfObjectives;//目标自变量维度
    int numberOfConstraints;//定义域
    public abstract solution evalute(solution s);//适应值计算函数
    //getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    public void setNumberOfVariables(int numberOfVariables) {
        this.numberOfVariables = numberOfVariables;
    }

    public int getNumberOfObjectives() {
        return numberOfObjectives;
    }

    public void setNumberOfObjectives(int numberOfObjectives) {
        this.numberOfObjectives = numberOfObjectives;
    }

    public int getNumberOfConstraints() {
        return numberOfConstraints;
    }

    public void setNumberOfConstraints(int numberOfConstraints) {
        this.numberOfConstraints = numberOfConstraints;
    }

}

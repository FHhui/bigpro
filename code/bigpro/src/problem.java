import javax.print.attribute.standard.PrinterResolution;

public abstract class problem {
    //问题类
    String name;//问题的名字
    double upper;
    double lower;
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

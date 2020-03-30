package main.Solution;

import java.io.Serializable;

public abstract class  variable implements Serializable {
    //自变量类，这里应该有一个自变量的取值，但由于三者都不一样，所以也就没法搞了
    public abstract String getBinaryVariable();
    public abstract void setBinaryVariable(String bv);
    public abstract int getRealVariable();
    public abstract void setRealVariable(int rv);
    public abstract double getDoubleVariable();
    public abstract void setDoubleVariable(double dv);
}

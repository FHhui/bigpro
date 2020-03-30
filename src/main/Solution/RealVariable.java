package main.Solution;

public class RealVariable extends variable {
    //实数自变量
    int realVariable;

    @Override
    public String getBinaryVariable() {
        return null;
    }

    @Override
    public void setBinaryVariable(String bv) {

    }

    @Override
    public int getRealVariable() {
        return realVariable;
    }

    @Override
    public void setRealVariable(int rv) {
        this.realVariable=rv;
    }

    @Override
    public double getDoubleVariable() {
        return 0;
    }

    @Override
    public void setDoubleVariable(double dv) {

    }
}

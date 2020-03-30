package main.Solution;

public class DoubleVariable extends variable{
    //浮点数自变量
    public double doubleVariable;
    @Override
    public String getBinaryVariable() {
        return null;
    }

    @Override
    public void setBinaryVariable(String bv) {

    }

    @Override
    public int getRealVariable() {
        return 0;
    }

    @Override
    public void setRealVariable(int rv) {

    }

    @Override
    public double getDoubleVariable() {
        return doubleVariable;
    }

    @Override
    public void setDoubleVariable(double dv) {
        this.doubleVariable=dv;
    }
}

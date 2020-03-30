package main.Solution;

public class BinaryVariable extends variable {
    //二进制自变量
    public String binaryVariable;

    @Override
    public String getBinaryVariable() {
        return binaryVariable;
    }

    public void setBinaryVariable(String binaryVariable) {
        this.binaryVariable = binaryVariable;
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
        return 0;
    }

    @Override
    public void setDoubleVariable(double dv) {

    }
}

public class RealVariable extends variable {
    //实数自变量
    int realVariable;

    @Override
    public String getBinaryVariable() {
        return null;
    }

    @Override
    public int getRealVariable() {
        return realVariable;
    }

    @Override
    public double getDoubleVariable() {
        return 0;
    }
}

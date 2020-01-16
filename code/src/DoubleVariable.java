public class DoubleVariable extends variable{
    //浮点数自变量
    double doubleVariable;
    @Override
    public String getBinaryVariable() {
        return null;
    }

    @Override
    public int getRealVariable() {
        return 0;
    }

    @Override
    public double getDoubleVariable() {
        return doubleVariable;
    }
}

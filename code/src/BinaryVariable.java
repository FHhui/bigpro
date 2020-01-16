public class BinaryVariable extends variable {
    //二进制自变量
    String binaryVariable;

    @Override
    public String getBinaryVariable() {
        return binaryVariable;
    }

    @Override
    public int getRealVariable() {
        return 0;
    }

    @Override
    public double getDoubleVariable() {
        return 0;
    }
}

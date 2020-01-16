import java.util.ArrayList;

public abstract class solutionSet {
    //解集类，这是一个解集,因为解集只有double类型的，所以无需子类
    ArrayList<solution> array;
    public abstract void add(solution s);//增加
    public abstract void remove(solution s);//删除
    public abstract void size();//显示大小
    public abstract void replace(solution s);//替换
}

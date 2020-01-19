import java.util.ArrayList;

public class solutionSet {
    //解集类，这是一个解集,因为解集只有double类型的，所以无需子类
    ArrayList<solution> array;
    int realsize;
    int size;
    public solutionSet(int n){
        array=new ArrayList<solution>();
        this.size=n;
        realsize=0;
    }
    public void add(solution s){
        System.out.println(s.variables);
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    }//增加
    public void remove(solution s){
        array.remove(s);
        realsize--;
    }//删除
    public int size(){
        return array.size();
    }//显示大小
    public boolean isFull(){
        if (realsize==size){
            return true;
        }else{
            return false;
        }
    }
    public void replace(solution s1,solution s2)
    {
        int i=0;
        for (solution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1
}

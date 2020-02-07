import java.util.ArrayList;
//多目标松鼠的解集类
public class NSSSADoubleSolutionSet extends solutionSet {
    ArrayList<NSSSADoubleSolution> array;
    int realsize;
    int size;
    double best;
    int whichbest;
    public void add(NSSSADoubleSolution s){
        if (realsize<size){
            array.add(s);
            realsize++;
        }
    } //添加

    public void remove(NSSSADoubleSolution s){
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

    public void replace(NSSSADoubleSolution s1,NSSSADoubleSolution s2){
        int i=0;
        for (NSSSADoubleSolution e:array){
            if (e.equals(s1)){
                array.set(i,s2);
            }
            i++;
        }
    }//s2替换s1

    public NSSSADoubleSolutionSet(int n) {
        super(n);
        this.array=new ArrayList<NSSSADoubleSolution>();
        this.size=n;
        realsize=0;
    }
    //松鼠算法浮点数解集。
}

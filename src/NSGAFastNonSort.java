import java.util.ArrayList;

public class NSGAFastNonSort extends Sort{
    //NSGA的快速非支配排序算法
    @Override
    public void execute() {

    }

    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
    public NSSSADoubleSolutionSet Texecute(NSSSADoubleSolutionSet s){

        ArrayList<NSSSADoubleSolutionSet> F=new ArrayList<NSSSADoubleSolutionSet>();
        NSSSADoubleSolutionSet fx=new NSSSADoubleSolutionSet(s.size);

        for (int i=0;i<s.size;i++){
            s.array.get(i).nq=0;
            for (int j=0;j<s.size;j++){
                if (j!=i){
                    if (s.array.get(i).fitness[0]<=s.array.get(j).fitness[0] && s.array.get(i).fitness[1]<=s.array.get(j).fitness[1]
                    &&s.array.get(i).fitness[2]<=s.array.get(j).fitness[2]){//两个函数均小于
                        //支配关系 i支配j
                        if (s.array.get(i).fitness[0]==s.array.get(j).fitness[0] && s.array.get(i).fitness[1]==s.array.get(j).fitness[1]
                        &&s.array.get(i).fitness[2]==s.array.get(j).fitness[2]){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.array.get(i).sp.add(s.array.get(j));
                        }
                    }else if(s.array.get(i).fitness[0]>=s.array.get(j).fitness[0] && s.array.get(i).fitness[1]>=s.array.get(j).fitness[1]
                            && s.array.get(i).fitness[2]>=s.array.get(j).fitness[2]){//两个函数均大于
                        //被支配关系 i被j支配
                        if (s.array.get(i).fitness[0]==s.array.get(j).fitness[0] && s.array.get(i).fitness[1]==s.array.get(j).fitness[1]
                                && s.array.get(i).fitness[2]==s.array.get(j).fitness[2]){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.array.get(i).nq++;
                        }
                    }
                }
            }
            if(s.array.get(i).nq==0){
                s.array.get(i).rank=1;
                fx.add(s.array.get(i));
            }
        }
        F.add(fx);
        int i=0;
        while (F.get(i).array.size()!=0){
            fx=new NSSSADoubleSolutionSet(s.array.size());
            for (int j=0;j<F.get(i).size();j++){//对F进行迭代
                for (int m=0;m<F.get(i).array.get(j).sp.size();m++){
                    //对F中的个体支配个体集进行迭代
                    F.get(i).array.get(j).sp.get(m).nq--;
                    if (F.get(i).array.get(j).sp.get(m).nq==0){
                        F.get(i).array.get(j).sp.get(m).rank=i+2;
                        fx.add(F.get(i).array.get(j).sp.get(m));
                    }
                }
            }
            F.add(fx);
            i++;
        }
        System.out.println("快排结束");
        return s;
    }
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s){

        ArrayList<NSSSADoubleSolutionSet> F=new ArrayList<NSSSADoubleSolutionSet>();
        NSSSADoubleSolutionSet fx=new NSSSADoubleSolutionSet(s.size);

        for (int i=0;i<s.size;i++){
            s.array.get(i).nq=0;
            for (int j=0;j<s.size;j++){
                if (j!=i){
                    if (s.array.get(i).fitness[0]<=s.array.get(j).fitness[0] && s.array.get(i).fitness[1]<=s.array.get(j).fitness[1]){//两个函数均小于
                        //支配关系 i支配j
                        if (s.array.get(i).fitness[0]==s.array.get(j).fitness[0] && s.array.get(i).fitness[1]==s.array.get(j).fitness[1]){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.array.get(i).sp.add(s.array.get(j));
                        }
                    }else if(s.array.get(i).fitness[0]>=s.array.get(j).fitness[0] && s.array.get(i).fitness[1]>=s.array.get(j).fitness[1]){//两个函数均大于
                        //被支配关系 i被j支配
                        if (s.array.get(i).fitness[0]==s.array.get(j).fitness[0] && s.array.get(i).fitness[1]==s.array.get(j).fitness[1]){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.array.get(i).nq++;
                        }
                    }
                }
            }
            if(s.array.get(i).nq==0){
                s.array.get(i).rank=1;
                fx.add(s.array.get(i));
            }
        }
        F.add(fx);
        int i=0;
        while (F.get(i).array.size()!=0){
            fx=new NSSSADoubleSolutionSet(s.array.size());
            for (int j=0;j<F.get(i).size();j++){//对F进行迭代
                for (int m=0;m<F.get(i).array.get(j).sp.size();m++){
                    //对F中的个体支配个体集进行迭代
                    F.get(i).array.get(j).sp.get(m).nq--;
                    if (F.get(i).array.get(j).sp.get(m).nq==0){
                        F.get(i).array.get(j).sp.get(m).rank=i+2;
                        fx.add(F.get(i).array.get(j).sp.get(m));
                    }
                }
            }
            F.add(fx);
            i++;
        }
        System.out.println("快排结束");
        return s;
    }
    public NSGADoubleSolutionSet execute(NSGADoubleSolutionSet s){
        ArrayList<NSGADoubleSolutionSet> F=new ArrayList<NSGADoubleSolutionSet>();
        NSGADoubleSolutionSet fx=new NSGADoubleSolutionSet(s.size);
        for (int i=0;i<s.size;i++){
            s.array.get(i).nq=0;
            for (int j=0;j<s.size;j++){
                if (j!=i){
                    if (s.array.get(i).fitness[0]<=s.array.get(j).fitness[0] && s.array.get(i).fitness[1]<=s.array.get(j).fitness[1]){//两个函数均小于
                        //支配关系 i支配j
                        if (s.array.get(i).fitness[0]==s.array.get(j).fitness[0] && s.array.get(i).fitness[1]==s.array.get(j).fitness[1]){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.array.get(i).sp.add(s.array.get(j));
                        }
                    }else if(s.array.get(i).fitness[0]>=s.array.get(j).fitness[0] && s.array.get(i).fitness[1]>=s.array.get(j).fitness[1]){//两个函数均大于
                        //被支配关系 i被j支配
                        if (s.array.get(i).fitness[0]==s.array.get(j).fitness[0] && s.array.get(i).fitness[1]==s.array.get(j).fitness[1]){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.array.get(i).nq++;
                        }
                    }
                }
            }
            if(s.array.get(i).nq==0){
                s.array.get(i).rank=1;
                fx.add(s.array.get(i));
            }
        }
        F.add(fx);
        int i=0;
        while (F.get(i).array.size()!=0){
            fx=new NSGADoubleSolutionSet(s.array.size());
            for (int j=0;j<F.get(i).size();j++){//对F进行迭代
                for (int m=0;m<F.get(i).array.get(j).sp.size();m++){
                    //对F中的个体支配个体集进行迭代
                    F.get(i).array.get(j).sp.get(m).nq--;
                    if (F.get(i).array.get(j).sp.get(m).nq==0){
                        F.get(i).array.get(j).sp.get(m).rank=i+2;
                        fx.add(F.get(i).array.get(j).sp.get(m));
                    }
                }
            }
            F.add(fx);
            i++;
        }
        System.out.println("快排结束");
        return s;
    }
}

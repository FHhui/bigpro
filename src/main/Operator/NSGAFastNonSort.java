package main.Operator;

import main.Algorithm.MaShOA;
import main.Solution.*;

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
    public NSGAIIIDoubleSolutionSet execute(NSGAIIIDoubleSolutionSet s){
        ArrayList<NSGAIIIDoubleSolutionSet> F= new ArrayList<>();
        NSGAIIIDoubleSolutionSet fx=new NSGAIIIDoubleSolutionSet(s.size);

        for (int i=0;i<s.size;i++){
            s.array.get(i).nq=0;
            s.array.get(i).sp=new ArrayList<>();
            for (int j=0;j<s.size;j++){
                int flag1=0;
                int flag2=0;
                if (j!=i){
                    //快速非支配排序
                    for (int m=0;m<s.array.get(j).fitness.length;m++){
                        if (s.array.get(i).fitness[m]<s.array.get(j).fitness[m]){
                            flag1++;
                        }else if(s.array.get(i).fitness[m]>s.array.get(j).fitness[m]){
                            flag2++;
                        }
                    }
                    if (flag1==s.array.get(i).fitness.length){
                        s.array.get(i).sp.add(s.array.get(j));
                    }else if(flag2==s.array.get(i).fitness.length){
                        s.array.get(i).nq++;
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
            fx=new NSGAIIIDoubleSolutionSet(s.array.size());
            for (int j=0;j<F.get(i).size();j++){//对F进行迭代
                for (int m=0;m<F.get(i).array.get(j).sp.size();m++){
                    //对F中的个体支配个体集进行迭代
                    F.get(i).array.get(j).sp.get(m).nq--;
                    if (F.get(i).array.get(j).sp.get(m).nq==0){
                        F.get(i).array.get(j).sp.get(m).rank=i+2;
                        fx.add(F.get(i).array.get(j).sp.get(m));
                        //System.out.println("123");
                    }
                }
            }
            F.add(fx);
            i++;
        }
        //System.out.println("快排结束");
        return s;
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

        for (int i=0;i<s.array.size();i++){
            s.array.get(i).nq=0;
            for (int j=0;j<s.array.size();j++){
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
       NSSSADoubleSolutionSet f=new NSSSADoubleSolutionSet(s.array.size());
        for (int j=0;j<s.array.size();j++){
            f.array.add(s.array.get(j));
        }
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
    public MaShOADoubleSolutionSet execute(MaShOADoubleSolutionSet s){
        ArrayList<MaShOADoubleSolutionSet> F= new ArrayList<>();
        MaShOADoubleSolutionSet fx=new MaShOADoubleSolutionSet(s.size);
        for (int i=0;i<s.size;i++){
            s.array.get(i).nq=0;
            s.array.get(i).sp=new ArrayList<>();
            for (int j=0;j<s.size;j++){
                int flag1=0;
                int flag2=0;
                if (j!=i){
                    //快速非支配排序
                    for (int m=0;m<s.array.get(j).fitness.length;m++){
                        if (s.array.get(i).fitness[m]<s.array.get(j).fitness[m]){
                            flag1++;
                        }else if(s.array.get(i).fitness[m]>s.array.get(j).fitness[m]){
                            flag2++;
                        }
                    }
                    if (flag1==s.array.get(i).fitness.length){
                        s.array.get(i).sp.add(s.array.get(j));
                    }else if(flag2==s.array.get(i).fitness.length){
                        s.array.get(i).nq++;
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
            fx=new MaShOADoubleSolutionSet(s.array.size());
            for (int j=0;j<F.get(i).size();j++){//对F进行迭代
                for (int m=0;m<F.get(i).array.get(j).sp.size();m++){
                    //对F中的个体支配个体集进行迭代
                    F.get(i).array.get(j).sp.get(m).nq--;
                    if (F.get(i).array.get(j).sp.get(m).nq==0){
                        F.get(i).array.get(j).sp.get(m).rank=i+2;
                        fx.add(F.get(i).array.get(j).sp.get(m));
                        //System.out.println("123");
                    }
                }
            }
            F.add(fx);
            i++;
        }
        //System.out.println("快排结束");
        return s;
    }
    public ArrayList<vector_Route> execute(ArrayList<vector_Route> s){

        ArrayList<ArrayList<vector_Route>> F=new ArrayList<ArrayList<vector_Route>>();
        ArrayList<vector_Route> fx=new ArrayList<>();

        for (int i=0;i<s.size();i++){
            s.get(i).nq=0;
            for (int j=0;j<s.size();j++){
                if (j!=i){
                    if (s.get(i).fitness1<=s.get(j).fitness1 && s.get(i).fitness1<=s.get(j).fitness2){//两个函数均小于
                        //支配关系 i支配j
                        if (s.get(i).fitness1==s.get(j).fitness1 && s.get(i).fitness2==s.get(j).fitness2){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.get(i).sp.add(s.get(j));
                        }
                    }else if(s.get(i).fitness1>=s.get(j).fitness1 && s.get(i).fitness2>=s.get(j).fitness2){//两个函数均大于
                        //被支配关系 i被j支配
                        if (s.get(i).fitness1==s.get(j).fitness1 && s.get(i).fitness2==s.get(j).fitness2){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.get(i).nq++;
                        }
                    }
                }
            }
            if(s.get(i).nq==0){
                s.get(i).rank=1;
                fx.add(s.get(i));
            }
        }
        F.add(fx);
        int i=0;
        while (F.get(i).size()!=0){
            fx=new ArrayList<>();
            for (int j=0;j<F.get(i).size();j++){//对F进行迭代
                for (int m=0;m<F.get(i).get(j).sp.size();m++){
                    //对F中的个体支配个体集进行迭代
                    F.get(i).get(j).sp.get(m).nq--;
                    if (F.get(i).get(j).sp.get(m).nq==0){
                        F.get(i).get(j).sp.get(m).rank=i+2;
                        fx.add(F.get(i).get(j).sp.get(m));
                    }
                }
            }
            F.add(fx);
            i++;
        }
        System.out.println("快排结束");
        return s;
    }
    public SSAMultiTspSolutionSet execute(SSAMultiTspSolutionSet s){

        ArrayList<SSAMultiTspSolutionSet> F=new ArrayList<>();
        SSAMultiTspSolutionSet fx=new SSAMultiTspSolutionSet();

        for (int i=0;i<s.array.size();i++){
            s.array.get(i).nq=0;
            for (int j=0;j<s.array.size();j++){
                if (j!=i){
                    if (s.array.get(i).fitness1<=s.array.get(j).fitness1 && s.array.get(i).fitness1<=s.array.get(j).fitness2){//两个函数均小于
                        //支配关系 i支配j
                        if (s.array.get(i).fitness1==s.array.get(j).fitness1 && s.array.get(i).fitness2==s.array.get(j).fitness2){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.array.get(i).sp.add(s.array.get(j));
                        }
                    }else if(s.array.get(i).fitness1>=s.array.get(j).fitness1 && s.array.get(i).fitness2>=s.array.get(j).fitness2){//两个函数均大于
                        //被支配关系 i被j支配
                        if (s.array.get(i).fitness1==s.array.get(j).fitness1 && s.array.get(i).fitness2==s.array.get(j).fitness2){
                            //对于相等的来说，没有任何操作才是对的
                        }else{
                            s.array.get(i).nq++;
                        }
                    }
                }
            }
            if(s.array.get(i).nq == 0){
                s.array.get(i).rank=1;
                fx.array.add(s.array.get(i));
            }
        }
        F.add(fx);
        int i=0;
        while (F.get(i).array.size()!=0){
            fx=new SSAMultiTspSolutionSet();
            for (int j=0;j<F.get(i).array.size();j++){//对F进行迭代
                for (int m=0;m<F.get(i).array.get(j).sp.size();m++){
                    //对F中的个体支配个体集进行迭代
                    F.get(i).array.get(j).sp.get(m).nq--;
                    if (F.get(i).array.get(j).sp.get(m).nq==0){
                        F.get(i).array.get(j).sp.get(m).rank=i+2;
                        fx.array.add(F.get(i).array.get(j).sp.get(m));
                    }
                }
            }
            F.add(fx);
            i++;
        }
        //System.out.println("快排结束");
        return s;
    }
}

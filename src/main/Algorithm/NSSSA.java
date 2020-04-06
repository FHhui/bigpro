package main.Algorithm;

import main.Operator.*;
import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.solutionSet;
import main.problem.Hyperproblem;
import main.problem.Multiproblem;
import main.problem.ZDT4problem;

import java.util.HashMap;
import java.util.Iterator;

public class NSSSA extends MultiAlgorithm {
    //多目标松鼠算法，
    //将松鼠算法写成多目标松鼠算法
    int generation=100;//迭代次数
    int k=4;//将网格分为几份
    int human=50;//种群大小
    public static int is_best=3;//最优个数
    public static int is_sec_best=9;//次优个数

    public NSSSADoubleSolutionSet run(Multiproblem p) {
        return getResult(p);
    }
    public NSSSA(int generation,int humans){
        this.generation=generation;
        this.human=humans;
    }
    public HashMap<int[],Integer> calLocation(HashMap<int[],Integer> map,NSSSADoubleSolutionSet s){
        map=new HashMap<>();//初始化参数
        //冒泡排序
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[0]>s.array.get(j+1).fitness[0]){
                    NSSSADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        double d1=(s.array.get(s.array.size()-1).distance-s.array.get(0).distance)/k;
        for(int i=1;i<s.array.size();i++){
            s.array.get(i).location[0]=(int)(s.array.get(i).fitness[0]/d1)+1;
        }
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[1]>s.array.get(j+1).fitness[1]){
                    NSSSADoubleSolution temp=s.array.get(j);
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        double d2=(s.array.get(s.array.size()-1).distance-s.array.get(0).distance)/k;
        for(int i=1;i<s.array.size();i++){
            s.array.get(i).location[1]=(int)(s.array.get(i).fitness[0]/d2)+1;
        }
        for (int i=0;i<k;i++){
            for (int j=0;j<k;j++){
                int[] a={i,j};
                map.put(a,0);
                //System.out.println(map.size());
            }
        }
        for (int i=0;i<s.array.size();i++){
            int[] h=s.array.get(i).location;
            //System.out.println(map.size());
            Iterator it=map.keySet().iterator();
            if (it.hasNext()){
                int[] temp= (int[]) it.next();
                if (temp[0]==h[0]&&temp[1]==h[1]){
                    map.replace(temp,map.get(temp)+1);
                }
            }
        }
        return map;
    }
    public NSSSADoubleSolutionSet getResult(Multiproblem p) {
        NSSSADoubleSolutionSet s=new NSSSADoubleSolutionSet(human);
        NSSSADoubleRandominit NSDR=new NSSSADoubleRandominit();//**更改问题
        s=NSDR.execute(s,p);//随机初始化操作
        for (int i=0;i<this.generation;i++){
            //迭代操作
            //快速非支配排序
            NSSSADoubleSolutionSet f=solutionSet.clone(s);
            NSGAFastNonSort NSFN=new NSGAFastNonSort();//快速非支配排序算子
            NSFN.execute(s);
            HashMap<int[], Integer> map=new HashMap<int[],Integer>();//网格地图
            //计算距离值
            //CalDistance NSCD=new CalDistance();//拥挤度计算算子
            //NSCD.execute(s);
            //利用网格法来计算距离，在排序方法中修改了相关设定
            map=calLocation(map,s);
            //根据帕累托等级与距离值进行排序，因为采用网格法修改了部分排列设定
            NSSSASort nss=new NSSSASort();
            s=nss.execute(s,map);
            //根据密度和适应值计算映射函数

            //种群迁移
            SSANewDisplacePlace sndp=new SSANewDisplacePlace();
            sndp.execute(s);
            //季节条件
            SSASeasonChange ssc=new SSASeasonChange();
            s=ssc.execute(s,p,i,generation);
            //根据季节等条件更新位置
            //子父代精英选择
            NSGADoubleGeneration NSDG=new NSGADoubleGeneration();
            s=NSDG.execute(s,f,map);

            for (int j=0;j<s.size();j++){
                if (s.array.get(j).rank==1){
                    System.out.println("{"+s.array.get(j).fitness[0]+","+s.array.get(j).fitness[1]+"},");
                }
            }
//            OutCsvFormat out=new OutCsvFormat();
//            out.writeCSV(s);
        }
        return null;
    }
    public void calfitness(){

    }
    public static void main(String args[]){
        NSSSA test=new NSSSA(100,50);
        ZDT4problem z=new ZDT4problem();
        test.getResult(z);
}
}

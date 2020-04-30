package main.Algorithm;

import main.Operator.*;
import main.Solution.NSSSADoubleSolutionSet;
import main.problem.*;

import java.util.HashMap;
import java.util.Iterator;

public class MoSSA extends MultiAlgorithm {
    //多目标松鼠算法，
    //将松鼠算法写成多目标松鼠算法
    int generation=100;//迭代次数
    int k=4;//将网格分为几份
    int human=50;//种群大小
    public static int is_best=3;//最优个数
    public static int is_sec_best=9;//次优个数
    public HashMap<int[],Integer> map;

    public NSSSADoubleSolutionSet run(Multiproblem p) {
        return getResult(p);
    }
    public MoSSA(int generation, int humans){
        this.generation=generation;
        this.human=humans;
    }
    public NSSSADoubleSolutionSet calLocation( NSSSADoubleSolutionSet s){
        //位置划分函数
        map=new HashMap<>();//初始化参数
        //冒泡排序.
        for (int m=0;m<s.array.get(0).fitness.length;m++){
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;i++){
                if (s.array.get(j).fitness[m]>s.array.get(j+1).fitness[m]){
                    NSSSADoubleSolution temp=s.array.get(j).copy(s.array.get(j),new ZDT6problem());
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        //单函数冒泡排序结束
        double delta=(s.array.get(s.array.size()-1).fitness[m]
                -s.array.get(0).fitness[m])/k;
        for (int i=0;i<s.size();i++){
            //System.out.println(s.array.get(i).fitness.length);
            s.array.get(i).location[m]=(int)Math.floor(s.array.get(i).fitness[m]/delta);
            //向下取整
        }
        }
        //到这一步每一个个体的location都已经更新完毕，然后统计每一个位置和
        map=new HashMap<>();
        for (int i=0;i<k;i++){
            for (int j=0;j<k;j++){
                int[] a={i,j};
                map.put(a,0);
            }
        }//初始化map

        //向map里放入数
        for (int i=0;i<s.array.size();i++){
            int[] h=s.array.get(i).location;
            Iterator it=map.keySet().iterator();
            if (it.hasNext()){
                int[] temp= (int[]) it.next();
                if (temp.equals(h)){
                    map.replace(temp,map.get(temp)+1);
                }
            }
    }
        this.map=map;
        return s;
    }
    public NSSSADoubleSolutionSet getResult(Multiproblem p) {
        NSSSADoubleSolutionSet s=new NSSSADoubleSolutionSet(human);
        NSSSADoubleRandominit NSDR=new NSSSADoubleRandominit();//**更改问题
        s=NSDR.execute(s,p);//随机初始化操作
        for (int i=0;i<this.generation;i++){
            //迭代操作
            //快速非支配排序
            NSSSADoubleSolutionSet f= s.copy(p);
            NSGAFastNonSort NSFN=new NSGAFastNonSort();//快速非支配排序算子
            NSFN.execute(s);
            s=calLocation(s);
            //利用网格法来计算距离，在排序方法中
            //map=calLocation(map,s);
            //根据帕累托等级与距离值进行排序，因为采用网格法修改了部分排列设定
            NSSSASort nss=new NSSSASort();
            s=nss.execute(s,map);
            //根据密度和适应值计算映射函数
            s=calfitness(s,map);
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
        }
        return null;
    }
    public NSSSADoubleSolutionSet calfitness(NSSSADoubleSolutionSet s,HashMap<int[],Integer> map){
        //计算映射函数
        for (int i=0;i<s.size();i++){
            //System.out.println(map);
            for (int[] key:map.keySet()){
                if (key[0]==s.array.get(i).location[0]&&key[1]==s.array.get(i).location[1]){
                    s.array.get(i).evafitness=
                            ((double)map.get(key) /s.array.get(i).sp.size());
                }
            }
        }
        return s;
    }
    public static void main(String args[]){
        MoSSA test=new MoSSA(100,50);
        ZDT6problem z=new ZDT6problem();
        test.getResult(z);
}
}
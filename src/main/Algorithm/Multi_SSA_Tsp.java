package main.Algorithm;

import main.Operator.*;
import main.Solution.SSAMultiTspSolution;
import main.Solution.SSAMultiTspSolutionSet;
import main.Solution.SSATspSolutionSet;
import main.problem.*;

import java.util.HashMap;
import java.util.Iterator;

public class Multi_SSA_Tsp {
    //会很慢
    //多目标松鼠算法用于解决tsp问题
    int generation;//迭代次数
    int humans;//种群个体数
    int citynum;
    int k=4;//将网格分为几份
    public HashMap<int[],Integer> map;
    public static final int is_best=3;
    public static final int is_sec_best=9;

    public Multi_SSA_Tsp(int generation,int humans,int citynum){
        this.generation=generation;
        this.humans=humans;
        this.citynum=citynum;
    }
    
    public SSAMultiTspSolutionSet calLocation(SSAMultiTspSolutionSet s){
        map=new HashMap<>();//初始化参数
        //冒泡排序.
        for (int m=0;m<2;m++){
            for (int i=0;i<s.array.size();i++){
                for (int j=0;j<s.array.size()-i-1;i++){
                    if (s.array.get(j).fitness1>s.array.get(j+1).fitness1){
                        SSAMultiTspSolution temp=s.array.get(j).copy(s.array.get(j));
                        s.array.set(j,s.array.get(j+1));
                        s.array.set(j+1,temp);
                    }
                }
            }
            //单函数冒泡排序结束
            double delta=(s.array.get(s.array.size()-1).fitness1
                    -s.array.get(0).fitness1)/k;
            for (int i=0;i<s.array.size();i++){
                //System.out.println(s.array.get(i).fitness.length);
                if (m==0)
                    s.array.get(i).location[m]=(int)Math.floor(s.array.get(i).fitness1/delta);
                if (m==1)
                    s.array.get(i).location[m]=(int)Math.floor(s.array.get(i).fitness1/delta);
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

    public SSAMultiTspSolutionSet run(Multiproblem p){
        return getResult(p);
    }

    public SSAMultiTspSolutionSet getResult(Multiproblem p ){
        double best=0;
        SSAMultiTspSolutionSet child=new SSAMultiTspSolutionSet();
        SSATspRandominit SDR=new SSATspRandominit();//初始化操作
        //所以这里应该有一个父代来为接下来的精英选择做准备。
        child=SDR.execute(child,p,humans,citynum);

        for (int i=0;i<generation;i++){
            //快速非支配排序

            for (int m=0;m<child.array.size();m++){
                Multi_Tsp p1=(Multi_Tsp) p;
                child.array.set(m,p1.evalute(child.array.get(m)));
            }

            NSGAFastNonSort NSFS=new NSGAFastNonSort();
            child=NSFS.execute(child);
            System.out.println("快速非支配排序完毕");
            child=this.calLocation(child);

            NSSSASort nss=new NSSSASort();
            child=nss.execute(child,map);
            System.out.println("冒泡排序操作完成");
            //随机选择，生成新位置算子·
            child=calLocation(child);
            System.out.println("位置排序已完成");
            SSA_Tsp_NewDisplace SND=new SSA_Tsp_NewDisplace();
            child=SND.execute(child,p,citynum);
            System.out.println("松鼠跳跃操作完毕");
            //季节变化条件算子
            SSASeasonChange SSC=new SSASeasonChange();
            child=SSC.execute(child,p,i,generation,best,citynum);
            System.out.println("季节条件变化完成");
            //重新排列并计算
            for (int m=0;m<child.array.size();m++){
                Multi_Tsp p1=(Multi_Tsp) p;
                child.array.set(m,p1.evalute(child.array.get(m)));
            }

            child=NSFS.execute(child);
            System.out.println("第二次非支配排序完成");
            //为什么会有没有rank值的选项，这就很离谱。
            for (int m=0;m<child.array.size();m++){
                if (child.array.get(m).rank == 1){
                    System.out.println(child.array.get(m).fitness1+","+child.array.get(m).fitness2);
                }
        }
            //输出第几代
            System.out.println(i);
            //在季节条件变化完成之后，这里应该还有一个精英选择算法
        }
        return child;
    }

    public static void main (String args[]){
        Multi_SSA_Tsp t=new Multi_SSA_Tsp(100000,50,100);
        t.getResult(new Multi_Tsp(100,"kroA100.tsp","kroB100.tsp"));
    }
}

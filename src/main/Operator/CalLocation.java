package main.Operator;

import main.Solution.NSSSADoubleSolution;
import main.Solution.NSSSADoubleSolutionSet;
import main.Solution.SSAMultiTspSolution;
import main.Solution.SSAMultiTspSolutionSet;
import main.problem.ZDT2problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CalLocation {
    //用于网格法中计算网格密度
    HashMap<int[],ArrayList> map;
    double delta;
    public NSSSADoubleSolutionSet execute(NSSSADoubleSolutionSet s,int k){
        map=new HashMap<>();//初始化参数
        //冒泡排序.
        for (int m=0;m<s.array.get(0).fitness.length;m++){
            //单函数冒泡排序
            for (int i=0;i<s.array.size();i++){
                for (int j=0;j<s.array.size()-i-1;j++){
                    if (s.array.get(j).fitness[m]>s.array.get(j+1).fitness[m]){
                        NSSSADoubleSolution temp=s.array.get(j).copy(s.array.get(j),new ZDT2problem());
                        s.array.set(j,s.array.get(j+1));
                        s.array.set(j+1,temp);
                    }
                }
            }
            //单函数冒泡排序结束

            delta=(s.array.get(s.array.size()-1).fitness[m]
                    -s.array.get(0).fitness[m])/k;//k表示需要划分多少个表格在一个纬度上
            //System.out.println("adadadad"+delta);
            for (int i=0;i<s.size();i++){
                //System.out.println(Math.floor((s.array.get(i).fitness[m]-s.array.get(0).fitness[m])/delta));
                s.array.get(i).location[m]=(int)Math.floor((s.array.get(i).fitness[m]-s.array.get(0).fitness[m])/delta);
                if (s.array.get(i).location[m]==k)
                    s.array.get(i).location[m]=k-1;
                //向下取整
            }
        }
        //到这一步每一个个体的location都已经更新完毕，然后统计每一个位置和
        map=new HashMap<>();
        for (int i=0;i<k;i++){
            for (int j=0;j<k;j++){
                int[] a={i,j};
                //System.out.println(a[0]+"---"+a[1]);
                map.put(a,new ArrayList<NSSSADoubleSolution>());
            }
        }//初始化map

        //向map里放入数
        for (int i=0;i<s.array.size();i++){
            int[] h=s.array.get(i).location;
            Iterator it=map.keySet().iterator();
           while (it.hasNext()){
                int[] temp= (int[]) it.next();
                //System.out.println(temp[0]+"-------------"+h[0]);
                //System.out.println(temp[1]+"-------------"+h[1]);
                if (temp[0]==h[0]&&temp[1]==h[1]){
                    map.get(temp).add(s.array.get(i));
                  //  System.out.println("a111111111111111111");
                    //map.replace(temp,map.get(temp).add(s.array.get(i)));
                }
            }
        }
        this.map=map;
        this.delta=delta;
        //System.out.println("a111111111111111111");
        return s;
    }
    public double getDelta(){
        return delta;
    }
public SSAMultiTspSolutionSet execute(SSAMultiTspSolutionSet s,int k){
    map=new HashMap<>();//初始化参数
    //冒泡排序.
    for (int m=0;m<s.array.get(0).fitness.length;m++){
        //单函数冒泡排序
        for (int i=0;i<s.array.size();i++){
            for (int j=0;j<s.array.size()-i-1;j++){
                if (s.array.get(j).fitness[m]>s.array.get(j+1).fitness[m]){
                    SSAMultiTspSolution temp=s.array.get(j).copy(s.array.get(j));
                    s.array.set(j,s.array.get(j+1));
                    s.array.set(j+1,temp);
                }
            }
        }
        //单函数冒泡排序结束

        delta=(s.array.get(s.array.size()-1).fitness[m]
                -s.array.get(0).fitness[m])/k;//k表示需要划分多少个表格在一个纬度上
        //System.out.println("adadadad"+delta);
        for (int i=0;i<s.array.size();i++){
            //System.out.println(Math.floor((s.array.get(i).fitness[m]-s.array.get(0).fitness[m])/delta));
            s.array.get(i).location[m]=(int)Math.floor((s.array.get(i).fitness[m]-s.array.get(0).fitness[m])/delta);
            if (s.array.get(i).location[m]==k)
                s.array.get(i).location[m]=k-1;
            //向下取整
        }
    }
    //到这一步每一个个体的location都已经更新完毕，然后统计每一个位置和
    map=new HashMap<>();
    for (int i=0;i<k;i++){
        for (int j=0;j<k;j++){
            int[] a={i,j};
            //System.out.println(a[0]+"---"+a[1]);
            map.put(a,new ArrayList<>());
        }
    }//初始化map

    //向map里放入数
    for (int i=0;i<s.array.size();i++){
        int[] h=s.array.get(i).location;
        Iterator it=map.keySet().iterator();
        while (it.hasNext()){
            int[] temp= (int[]) it.next();
            //System.out.println(temp[0]+"-------------"+h[0]);
            //System.out.println(temp[1]+"-------------"+h[1]);
            if (temp[0]==h[0]&&temp[1]==h[1]){
                map.get(temp).add(s.array.get(i));
                //  System.out.println("a111111111111111111");
                //map.replace(temp,map.get(temp).add(s.array.get(i)));
            }
        }
    }
    this.map=map;
    this.delta=delta;
    //System.out.println("a111111111111111111");
    return s;
}
    public HashMap<int[], ArrayList> getMap() {
        return map;
    }
}

package main.Solution;

import java.io.*;
import java.util.ArrayList;

public class SSAMultiTspSolution implements Serializable {
    //多目标Tsp问题
    public double fitness1;//适应度评价标准1
    public double fitness2;//适应度评价标准2
    public ArrayList<Integer> city_cycle;
    public int[] location;//
    public boolean is_best;//是否到达过最优解
    public boolean is_sec_best;//是否到达过次优解
    public int nq;
    public int rank;//帕累托等级
    public ArrayList<SSAMultiTspSolution> sp;
    public SSAMultiTspSolution(){
        this.city_cycle=new ArrayList<>();
        this.nq=0;
        this.rank=0;
        location=new int[2];
        this.sp=new ArrayList<>();
    }
    //clone方法虽然是一个好方法，但是会导致运行速度变慢等等不利于使用的操作，所以我们要重新写一个copy方法
    public static <T> T clone(T sourceObj) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 将sourceObj对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。
            // 所以利用这个特性可以实现对象的深拷贝
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(sourceObj);
            // 将流序列化成对象
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object targetOjb = objectInputStream.readObject();
            return (T) targetOjb;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                byteArrayOutputStream = null;
                byteArrayInputStream = null;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("出现异常");
            }
        }
        return null;
    }
    public static SSAMultiTspSolution copy(SSAMultiTspSolution a){
        SSAMultiTspSolution b=new SSAMultiTspSolution();
        b.fitness1=a.fitness1;
        b.fitness2=a.fitness2;
        b.city_cycle=a.city_cycle;
        b.location=a.location;
        b.is_best=a.is_best;
        b.is_sec_best=a.is_sec_best;
        b.nq=a.nq;
        b.rank=a.rank;
        b.sp=a.sp;
        //将A的所有属性全部赋值给b
        return b;
    }
}

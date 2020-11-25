package main.Solution;

import java.io.*;
import java.util.ArrayList;

public class vector_Route implements Serializable {
    //路径对，用于多目标Tsp问题进行拆分完整路径
    public double fitness1;//
    public double fitness2;//
    public int[] Dc=new int[2];
    public int nq;
    public int rank;//帕累托等级
    public ArrayList<vector_Route> sp;//支配集合
    public  vector_Route(int[] Dc){
        this.Dc=Dc;
        this.fitness1=0;
        this.fitness2=0;
        this.nq=0;
        this.rank=0;
        this.sp=new ArrayList<>();
    }
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

}

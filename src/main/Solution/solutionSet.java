package main.Solution;

import java.io.*;
import java.util.ArrayList;

public class solutionSet implements Serializable {
    //实现序列化的解集，在具体的实现当中应该有深度克隆的内容
    //在这里我们使用序列化来实现
    //解集类，这是一个解集,因为解集只有double类型的，所以无需子类
    public ArrayList<solution> array;
    //double fitness[];
    public int realsize;
    public int size;
    static  final long serialVersionUID = 3747630134243407984L;
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

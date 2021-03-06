import java.io.*;

public  class solution implements Serializable {
    //解决方案类，这个类就相当于；松鼠算法中的种群中的个体，也就是解集
    double[] fitness;//适应值集合,该集合的大小有问题的numberofobjective决定
    variable[] variables;//自变量集合
    public solution(problem p){
        //System.out.println(p.getClass());
        fitness=new double[p.getNumberOfObjectives()];
        //System.out.println(fitness.length);
        //System.out.println(p.getNumberOfObjectives());
        //具体的赋值留到了具体的解里面去，因为在这里无法确定variable的性质
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

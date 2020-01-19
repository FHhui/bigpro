public  class solution {
    //解决方案类，这个类就相当于；松鼠算法中的种群中的个体，也就是解集
    double[] fitness;//适应值集合,该集合的大小有问题的numberofobjective决定
    variable[] variables;//自变量集合
    public solution(problem p){
        //System.out.println(p.getClass());
        fitness=new double[p.getNumberOfObjectives()];
        //System.out.println(p.getNumberOfObjectives());
        //具体的赋值留到了具体的解里面去，因为在这里无法确定variable的性质
    }
}

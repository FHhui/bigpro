package main.Operator;

import main.Solution.GABinarySolutionSet;

public class GABinaryFindLocal extends LocalSearch{


    public int findbest(GABinarySolutionSet s){
        int sbest=0;//父代最优位置
        double sbestfit=Integer.MAX_VALUE;//父代最优值
        //System.out.println(s.array.size());
        for (int i=0;i<s.array.size();i++){
            if (s.array.get(i).fitness[0]<sbestfit){
                //System.out.println(i);
                sbest=i;
                sbestfit=s.array.get(i).fitness[0];
            }
        }
        //System.out.println(sbest);
        return sbest;
    }
    public int findworst(GABinarySolutionSet s){
        int cworst=0;//子代最差值
        double cworstfit=Integer.MIN_VALUE;//子代最差值位置
        for (int i=0;i<s.array.size();i++){
            if (s.array.get(i).fitness[0]>cworstfit){
                cworst=i;
                cworstfit=s.array.get(i).fitness[0];
            }
        }
        return cworst;
    }
}

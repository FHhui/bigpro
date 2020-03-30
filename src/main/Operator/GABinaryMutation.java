package main.Operator;

import main.Solution.GABinarySolution;
import main.Solution.GABinarySolutionSet;

public class GABinaryMutation extends Mutation{
    //遗传算法二进制变异算子

    public GABinaryMutation(double pm) {
        super(pm);
    }
    public GABinarySolutionSet execute(GABinarySolutionSet s) {
        for (int i=0;i<s.size();i++){
            double m=Math.random();
            for (int j=0;j<s.array.get(i).variables.length;j++){

                if (m<pm){
                    int pos=(int)(Math.random()* GABinarySolution.eLen);
                    StringBuilder strBuilder = new StringBuilder(s.array.get(i).variables[j].getBinaryVariable());
                    if (strBuilder.charAt(pos)=='0'){
                        strBuilder.setCharAt(pos,'1');
                    }else{
                        strBuilder.setCharAt(pos,'0');
                    }
                    s.array.get(i).variables[j].setBinaryVariable(strBuilder.toString());
                }
            }
        }
        return s;
    }

}

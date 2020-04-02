package main.Operator;

import main.Algorithm.MaShOA;
import main.Solution.MaShOADoubleSolution;
import main.Solution.MaShOADoubleSolutionSet;
import main.Solution.NSGAIIIDoubleSolution;
import main.Solution.solutionSet;
import main.problem.DTLZ1;
import main.problem.Hyperproblem;

import java.util.ArrayList;
import java.util.HashMap;

public class MaShOAGeneration extends operator{
//多目标松鼠迭代算子
HashMap<Integer, ArrayList<MaShOADoubleSolution>> front;
    ArrayList<MaShOADoubleSolution> front_l;
    @Override
    public void execute() {

    }
    public MaShOADoubleSolutionSet execute(MaShOADoubleSolutionSet parent, MaShOADoubleSolutionSet child, Hyperproblem p){
        MaShOADoubleSolutionSet s=new MaShOADoubleSolutionSet(parent.array.size()*2);
        for (int i=0;i<parent.array.size()*2;i++){
            if (i<parent.array.size()){
                s.add(parent.array.get(i));
            }else {
                s.add(child.array.get(i-parent.array.size()));
            }
        }
        DTLZ1 mp=(DTLZ1)p;
        for (int i=0;i<s.array.size();i++){
            MaShOADoubleSolution ansm=s.array.get(i);
            ansm=mp.eval(ansm);
            s.array.set(i,ansm);
        }
        //快速非支配排序
        NSGAFastNonSort NFS=new NSGAFastNonSort();
        s=NFS.execute(s);
        this.front=new HashMap<>();
        for (int i=0;i<s.size();i++){
        //首先把帕累托等级给分开
        if (front.keySet(). contains ( s.array.get(i).rank)){
            front.get(s.array.get(i).rank).add(s.array.get(i));

        } else{
            front_l=new ArrayList<>();
            front_l.add(s.array.get(i));
            front.put(s.array.get(i).rank,front_l);
        }
        }
        //向新种群依次添加，知道第l层，并将第l层的也添加进去

        //参考点的迭代

        return s;
}
    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
}

package main.Operator;

import main.Algorithm.MaShOA;
import main.Solution.*;
import main.problem.DTLZ1;
import main.problem.Hyperproblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaShOAGeneration extends operator{
//多目标松鼠迭代算子
HashMap<Integer, ArrayList<MaShOADoubleSolution>> front;
    ArrayList<MaShOADoubleSolution> front_l;
    @Override
    public void execute() {

    }
    public MaShOADoubleSolutionSet execute(MaShOADoubleSolutionSet parent, MaShOADoubleSolutionSet child, Hyperproblem p,
                                           List<ReferencePoint<MaShOADoubleSolution>> ref, int generation){
        int maxsize=parent.array.size();
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
        //向新种群依次添加，直到第l层，并将第l层的也添加进去
        int rankingIndex=1;//表示第几层,因为是从帕累托等级为1开始的，所以，你懂的
        int candidateSolutions=0;
        ArrayList<MaShOADoubleSolution> newS=new ArrayList<>();
        while(candidateSolutions<maxsize){
            candidateSolutions+=front.get(rankingIndex).size();
            front_l = front.get(rankingIndex);
            for (int i = 0 ; i < front_l.size(); i++) {
                newS.add(front_l.get(i));
            }
        }
        //根据JF进行排序并且取前L个
        MaShOADoubleSolutionSet S=new MaShOADoubleSolutionSet(newS.size());
        for (MaShOADoubleSolution ss:newS){
            S.add(ss);
        }
        MaShOAinitJF initJF=new MaShOAinitJF(S);
        //重新计算JF
        S=initJF.execute(ref,generation);
        SortMaoPao sm=new SortMaoPao();
        S=sm.run(S);
        MaShOADoubleSolutionSet ans=new MaShOADoubleSolutionSet(maxsize);
        for (int i=0;i<maxsize;i++){
         ans.add(S.array.get(i));
        }
        return ans;
}
    @Override
    public solutionSet execute(solutionSet s) {
        return null;
    }
}

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ReferencePoint<S extends solution> {
    public List<Double> position;
    private int memberSize;
    private List<HashMap<S, Double>> potentialMembers;

    public ReferencePoint() {
    }

    /**
     * Constructor
     */
    public ReferencePoint(int size) {
        position = new ArrayList<>();
        for (int i = 0; i < size; i++)
            position.add(0.0);
        memberSize = 0;
        potentialMembers = new ArrayList<>();
    }

    public ReferencePoint(ReferencePoint<S> point) {
        position = new ArrayList<>(point.position.size());
        for (Double d : point.position) {
            position.add(new Double(d));
        }
        memberSize = 0;
        potentialMembers = new ArrayList<>();
    }

    public void generateReferencePoints(
            List<ReferencePoint<S>> referencePoints,
            int numberOfObjectives,
            List<Integer> numberOfDivisions) {

        ReferencePoint<S> refPoint = new ReferencePoint<>(numberOfObjectives);//目标数
        generateRecursive(referencePoints, refPoint, numberOfObjectives, numberOfDivisions.get(0), numberOfDivisions.get(0), 0);

    }

    private void generateRecursive(
            List<ReferencePoint<S>> referencePoints,
            ReferencePoint<S> refPoint,
            int numberOfObjectives,
            int left,
            int total,
            int element) {
        if (element == (numberOfObjectives - 1)) {
            refPoint.position.set(element, (double) left / total);
            referencePoints.add(new ReferencePoint<>(refPoint));
        } else {
            for (int i = 0; i <= left; i += 1) {
                refPoint.position.set(element, (double) i / total);
                generateRecursive(referencePoints, refPoint, numberOfObjectives, left - i, total, element + 1);
            }
        }
        //return referencePoints;
    }

    public List<Double> pos() {
        return this.position;
    }

    public int MemberSize() {
        return memberSize;
    }

    public boolean HasPotentialMember() {
        return potentialMembers.size() > 0;
    }

    public void clear() {
        memberSize = 0;
        this.potentialMembers.clear();
    }

    public void AddMember() {
        this.memberSize++;
        //p++的操作
    }

    public void AddPotentialMember(S member_ind, double distance) {
        //连接附近的解到参考点的操作
        HashMap h = new HashMap<S, Double>();
        h.put(member_ind, distance);
        this.potentialMembers.add(h);
    }


}

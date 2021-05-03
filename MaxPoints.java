import java.util.HashMap;
import java.util.Map;

// leetcode problem number: 149
class MaxPoint {
    public int maxPoints(int[][] points) {
        int maxPoints = 0;
        for (int i = 0; i < points.length; i++) {
            int currentMax = 0;
            int samePoints = 0;
            Map<Double, Integer> anglePoinsMap = new HashMap<>();
            for (int j = i; j < points.length; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    samePoints++;
                    continue;
                }
                double angle = getAngle(points[i], points[j]);
                anglePoinsMap.put(angle, anglePoinsMap.getOrDefault(angle, 0) + 1);
            }
            for (Map.Entry<Double, Integer> m : anglePoinsMap.entrySet()) {
                if (currentMax < (m.getValue()))
                    currentMax = m.getValue();
            }
            maxPoints = Math.max(maxPoints, currentMax + samePoints);
        }
        return maxPoints;
    }

    public double getAngle(int[] firstPoint, int[] secondPoint) {
        if (firstPoint[0] == secondPoint[0])
            return 90.0;
        if (firstPoint[1] == secondPoint[1])
            return 0.0;
        return (secondPoint[0] - firstPoint[0]) / (double) (secondPoint[1] - firstPoint[1]);
    }
}

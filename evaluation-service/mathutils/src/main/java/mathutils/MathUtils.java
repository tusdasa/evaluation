package mathutils;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

/**
 * @Author: tusdasa
 * @Date: 2020-03-21 1:58 PM
 */

public class MathUtils {

    private StandardDeviation standardDeviation;

    public MathUtils() {
        this.standardDeviation = new StandardDeviation();
    }

    /**
     * 求算数平均数
     *
     * @param array double[]
     * @return 平均数
     */
    public double meanNumber(double[] array) {
        if (check(array)) {
            return array[0];
        }
        return StatUtils.mean(array);
    }

    /**
     * 求标准差
     *
     * @param array double[]
     * @return 标准差
     */
    public double standardDeviationNumber(double[] array) {
        if (check(array)) {
            return 0.0D;
        }
        return standardDeviation.evaluate(array);
    }

    /**
     * 求方差
     *
     * @param array double[]
     * @return 方差
     */
    public double varianceNumber(double[] array) {
        if (check(array)) {
            return array[0];
        }
        return StatUtils.populationVariance(array);
    }

    /**
     * @param array double[]
     * @return 众数
     */
    public double[] modeNumber(double[] array) {
        if (check(array)) {
            return array;
        }
        return StatUtils.mode(array);
    }

    /**
     * @param array double[]
     * @return 和
     */
    public double sumNumber(double[] array) {
        return StatUtils.sum(array);
    }

    private boolean check(double[] array) {
        if (array.length < 2) {
            return true;
        }
        return false;
    }

}

package mathutils.array;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-03-21 2:01 PM
 */

public class MathArrayUtils {

    public MathArrayUtils() {
    }

    /**
     * 计算占数组长度 percentage 大小
     *
     * @param array      double数组
     * @param percentage 占的百分比(小数)
     * @return 数组长度 * 百分比
     */
    public static int getPercentageIndex(double[] array, double percentage) {
        if (array.length <= 2) {
            return 0;
        }
        // 小于3
        if (array.length <= 3) {
            return 1;
        }
        // 向上取整
        return (int) Math.ceil(array.length * percentage);
    }

    /**
     * 将 List<Integer> 转换为 double[]
     *
     * @param integerList 整数集合
     * @return 转换后的double类型数组
     */
    public static double[] getDoubleArray(List<Integer> integerList) {
        Integer[] var1 = integerList.toArray(new Integer[0]);
        double[] var2 = new double[var1.length];
        int i = 0;
        for (Integer v : var1) {
            var2[i++] = v;
        }
        return var2;
    }

    /**
     * 去掉取前半部分 percentage 长度的数
     *
     * @param array      double数组
     * @param percentage 占的百分比(小数)
     * @return double数组
     */
    public static double[] getArrayByFront(double[] array, double percentage) {
        int index = MathArrayUtils.getPercentageIndex(array, percentage);
        return Arrays.copyOfRange(array, index, array.length);
    }

    /**
     * 去掉取后半部分 percentage 长度的数
     *
     * @param array      double数组
     * @param percentage 占的百分比(小数)
     * @return double数组
     */
    public static double[] getArrayByBack(double[] array, double percentage) {
        int index = MathArrayUtils.getPercentageIndex(array, percentage);
        return Arrays.copyOfRange(array, 0, array.length - index);
    }

    /**
     * 去掉取两头 percentage 长度的数
     *
     * @param array      double数组
     * @param percentage 占的百分比(小数)
     * @return double数组
     */
    public static double[] getArrayFrontAndBack(double[] array, double percentage) {
        int index = MathArrayUtils.getPercentageIndex(array, percentage);
        return Arrays.copyOfRange(array, index, array.length - index);
    }

}

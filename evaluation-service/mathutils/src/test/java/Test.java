
import mathutils.array.MathArrayUtils;
import mathutils.sorts.QuickSort;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-03-21 2:46 PM
 */

public class Test {

    @org.junit.Test
    public void testGetDoubleArray() {
        List<Integer> integerList = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            integerList.add(RandomUtils.nextInt(10, 100));
        }
        double[] a = MathArrayUtils.ListIntegerToArray(integerList);

        System.out.println(Arrays.toString(a));
    }

    @org.junit.Test
    public void testGetPercentageIndex() {
        List<Integer> integerList = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            integerList.add(RandomUtils.nextInt(10, 100));
        }
        double[] a = MathArrayUtils.ListIntegerToArray(integerList);
        System.out.println(MathArrayUtils.getPercentageIndex(a, 0.1d));
    }

    @org.junit.Test
    public void testQuickSort() {
        List<Integer> integerList = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            integerList.add(RandomUtils.nextInt(10, 100));
        }
        double[] a = MathArrayUtils.ListIntegerToArray(integerList);
        QuickSort.quickSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

    @org.junit.Test
    public void testGetArrayByFront() {
        double[] a = new double[20];
        for (int i = 0; i < 20; i++) {
            a[i] = RandomUtils.nextInt(10, 100);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(MathArrayUtils.getArrayByFront(a, 0.1D)));
    }

    @org.junit.Test
    public void testGetArrayByBack() {
        double[] a = new double[20];
        for (int i = 0; i < 20; i++) {
            a[i] = RandomUtils.nextInt(10, 100);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(MathArrayUtils.getArrayByBack(a, 0.1D)));
    }

    @org.junit.Test
    public void testGetArrayFrontAndBack() {
        double[] a = new double[20];
        for (int i = 0; i < 20; i++) {
            a[i] = RandomUtils.nextInt(10, 100);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(MathArrayUtils.getArrayFrontAndBack(a, 0.1D)));
    }

}

package mathutils.sorts;

/**
 * @Author: tusdasa
 * @Date: 2020-03-21 1:59 PM
 */

public class QuickSort {
    // 快速排序，a是数组，n表示数组的大小
    public static void quickSort(double[] a, int n) {
        quickSortInternally(a, 0, n - 1);
    }

    // 快速排序递归函数，p,r为下标
    private static void quickSortInternally(double[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    private static int partition(double[] a, int p, int r) {
        double pivot = a[r];
        int i = p;
        for (int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                if (i == j) {
                    ++i;
                } else {
                    double tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

        double tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        return i;
    }
}

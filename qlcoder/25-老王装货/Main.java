import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcw on 2017/06/02.
 */
public class Main {

    public static List<Integer> maxPath = new ArrayList<Integer>();
    public static int curMaxWeight = 0;

    public static int MAX_WEIGHT = 5000;

    public static int[] arr = new int[]{509, 838, 924, 650, 604, 793, 564, 651, 697, 649, 747, 784, 701, 605, 644};

    public static void func(int index, int curWeight, List<Integer> path) {
        if (index == arr.length - 1) {
            //预防数组越界
            return;
        }
        List<Integer> list = new ArrayList<Integer>();
        list.addAll(path);
        func(index + 1, curWeight, list);

        curWeight += arr[index];
        path.add(index);
        if (curWeight > curMaxWeight && curWeight < MAX_WEIGHT) {
            //符合条件，赋值
            curMaxWeight = curWeight;
            maxPath.clear();
            maxPath.addAll(path);
        }
        if (curWeight < MAX_WEIGHT) {
            func(index + 1, curWeight, path);
        }
    }

    public static void main(String[] args) {
        Main.func(0, 0, new ArrayList<Integer>());
        System.out.println(Main.curMaxWeight);
        System.out.println(Main.maxPath);
    }
}
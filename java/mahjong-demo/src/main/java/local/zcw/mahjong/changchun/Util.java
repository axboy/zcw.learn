package local.zcw.mahjong.changchun;

import local.zcw.mahjong.base.CardDown;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zcw on 2017/05/28.
 */
public class Util {

    /**
     * 检查是否可以和牌
     *
     * @param handList;手里的牌
     * @param cardDowns;吃碰杠的牌
     * @param card;抽的牌
     * @return
     */
    public static int check_hu(List<Byte> handList, List<CardDown> cardDowns, byte card) {
        return check_hu_pihu(handList, cardDowns, card);
    }

    /**
     * 检查是否可以屁胡
     *
     * @param handList;
     * @param cardDowns;
     * @param card
     * @return
     */
    private static int check_hu_pihu(List<Byte> handList, List<CardDown> cardDowns, byte card) {
        //1、找出所有可能的将牌
        //2、去除将牌，剩下的牌判断是否符合123，111条件
        Set<Byte> jiangSet = new HashSet<Byte>();           //所有可能作为将牌的set
        List<Byte> tmpList = new ArrayList<Byte>();         //临时数组，存储所有手牌
        tmpList.addAll(handList);
        tmpList.add(card);
        while (tmpList.size() > 0) {
            byte b = tmpList.get(0);
            tmpList.remove(0);
            while (tmpList.remove(new Byte(b))) {
                jiangSet.add(b);
            }
        }

        tmpList.clear();
        tmpList.addAll(handList);
        tmpList.add(card);

        System.out.println("将Set:" + jiangSet);
        //遍历所有的将牌的和牌情况
        for (byte jiang : jiangSet) {
            List<Byte> list = new ArrayList<Byte>();
            list.addAll(tmpList);
            //删除将牌
            list.remove(new Byte(jiang));
            boolean res = list.remove(new Byte(jiang));
            System.out.println("去除将牌结果：" + res);
            if (!res || list.size() % 3 != 0) {
                //如果去除两张将牌失败，或者去除将牌后手牌长度不是3的倍数
                System.out.println("去除将牌，长度错误");
                break;
            }
            int result = check_shunzi(list);
            if (result > 0) {
                return result;
            }
        }
        return -1;
    }

    /**
     * 检查顺子
     * @param list
     * @return
     */
    private static int check_shunzi(List<Byte> list) {
        System.out.println("check_shunzi:" + list);
        if (list.size() == 0) {
            System.out.println("hule");
            return 1;
        }
        byte b = list.get(0);
        byte b_2 = (byte) (b - 2);
        byte b_1 = (byte) (b - 1);
        byte b1 = (byte) (b + 1);
        byte b2 = (byte) (b + 2);
        list.remove(new Byte(b));
        //符合条件，-2，-1，0
        if (list.contains(b_2) && list.contains(b_1)) {
            int res = check_shunzi(removeShunZiCard(list, b_2, b_1));
            System.out.println("-2,-1,0 => " + res);
            if (res > 0) {
                return res;
            }
        }
        //符合条件，-1，0，1
        if (list.contains(b_1) && list.contains(b1)) {
            int res = check_shunzi(removeShunZiCard(list, b_1, b1));
            System.out.println("-1,0,1 => " + res);
            if (res > 0) {
                return res;
            }
        }
        //符合条件0，1，2
        if (list.contains(b1) && list.contains(b2)) {
            int res = check_shunzi(removeShunZiCard(list, b1, b2));
            System.out.println("0,1,2 => " + res);
            if (res > 0) {
                return res;
            }
        }
        //符合条件0，0，0
        List<Byte> newList = removeKeZiCard(list, b);
        if (newList != null) {
            int res = check_shunzi(newList);
            System.out.println("0,0,0 => " + res);
            if (res > 0) {
                return res;
            }
        }
        return -1;
    }

    /**
     * 移除符合条件的顺子牌
     * @param list
     * @param b1
     * @param b2
     * @return
     */
    private static List<Byte> removeShunZiCard(List<Byte> list, byte b1, byte b2) {
        List<Byte> newList = new ArrayList<Byte>();
        newList.addAll(list);
        newList.remove(new Byte(b1));
        newList.remove(new Byte(b2));
        return newList;
    }

    /**
     * 移除刻子牌
     * @param list
     * @param b
     * @return
     */
    private static List<Byte> removeKeZiCard(List<Byte> list, byte b) {
        List<Byte> newList = new ArrayList<Byte>();
        newList.addAll(list);
        newList.remove(new Byte(b));
        boolean boo = newList.remove(new Byte(b));
        if (boo) {
            return newList;
        } else {
            return null;
        }
    }
}

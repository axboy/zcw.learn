package local.zcw.mahjong.changchun;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcw on 2017/05/28.
 */
public class Main {
    public static void main(String[] args) {
        List<Byte> list = new ArrayList<Byte>();

        list.add(new Byte((byte) 0x03));
        list.add(new Byte((byte) 0x04));
        list.add(new Byte((byte) 0x05));

        list.add(new Byte((byte) 0x04));
        list.add(new Byte((byte) 0x05));
        list.add(new Byte((byte) 0x06));

        list.add(new Byte((byte) 0x16));
        list.add(new Byte((byte) 0x17));
        list.add(new Byte((byte) 0x15));

        list.add(new Byte((byte) 0x23));
        list.add(new Byte((byte) 0x23));
        //list.add(new Byte((byte)0x23));

        list.add(new Byte((byte) 0x01));
        list.add(new Byte((byte) 0x01));

        System.out.println(Util.check_hu(list, null, (byte) 0x23));
    }
}

package net.kingsilk.util;

/**
 * Created by zcw on 16-11-29.
 */
public class StringTools {
    private final static byte[] hex = "0123456789ABCDEF".getBytes();

    /**
     * 从字节数组到16进制字符串转换，每两位隔一个空格
     */
    public static String byte2hex(byte[] b) { // 一个字节的数，
        /* 每两位不隔空格
        byte[] buff = new byte[2 * b.length];
        for (int i = 0; i < b.length; i++) {
            buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
            buff[2 * i + 1] = hex[b[i] & 0x0f];
        }
        return new String(buff);
        */
        String h = "";
        for (int i = 0; i < b.length; i++) {
            String temp = Integer.toHexString(b[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h = h + " " + temp;
        }
        return h.toUpperCase();
    }

    /**
     * 从16进制字符串到字节数组转换
     */
    public static byte[] hex2byte(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }
}

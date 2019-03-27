package org.zhangyc.test.bytes;

public class StartWith {

    public static void main(String[] args) {
        String str = "eos-001";

        boolean flag = startWith(str.getBytes(), "e".getBytes());
        System.out.println("start with e = "+ flag);
        flag = startWith(str.getBytes(), "eo".getBytes());
        System.out.println("start with eo = "+ flag);
        flag = startWith(str.getBytes(), "eos".getBytes());
        System.out.println("start with eos = "+ flag);
        flag = startWith(str.getBytes(), "eos-".getBytes());
        System.out.println("start with eos- = "+ flag);
        flag = startWith(str.getBytes(), "eos-0".getBytes());
        System.out.println("start with eos-0 = "+ flag);
        flag = startWith(str.getBytes(), "eos-00".getBytes());
        System.out.println("start with eos-00 = "+ flag);
        flag = startWith(str.getBytes(), "eos-001".getBytes());
        System.out.println("start with eos-001 = "+ flag);

        flag = startWith(str.getBytes(), "et".getBytes());
        System.out.println("start with et = "+ flag);

        flag = startWith(str.getBytes(), "eos-002".getBytes());
        System.out.println("start with eos-002 = "+ flag);

        flag = startWith(str.getBytes(), "eos-0021".getBytes());
        System.out.println("start with eos-0021 = "+ flag);
    }

    public static boolean startWith(byte[] bytes, byte[] prefix){
        byte ta[] = bytes;
        int to = 0;
        byte pa[] = prefix;
        int po = 0;
        int pc = prefix.length;
        // Note: toffset might be near -1>>>1.
        if ((prefix.length > bytes.length)) {
            return false;
        }
        while (--pc >= 0) {
            if (ta[to++] != pa[po++]) {
                return false;
            }
        }
        return true;
    }
}

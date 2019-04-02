package org.zhangyc.test.bytes;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class BytesConcat {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byte[] byte_1 = byteBuffer.putLong(1l).array();
        System.out.println("byte_1="+ Arrays.toString(byte_1));
        byte[] byte_1_1 = {byte_1[7]};
        System.out.println("byte_1_1="+ Arrays.toString(byte_1_1));
        byteBuffer = ByteBuffer.allocate(8);
        byte[] byte_2 = byteBuffer.putLong(2l).array();
        System.out.println("byte_2="+ Arrays.toString(byte_2));
        byte[] byte_3 = byteMerger(byte_1_1, byte_2);
        System.out.println("byte_3="+ Arrays.toString(byte_3));
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
}

package org.zhangyc.test.rocksdb;

import java.nio.ByteBuffer;

public class ByteUtils {
    public static byte[] longToBytes(long id) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        return byteBuffer.putLong(id).array();
    }

    public static byte[] intToBytes(int id) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        return byteBuffer.putInt(id).array();
    }

    public static byte[] bytesToBytes(byte[] key) {
        byte[] byte_3 = new byte[8];
        System.arraycopy(key, 0, byte_3, 0, key.length);
        return byte_3;
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    public static long bytesToLong(byte[] bytes){
        long ret = 0;
        for (int i = 0; i < bytes.length; i++) {
            ret += (long) (bytes[bytes.length - i - 1] & 0xFF) << (long) (i * 8);
        }
        return ret;
    }
}

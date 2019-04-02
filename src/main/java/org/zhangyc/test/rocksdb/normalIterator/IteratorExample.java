package org.zhangyc.test.rocksdb.normalIterator;

import org.rocksdb.*;

public class IteratorExample {
    static {
        RocksDB.loadLibrary();
    }

    public static void main(String[] args) throws RocksDBException {
        Options options = new Options().setCreateIfMissing(true);
        RocksDB client = RocksDB.open(options, "data/prefixIterator");

        try {
            //正常迭代器
            RocksIterator iterator = client.newIterator();
            for (iterator.seekToFirst(); iterator.isValid(); iterator.next()) {
                String key = new String(iterator.key());
                String value = new String(iterator.value());
                System.out.println(key + " --> " + value);
            }

            //开始结束
            ReadOptions readOptions = new ReadOptions().setIterateUpperBound(new Slice("btc-0011"));
            iterator = client.newIterator(readOptions);
            System.out.println("-----------------------------------");
            for (iterator.seekForPrev("btc-0002".getBytes()); iterator.isValid(); iterator.next()) {
                String key = new String(iterator.key());
                String value = new String(iterator.value());
                System.out.println(key + " --> " + value);
            }
        } finally {
            client.close();
        }


    }
}

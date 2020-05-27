package org.zhangyc.test.rocksdb;

import org.rocksdb.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: yichu.zhang
 * @Date: 2019-12-13 16:15
 */
public class SortedTest {
    public static void main(String[] args) throws IOException, RocksDBException {

        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();

        // the Options class contains a set of configurable DB options
        // that determines the behavior of a database.
        Options options = new Options().setCreateIfMissing(true);
        options = options.setWalDir("");
        options = options.setParanoidChecks(true);
        System.out.println("WAL : " + options.walDir());

        SkipListMemTableConfig memTableConfig = new SkipListMemTableConfig();

        //a factory method that returns a RocksDB instance
        RocksDB client = RocksDB.open(options, "data/sorted");

        List<byte[]> keys = Stream.of("1","2","3", "10",
                "10", "11", "12", "20", "21", "30", "31",
                "101", "102", "110", "112", "201", "210", "301")
                .map(String::getBytes).collect(Collectors.toList());

        for (byte[] line : keys) {
            System.out.println("key = " + new String(line));
            client.put(line, line);
        }

        RocksIterator iter = client.newIterator();
        for(iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println("iter key:" + Arrays.toString(iter.key()) + ", key value:" + new String(iter.value()));
            client.delete(iter.key());
        }

        byte[] b1 = ByteUtils.byteMerger(ByteUtils.intToBytes(127), ByteUtils.intToBytes(1));
        System.out.println(Arrays.toString(ByteUtils.byteMerger(b1, ByteUtils.longToBytes(Long.MAX_VALUE))));
        System.out.println(Arrays.toString(ByteUtils.longToBytes(Long.MAX_VALUE)));


        System.out.println("使用指定长度的byte数组保存key, 长度不够的， 在左边用0补齐");

        for (byte[] line : keys) {
            System.out.println("key = " + new String(line));
            client.put(ByteUtils.longToBytes(ByteUtils.bytesToLong(line)), line);
        }


        iter = client.newIterator();
        for(iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println("iter key:" + Arrays.toString(iter.key()) + ", key value:" + new String(iter.value()));
            client.delete(iter.key());
        }

        client.close();
    }
}

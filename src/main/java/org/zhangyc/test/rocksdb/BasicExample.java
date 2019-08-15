package org.zhangyc.test.rocksdb;

import org.rocksdb.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://github.com/facebook/rocksdb/wiki/RocksJava-Basics
 */
public class BasicExample {

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

        // a factory method that returns a RocksDB instance
        RocksDB client = RocksDB.open(options, "data/compare");

        List<byte[]> keys = Stream.of("workman", "worked", "working", "worker", "1", "10" ,"11", "2", "a", "A", "b")
                .map(String::getBytes).collect(Collectors.toList());

        for (byte[] line : keys) {
            client.put(line, line);
        }

        System.out.println(new String(client.get("workman".getBytes())));
        StringBuilder sb = new StringBuilder();
        System.out.println("workman-exists="+client.keyMayExist("workman".getBytes(), sb));
        System.out.println("workman-sb="+sb.length());
        byte[] oldValue = client.get("workman".getBytes());
        System.out.println(oldValue != null);
        sb = new StringBuilder();
        System.out.println("workman1-exists="+client.keyMayExist("workman1".getBytes(), sb));
        System.out.println("workman1-sb="+sb.length());
        byte[] oldValue1 = client.get("workman1".getBytes());
        System.out.println(oldValue1 == null);

//
//        for (Map.Entry<byte[], byte[]> entry : client.multiGet(keys).entrySet()) {
//            String key = Arrays.toString(entry.getKey());
//            String val = Arrays.toString(entry.getValue());
////            String key = new String(entry.getKey());
////            String val = new String(entry.getValue());
//            System.out.printf("%-7s --> %-7s\n", key, val);
//        }

        //remove some key-value pairs
//        for (byte[] key : keys) {
//            client.remove(key);
//        }

        RocksIterator iter = client.newIterator();
        for(iter.seekToFirst(); iter.isValid(); iter.next()) {
//            System.out.println("iter key:" + new String(iter.key()) + ", iter value:" + new String(iter.value()));
            System.out.println("iter key:" + Arrays.toString(iter.key()) + ", iter value:" + Arrays.toString(iter.value()));
        }

        //
        RocksIterator iterator = client.newIterator();
        iterator.seekToFirst();
        if(iterator.isValid()) {
            System.out.println("key=" + new String(iterator.key()) + "; value=" + new String(iterator.value()));
        }

        iterator.seekToLast();
        if(iterator.isValid()) {
            System.out.println("key=" + new String(iterator.key()) + "; value=" + new String(iterator.value()));
        }

        ReadOptions readOptions = new ReadOptions();
        readOptions = readOptions.setVerifyChecksums(true);

//        for (Map.Entry<byte[], byte[]> entry : client.multiGet(readOptions, keys).entrySet()) {
//            String key = new String(entry.getKey());
//            String val = new String(entry.getValue());
//            System.out.printf("%-7s --> %-7s\n", key, val);
//        }


        Snapshot snapshot = client.getSnapshot();
        System.out.println(snapshot.getSequenceNumber());

        WriteOptions writeOptions = new WriteOptions();
        writeOptions.setDisableWAL(false);

        client.close();
    }
}

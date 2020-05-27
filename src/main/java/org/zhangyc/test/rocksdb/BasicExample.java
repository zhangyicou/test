package org.zhangyc.test.rocksdb;

import org.rocksdb.*;

import java.io.IOException;
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

        System.out.println("minWriteBufferNumberToMerge = " + options.minWriteBufferNumberToMerge());
        System.out.println("maxWriteBufferNumber = " + options.maxWriteBufferNumber());
        System.out.println("maxTableFilesSizeFIFO = " + options.maxTableFilesSizeFIFO());
        System.out.println("maxTableFilesSizeFIFO= " + options.maxTableFilesSizeFIFO() / 1024);
        System.out.println("maxTableFilesSizeFIFO= " + options.maxTableFilesSizeFIFO() / 1024/1024);

        System.out.println("maxTableFilesSizeFIFO = " + options.compactionOptionsFIFO());

        SkipListMemTableConfig memTableConfig = new SkipListMemTableConfig();

        // a factory method that returns a RocksDB instance
        RocksDB client = RocksDB.open(options, "data/compare");

        List<byte[]> keys = Stream.of("a12340","a12341","a12342","a12343","a12344","a12345")
                .map(String::getBytes).collect(Collectors.toList());

        for (byte[] line : keys) {
            System.out.println("key = " + new String(line));
            client.put(line, line);
        }


        client.delete("workman".getBytes());
        client.delete("a12344".getBytes());

        RocksIterator iter = client.newIterator();
        for(iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println("iter key:" + new String(iter.key()) + ", iter value:" + new String(iter.value()));
            //System.out.println("iter key:" + Arrays.toString(iter.key()) + ", iter value:" + Arrays.toString(iter.value()));
        }

        //
        RocksIterator iterator = client.newIterator();
        iterator.seekToFirst();
        if(iterator.isValid()) {
            System.out.println("to first key=" + new String(iterator.key()) + "; value=" + new String(iterator.value()));
        }

        iterator.seekToLast();
        if(iterator.isValid()) {
            System.out.println("to last key=" + new String(iterator.key()) + "; value=" + new String(iterator.value()));
        }

        System.out.println("a12344 value is " + client.get("a12344".getBytes()));

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

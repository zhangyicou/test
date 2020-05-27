package org.zhangyc.test.rocksdb.prefixIterator;

import org.rocksdb.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 前缀迭代器
 */
public class PrefixIterator {

    public static void main(String[] args) throws IOException, RocksDBException {

        RocksDB.loadLibrary();

        Statistics statistics = new Statistics();
        Options options = new Options().setCreateIfMissing(true).setParanoidChecks(true)
                .useFixedLengthPrefixExtractor(3)
                .setUseDirectReads(true).setStatistics(statistics);

        RocksDB client = RocksDB.open(options, "data/prefixIterator");

        List<byte[]> keys = Stream.of("btc-0001", "btc-0011", "btc-0002", "btc-0003",
                "eos-0001", "eos-0002", "eos-0003",
                "etc-0001", "etc-0002", "etc-0003",
                "ltc-0001", "ltc-0002", "ltc-0003" )
                .map(String::getBytes).collect(Collectors.toList());

        for (byte[] line : keys) {
            client.put(line, line);
        }


        ReadOptions readOptions = new ReadOptions().setTotalOrderSeek(true).setPrefixSameAsStart(true);
        String prefix = "eos-0002";
        RocksIterator iterator = client.newIterator(readOptions);
        for (iterator.seek(prefix.getBytes());
             iterator.isValid()/* && startWith(iterator.key(), prefix.getBytes())*/;
             iterator.next()) {

             String key = new String(iterator.key());
             String value = new String(iterator.value());
             System.out.println(key + " --> " + value);
        }

        client.close();
        options.dispose();
    }

    public static boolean startWith(byte[] bytes, byte[] prefix){
        byte[] ta = bytes;
        int to = 0;
        byte[] pa = prefix;
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

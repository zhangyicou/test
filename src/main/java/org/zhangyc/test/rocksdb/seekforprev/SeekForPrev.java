package org.zhangyc.test.rocksdb.seekforprev;

import org.rocksdb.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeekForPrev {
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
        String prefix = "btc-0002";
        RocksIterator iterator = client.newIterator(readOptions);
        for (iterator.seekForPrev(prefix.getBytes());
             iterator.isValid()/* && startWith(iterator.key(), prefix.getBytes())*/;
             iterator.next()) {

            String key = new String(iterator.key());
            String value = new String(iterator.value());
            System.out.println(key + " --> " + value);
        }

        client.close();
        options.dispose();
    }
}

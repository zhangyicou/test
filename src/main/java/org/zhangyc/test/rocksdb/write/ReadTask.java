package org.zhangyc.test.rocksdb.write;

import org.rocksdb.ReadOptions;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;
import org.zhangyc.test.rocksdb.ByteUtils;

public class ReadTask extends Thread {
    private RocksDB client;
    private long start;

    public ReadTask(RocksDB client, int start){
        this.client = client;
        this.start = start;
    }
    @Override
    public void run() {

        /**
         * 您可以通过为传递给NewIterator（）的读取选项设置ReadOptions.iterate_upper_bound来指定范围查询的上限。
         * 通过设置此选项，RocksDB不必在上限之后找到下一个键。 在某些情况下，可以避免某些I / O或计算。
         * 在某些特定工作负载中，改进可能很大。 注意它适用于前向和后向迭代。
         */
        ReadOptions readOptions = new ReadOptions().setTailing(true)
                .setVerifyChecksums(false).setReadaheadSize(512);

        try {

            RocksIterator iterator = this.client.newIterator(readOptions);

            String key = "";
            iterator.seekToFirst();
            long value = 0l;
            while (true){
                if (iterator.isValid()) {
                    value = ByteUtils.bytesToLong(iterator.key());
                    System.out.println(key + " --> " + value);
                    iterator.next();
                } else {
                    iterator.seek(key.getBytes());
                    if (iterator.isValid()) {
                        iterator.next();
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
//        while (true) {
//            RocksIterator iterator = client.newIterator();
//            for (iterator.seekForPrev(ByteUtils.longToBytes(this.start)); iterator.isValid(); iterator.next()) {
//                this.start = ByteUtils.bytesToLong(iterator.key());
//                System.out.println(ByteUtils.bytesToLong(iterator.key()) + " : " + ByteUtils.bytesToLong(iterator.value()));
//            }
//            Thread.yield();
//        }
    }
}

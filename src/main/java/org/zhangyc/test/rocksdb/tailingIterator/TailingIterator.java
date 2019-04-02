package org.zhangyc.test.rocksdb.tailingIterator;

import org.rocksdb.Options;
import org.rocksdb.ReadOptions;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;

import java.util.concurrent.TimeUnit;

public class TailingIterator {
    public static void main(String[] args) throws Exception {
        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true)
                .setCreateMissingColumnFamilies(true).setUseDirectReads(true)
                .setUseDirectIoForFlushAndCompaction(true).setMaxWriteBufferNumber(512).setMaxTotalWalSize(512);

        RocksDB client = RocksDB.open(options, "data/tailInterator");

        Thread reader = new TailingIterator().new ReadThread(client);
        reader.start();
        Thread writer = new TailingIterator().new WriteThread(client);
        writer.start();
    }

    class ReadThread extends  Thread{
        private RocksDB db;

        public ReadThread(RocksDB client){
            this.db = client;
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

                RocksIterator iterator = this.db.newIterator(readOptions);
//                for (iterator.seekToFirst();iterator.isValid(); iterator.next()) {
//                    String key = new String(iterator.key());
//                    String value = new String(iterator.value());
//                    System.out.println(key + " --> " + value);
//                }


                String key = "";
                iterator.seekToFirst();
//                for (; ; ) {
//                    if (iterator.isValid()) {
//                        key = new String(iterator.key());
//                        String value = new String(iterator.value());
//                        System.out.println(key + " --> " + value);
//                        iterator.next();
//                    } else {
//                        iterator.seek(key.getBytes());
//                        if (iterator.isValid()) {
//                            iterator.next();
//                        }
//                    }
//                }

               while (true){
                   if (iterator.isValid()) {
                       key = new String(iterator.key());
                       String value = new String(iterator.value());
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
        }
    }

    class WriteThread extends Thread{
        private RocksDB db;
        public WriteThread(RocksDB client){
            this.db = client;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                e.printStackTrace();
            }

            for (int index = 0; index <= 1024 * 1024 * 100; index++) {
                String key = String.format("%08d", index);
                try {
                    db.put(key.getBytes(), key.getBytes());
//                    System.out.print("-" + key);
                    if(index > 1 && index % 100 == 0){
                        System.out.println("------------------------");
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

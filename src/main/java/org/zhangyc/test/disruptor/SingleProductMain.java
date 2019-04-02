package org.zhangyc.test.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkProcessor;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * Created by zhangyicou on 2019/1/19.
 */
public class SingleProductMain {
    public static void main(String[] args) {
        int bufferSize = 4;//环形队列长度，必须是2的N次方
        EventFactory<LongEvent> eventFactory = new LongEventFactory();

        /**
         * 定义Disruptor，基于单生产者，阻塞策略
         */
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,bufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE,new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new MyExceptionHandler());
        //
//        System.out.println("------并行执行----------------------------------------------");
//        //并行执行
        disruptor.handleEventsWith(new C11EventHandler()).then(new C12EventHandler(), new C21EventHandler(), new C22EventHandler());
//        System.out.println("----------------------------------------------------------");

//        System.out.println("------串行依次执行------------------------------------------");
//        //串行依次执行
//        disruptor.handleEventsWith(new C11EventHandler()).then(new C12EventHandler()).then(new C21EventHandler()).then(new C22EventHandler());
//        System.out.println("----------------------------------------------------------");

//        System.out.println("------菱形方式执行------------------------------------------");
//        //菱形方式执行
//        disruptor.handleEventsWith(new C11EventHandler(),new C12EventHandler()).then(new C21EventHandler());
//        System.out.println("----------------------------------------------------------");
//
//        C11EventHandler handler11 = new C11EventHandler();
//        C12EventHandler handler12 = new C12EventHandler();
//        C21EventHandler handler21 = new C21EventHandler();
//        C22EventHandler handler22 = new C22EventHandler();
//        disruptor.handleEventsWith(handler11, handler21);
//        disruptor.after(handler11).handleEventsWith(handler12);
//        disruptor.after(handler21).handleEventsWith(handler22);
//        disruptor.after(handler12, handler22).handleEventsWith(handler11);


//        System.out.println("------链式并行计算------------------------------------------");
//        disruptor.handleEventsWith(new C11EventHandler()).then(new C12EventHandler());
//        disruptor.handleEventsWith(new C21EventHandler()).then(new C22EventHandler());
//        disruptor.handleEventsWith(new C21EventHandler()).then(new C22EventHandler());
//        System.out.println("----------------------------------------------------------");

//        System.out.println("------并行计算实现,c1,c2互相不依赖,同时C1，C2分别有2个实例--------");
//        disruptor.handleEventsWithWorkerPool(new C11EventHandler(),new C11EventHandler());
//        disruptor.handleEventsWithWorkerPool(new C21EventHandler(),new C21EventHandler());
//        System.out.println("----------------------------------------------------------");

//        System.out.println("------串行依次执行------------------------------------------");
//        //串行依次执行
//        disruptor.handleEventsWithWorkerPool(new C11EventHandler(),new C11EventHandler(),new C21EventHandler(),new C21EventHandler())
//                .thenHandleEventsWithWorkerPool(new C12EventHandler());
//        System.out.println("----------------------------------------------------------");

        disruptor.start();

        /////////////////////////////////////////////////////////////////////
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        Long[] ary = {100L, 110L};

        for(int i = 0; i <= 3; i++) {
            /**
             * 输入10
             */
            ringBuffer.publishEvent(new LongEventTranslator(), i * 10L);
//            ringBuffer.publishEvents(new LongEventTranslator(), ary);
            //ringBuffer.publishEvent(new LongEventTranslator(),100L);
        }

        disruptor.shutdown();
    }

}

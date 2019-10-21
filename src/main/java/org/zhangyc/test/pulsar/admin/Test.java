package org.zhangyc.test.pulsar.admin;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.common.policies.data.SubscriptionStats;
import org.apache.pulsar.common.policies.data.TopicStats;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Author: yichu.zhang
 * @Date: 2019-09-19 14:44
 */
public class Test {
    public static void main(String[] args) throws PulsarClientException, PulsarAdminException {
        String url = "http://pulsar-options.dev-okex.svc.cluster.local:8080";//"http://192.168.80.80:8080";
        PulsarAdmin admin = PulsarAdmin.builder().serviceHttpUrl(url).build();

        //主题列表
        List<String> topicList = admin.topics().getList("public/default");
        topicList.stream().forEach(System.out::println);

        System.out.println("topic print end.........");

        final AtomicLong msgBacklogSum=new AtomicLong(0);
        AtomicReference<TopicStats> topicStats = new AtomicReference<>();
        //获取统计数据
        topicList.stream().forEach(topicName -> {
            try {
                topicStats.set(admin.topics().getStats(topicName));
                //System.out.println(JSON.toJSONString(topicStats));

                List<SubscriptionStats> statsList = topicStats.get().subscriptions.values().stream().filter(myTopic -> myTopic.msgBacklog > 0).collect(Collectors.toList());
                if(CollectionUtils.isNotEmpty(statsList)) {
                    Long sum = statsList.stream().map(myTopic -> myTopic.msgBacklog).reduce((a, b) -> {
                        System.out.println("a =" + a + ", b =" + b);
                        return a + b;
                    }).get();

                    msgBacklogSum.addAndGet(sum);
                    System.out.println("sum =" + sum);
                }
            } catch (PulsarAdminException e) {
                e.printStackTrace();
            }

        });

        System.out.println("msgBacklog Sum = "+msgBacklogSum.get());

    }
}

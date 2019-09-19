package org.zhangyc.test.lock;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: yichu.zhang
 * @Date: 2019-09-18 19:14
 */

@Data
@Builder
@ToString
public class Event {
    private int id;
    private String name;
    private Long time;
}

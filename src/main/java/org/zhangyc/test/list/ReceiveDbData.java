package org.zhangyc.test.list;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 柜台撮合事件接受模块按类型处理结果数据
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2019-04-30 16:46
 */
@Data
public class ReceiveDbData {
    /**
     * 待插入event表列表
     */
    private List<Integer> orderEventList;
    /**
     * 更新event表的command_status
     */
    private List<Long> commandIdList;

    private List<Long> sequenceSyncList;

    public ReceiveDbData() {
        super();
        this.orderEventList = new ArrayList<>();
        this.commandIdList = new ArrayList<>();
        this.sequenceSyncList = new ArrayList<>();
    }

}

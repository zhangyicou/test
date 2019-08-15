package org.zhangyc.test.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        ResultVO resultVO = ResultVO.builder().code(1).message("success")
                .data(Demo.builder().id(1).name("zhangyicou").address("beijingshi").createTime(new Date()).build()).build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultVO);
        System.out.println("json="+json);

        ResultVO1 user = mapper.readValue(json, ResultVO1.class);
        System.out.println(user.getCode());
        System.out.println(user.getData().getCreateTime().getTime());
    }
}

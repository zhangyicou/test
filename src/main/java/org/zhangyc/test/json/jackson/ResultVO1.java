package org.zhangyc.test.json.jackson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO1 {
    private int code;
    private String message;
    private Demo data;
}

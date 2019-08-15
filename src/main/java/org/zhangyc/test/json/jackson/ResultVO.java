package org.zhangyc.test.json.jackson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    private int code;
    private String message;
    private T data;
}

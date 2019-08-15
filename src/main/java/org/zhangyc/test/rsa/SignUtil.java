package org.zhangyc.test.rsa;

import com.google.common.base.Charsets;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jiangchuan.deng on 2017/10/20.
 */
public class SignUtil {

    public static boolean verify(long userId, int currencyId, BigDecimal amount, String tradeNo,
                                 int target, String sign, String publicKey) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("amount", amount + "");
        params.add("currencyId", currencyId + "");
        params.add("userId", userId + "");
        params.add("tradeNo", tradeNo);
        params.add("target", target + "");

        return RSAUtil.verify( createLinkString( params ), sign, publicKey, Charsets.UTF_8.toString() );
    }

    public static String sign(MultiValueMap<String, String> params, String privateKey) {
        return RSAUtil.sign( createLinkString( params ), privateKey, Charsets.UTF_8.toString() );
    }

    public static boolean verify(MultiValueMap<String, String> params, String publicKey) {
        List<String> signList = params.remove("sign");
        return RSAUtil.verify(createLinkString(params), signList.get(0), publicKey, Charsets.UTF_8.toString());
    }

    public static void main(String[] args) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.put("userId", Collections.singletonList("36203793"));
        map.put("brokerId", Collections.singletonList("0"));
        map.put("currencyId", Collections.singletonList("0"));
        map.put("target", Collections.singletonList("36203794"));
        map.put("tradeNo", Collections.singletonList("fdacb07e-2fa6-4c5e-9334-f3a8b41588bw"));
        map.put("amount", Collections.singletonList("10"));
        String prikey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAO2Wo/qHwpeMYdMHC1eI2EdC2DVs6tVP/M7fo/K57rJ7HWmZbQpAwadzAU2ckqjZkRUvJgU00r2Lgq6VXoJOd9quu81150i/Pgdn+ViLmKSp4Dhcq7wgUHznoJLkrhmIgmC4gknIMU5DrzM/5ToRK9deaW3jVejmi9p0K8D0TiX1AgMBAAECgYEA6HDJitCJQ2UXS7MHmKMMHWO7TUF62ijAR9CqgE0Bx2IFm4t8D1zdWSp9quW04jbA5z3Sb6OHTeOQfacq6EdGnyxVipp38E3sRU1YGES2ntSrMP98G+tckaNgkzCyj4ZiMOZcC27M44XncBnsbqo55VUO2Lrp5Kn+L9RPO8vYPQECQQD6m/CWLl4EaONF5URdWy9HrJ0Bg4MZBscP0IdSgFG+q0wlZjaMxAj3QgoVRnG3CKMQ2XXKpXhVYzZ6XUBfPxG1AkEA8rL/gKhvmnxER6bzrklTn8jwbxB16UnwGs8xRujQSP4q85fwAVHdDtTHg/uVJqxhGeyPimD+oLX+rQ8HI9RrQQJAd0HL/Hu2K90KAlH29YIV/sKquHSv5zHtep4eN2lu/RAhHU8FuHj1V0yq5bLvHE3U44jIYBXcLK4Y16QWpPyrXQJBALC1HY0qll+v3PMinsgmQYnUfyMOpBtgpz6N3v4XIxgSX2mPFcOQPFjBGieBtR48YlLCx9xkNOEEwdJ1X6h50EECQQC4YF6kRIJ+0xWaGJRI+MM46BrWbHja+DG1hFyHw1BXFmdtUOSOuaDQInn5xjnTxWqIFLKMM1iEACRxRDD6PmYt";
        System.out.println(SignUtil.sign(map, prikey));

    }

    /**
     * 拼接参数
     *
     * @param params
     * @return
     */
    public static String createLinkString(MultiValueMap<String, String> params) {
        String[] keyArr = params.keySet().toArray( new String[0] );
        StringBuilder paramStr = new StringBuilder();
        if (keyArr.length > 0) {
            Arrays.sort( keyArr );
            for (String key : keyArr) {
                paramStr.append(key).append("=").append(params.get(key).get(0)).append("&");
            }
            paramStr.setLength( paramStr.length() - 1 );
        }
        return paramStr.toString();
    }
}

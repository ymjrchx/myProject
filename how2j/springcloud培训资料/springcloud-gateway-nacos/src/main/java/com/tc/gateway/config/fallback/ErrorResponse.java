/**
 * FileName: ErrorResponse
 * Author:   tumq
 * Date:     2019/7/9 11:18
 * Description: 异常返回类
 */
package com.tc.gateway.config.fallback;

/**
 * 〈一句话功能简述〉<br> 
 * 〈异常返回类〉
 *
 * @author Richard
 * @create 2019/7/9
 */
public class ErrorResponse {

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
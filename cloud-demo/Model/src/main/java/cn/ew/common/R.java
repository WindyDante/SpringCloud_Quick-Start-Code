package cn.ew.common;

import lombok.Data;

@Data
public class R {

    private Integer code; // 状态码
    private String msg; // 提示信息
    private Object data; // 数据

    public static R ok() {
        R r = new R();
        r.setCode(200);
        return r;
    }

    public static R ok(String msg, Object data) {
        R r = new R();
        r.setCode(200);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static R err(String msg) {
        R r = new R();
        r.setCode(500);
        r.setMsg(msg);
        return r;
    }

    public static R err(Integer code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }


}

package com.platform.exception;

/**
 * 说明：数据操作自定义异常类 <br>
 * 作者：马金凯<br>
 * 更改记录： <br>
 * -------------------------------------------------------<br>
 * 改动人 时间 原因<br>
 * -------------------------------------------------------<br>
 * 马金凯 2007-12-1 创建文件<br>
 * -------------------------------------------------------<br>
 */
public class CRUDException extends Exception {

    private static final long serialVersionUID = 4570573798200354363L;

    public CRUDException(String msg) {
        super(msg);
    }

    public CRUDException(Throwable cause) {
        super(cause);
    }

    public CRUDException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

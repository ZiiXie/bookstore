package com.example.bookstore.annotation;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 4890803011331841425L;

    /** 业务错误码 */
    private Integer code;
    /** 信息描述 */
    private String message;
    /** 返回参数 */
    private T data;

    private ResultData(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    /** 业务成功返回业务代码和描述信息 */
    public static ResultData<Void> success() {
        return new ResultData<Void>(ResultStatus.SUCCESS, null);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> ResultData<T> success(T data) {
        return new ResultData<T>(ResultStatus.SUCCESS, data);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> ResultData<T> success(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return success(data);
        }
        return new ResultData<T>(resultStatus, data);
    }

    /** 业务异常返回业务代码和描述信息 */
    public static <T> ResultData<T> failure() {
        return new ResultData<T>(ResultStatus.INTERNAL_SERVER_ERROR, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> ResultData<T> failure(ResultStatus resultStatus) {
        return failure(resultStatus, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> ResultData<T> failure(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return new ResultData<T>(ResultStatus.INTERNAL_SERVER_ERROR, null);
        }
        return new ResultData<T>(resultStatus, data);
    }
}

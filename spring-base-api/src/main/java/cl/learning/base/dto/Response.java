package cl.learning.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : 常亮
 * @Date : 16:51 2019-01-14
 * @Description :
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 6483675304190334962L;
    private T result;
    private String error;
    private Boolean success = true;

    public static <T> Response<T> ok(T t) {
        Response<T> response = new Response<>();
        response.setResult(t);
        return response;
    }

    public static Response fail(String error) {
        Response response = new Response<>();
        response.setSuccess(false);
        response.setError(error);
        return response;
    }

    public static Response ok() {
        return new Response();
    }

}

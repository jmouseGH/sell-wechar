package z.demo.vo;

import lombok.Data;


/**
 * 返回给前端的数据对象
 */

/**
 * {
 *     "code": 0,
 *     "msg": "成功",
 *     "data": [
 *
 *         productInfo ...
 *
 *         ]
 * }
 * @param <T>
 */


@Data
public class ResultVo<T> {

    /** 返回的状态码 */
    private Integer code;

    /** 表示处理结果,是成功处理,还是出现异常 */
    private String msg;

    /** 返回 的具体对象 */
    private T data;


}



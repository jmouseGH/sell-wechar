package z.demo.util;

import z.demo.vo.ResultVo;

public class ResultVoUtil {

    public static ResultVo succcess ( Object object ){
        ResultVo resultVo = new ResultVo() ;
        resultVo.setMsg("success");
        resultVo.setCode(0);
        resultVo.setData(object);

        return resultVo ;
    }

    /**
     * 返回错误 信息
     * @return
     */
    public static ResultVo error  (  ){
        ResultVo resultVo = new ResultVo() ;
        resultVo.setMsg("error");
        resultVo.setCode(1);
        resultVo.setData(null);

        return resultVo ;
    }

    /**
     * 自定义 返回类型
     * @param code
     * @param msg
     * @return
     */
    public static ResultVo custom (Integer code , String msg ){
        ResultVo resultVo = new ResultVo() ;
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        resultVo.setData(null);

        return resultVo ;
    }
}

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

    public static ResultVo succcess (  ){
        ResultVo resultVo = new ResultVo() ;
        resultVo.setMsg("error");
        resultVo.setCode(1);
        resultVo.setData(null);

        return resultVo ;
    }

    public static ResultVo succcess (Integer code , String msg ){
        ResultVo resultVo = new ResultVo() ;
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        resultVo.setData(null);

        return resultVo ;
    }
}

package com.toone.v3.platform.function;

import com.toone.v3.platform.function.common.ServerFuncCommonUtils;
import com.toone.v3.platform.function.common.exception.ServerFuncException;
import com.yindangu.v3.business.VDS;
import com.yindangu.v3.business.plugin.business.api.func.IFuncContext;
import com.yindangu.v3.business.plugin.business.api.func.IFuncOutputVo;
import com.yindangu.v3.business.plugin.business.api.func.IFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 求数值的绝对值<br>
 * 代码示例:ABS(-10)返回值为10<br>
 * 参数数量:1 参数1--指定的数(数值类型) 返回值为正数<br>
 *
 * @author xugang
 * @date 2021-05-24
 */
public class ABSFunc implements IFunction {

    private final static Logger log = LoggerFactory.getLogger(ABSFunc.class);

    @Override
    public IFuncOutputVo evaluate(IFuncContext context) {

        IFuncOutputVo outputVo = context.newOutputVo();
        Object param = null;
        try {
            ServerFuncCommonUtils service = VDS.getIntance().getService(ServerFuncCommonUtils.class, ServerFuncCommonUtils.OutServer_Code);
            // 校验参数个数
            service.checkParamSize(ServerFuncCommonUtils.ABS.Function_Code(), context, 1);

            param = context.getInput(0);

            // 校验参数是否为null
            service.checkParamNull(ServerFuncCommonUtils.ABS.Function_Code(), param);

            if(!isNumeric(param.toString())) {
                throw new ServerFuncException("函数【" + ServerFuncCommonUtils.ABS.Function_Code() + "】的第1个参数必须为数字类型，参数1：" + param);
            }

            BigDecimal valBD = new BigDecimal(param.toString());
            String result = valBD.abs().stripTrailingZeros().toPlainString();

            outputVo.put(result);
            outputVo.setSuccess(true);
        } catch (ServerFuncException e) {
            outputVo.setSuccess(false);
            outputVo.setMessage(e.getMessage());
        } catch (Exception e) {
            outputVo.setSuccess(false);
            outputVo.setMessage("函数【" + ServerFuncCommonUtils.ABS.Function_Code() + "】计算有误，参数1：" + param + "，" + e.getMessage());
            log.error("函数【" + ServerFuncCommonUtils.ABS.Function_Code() + "】计算失败，请检查入参值：" + param, e);
        }

        return outputVo;
    }

    /* 判断是否为数值类型 */
    private boolean isNumeric(String str) {
        return str.matches("-?[0-9]+.?[0-9]*");
    }
}

package com.toone.v3.platform.function;

import com.toone.v3.platform.function.common.ServerFuncCommonUtils;
import com.yindangu.v3.plugin.vds.reg.api.IRegisterPlugin;
import com.yindangu.v3.plugin.vds.reg.api.builder.IFunctionBuilder;
import com.yindangu.v3.plugin.vds.reg.api.model.IComponentProfileVo;
import com.yindangu.v3.plugin.vds.reg.api.model.IFunctionProfileVo;
import com.yindangu.v3.plugin.vds.reg.api.model.IPluginProfileVo;
import com.yindangu.v3.plugin.vds.reg.api.model.VariableType;
import com.yindangu.v3.plugin.vds.reg.common.RegVds;

import java.util.ArrayList;
import java.util.List;

/**
 * 函数注册器
 * 
 * @Author xugang
 * @Date 2021/6/7 15:49
 */
public class GetIPAddressRegister implements IRegisterPlugin {

    private static final String Component_Code = "Serverfunc_GetIPAddressFunc";
    private static final String Component_Version = "3.10.0";

    @Override
    public IComponentProfileVo getComponentProfile() {
        return RegVds.getPlugin()
                .getComponentProfile()
                .setGroupId(ServerFuncCommonUtils.Group_Id)
                .setCode(Component_Code)
                .setVersion(Component_Version)
                .build();
    }

    @Override
    public List<IPluginProfileVo> getPluginProfile() {
        List<IPluginProfileVo> plugins = new ArrayList<>();
        plugins.add(getFunc());

        return plugins;
    }

    /**
     * GetIPAddressFunc函数
     *
     * @return GetIPAddressFunc函数描述器
     */
    private IFunctionProfileVo getFunc() {
        IFunctionBuilder pluginBuilder = RegVds.getPlugin().getFunctiontPlugin();
        IFunctionProfileVo.IFunctionOutputVo outputVo = pluginBuilder.newOutput()
                .setDesc("ip")
                .setType(VariableType.Char)
                .build();
        pluginBuilder.setAuthor(ServerFuncCommonUtils.Plugin_Author)
                .setCode(ServerFuncCommonUtils.GetIPAddressFunc.Function_Code())
                .setDesc(ServerFuncCommonUtils.GetIPAddressFunc.Function_Desc())
                .setName(ServerFuncCommonUtils.GetIPAddressFunc.Function_Name())
                .setEntry(GetIPAddressFunc.class)
                .setExample("代码示例:GetIPAddressFunc()，返回当前客户端请求的IP地址字符串，注意返回的是服务器端获取到的请求IP地址，如果使用了代理服务器，则返回代理之前的真实客户端地址。\n" +
                        "无参数；\n" +
                        "返回值为字符串。")
                .setOutput(outputVo);

        return pluginBuilder.build();
    }
}

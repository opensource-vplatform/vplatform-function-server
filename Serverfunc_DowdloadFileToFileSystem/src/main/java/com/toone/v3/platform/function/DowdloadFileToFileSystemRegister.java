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
 * @Date 2021/5/31 21:08
 */
public class DowdloadFileToFileSystemRegister implements IRegisterPlugin {

    private static final String Component_Code = "Serverfunc_DowdloadFileToFileSystem";
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
     * DowdloadFileToFileSystem函数
     *
     * @return DowdloadFileToFileSystem函数描述器
     */
    private IFunctionProfileVo getFunc() {
        IFunctionBuilder pluginBuilder = RegVds.getPlugin().getFunctiontPlugin();
        IFunctionProfileVo.IFunctionInputVo inputVo1 = pluginBuilder.newInput()
                .setDesc("网络文件路径")
                .setType(VariableType.Char)
                .setRequired(true)
                .build();
        IFunctionProfileVo.IFunctionInputVo inputVo2 = pluginBuilder.newInput()
                .setDesc("文件类型")
                .setType(VariableType.Char)
                .setRequired(false)
                .build();
        IFunctionProfileVo.IFunctionOutputVo outputVo = pluginBuilder.newOutput()
                .setDesc("文件ID")
                .setType(VariableType.Char)
                .build();
        pluginBuilder.setAuthor(ServerFuncCommonUtils.Plugin_Author)
                .setCode(ServerFuncCommonUtils.DowdloadFileToFileSystem.Function_Code())
                .setDesc(ServerFuncCommonUtils.DowdloadFileToFileSystem.Function_Desc())
                .setName(ServerFuncCommonUtils.DowdloadFileToFileSystem.Function_Name())
                .setEntry(DowdloadFileToFileSystemFunc.class)
                .setExample("代码示例：DowdloadFileToFileSystem(\"http://img07.tooopen.com/images/20170316/tooopen_sy_201956178977.jpg\",\"jpg\") \n" +
                        "参数1：网络文件路径（字符串类型）\n" +
                        "参数2：文件类型（可不传，不传可能导致无法识别文件类型）（字符串类型）\n" +
                        "返回值为字符串类型，成功时返回文件id字符串，失败时返回-1字符串。")
                .setOutput(outputVo)
                .addInputParam(inputVo1)
                .addInputParam(inputVo2);

        return pluginBuilder.build();
    }
}

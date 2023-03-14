package cn.tongdun.mobrisk.core.collectors;

import cn.tongdun.mobrisk.core.utils.EnvUtils;

/**
 * @description: Root
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class RootCollector {

    public boolean getRoot() {
        return EnvUtils.fileInEnv("su");
    }
}

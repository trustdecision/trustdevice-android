package cn.tongdun.mobrisk.providers;

import android.util.Pair;

import java.util.List;

/**
 * @description: Provider
 * @author: wuzuchang
 * @date: 2022/12/8
 */
@Deprecated(since = "pro no such class")
public abstract class InfoProvider<T> {

    public abstract String getProviderName();
    public abstract List<Pair<String, T>> getRawData();
}

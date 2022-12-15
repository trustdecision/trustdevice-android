package cn.tongdun.mobrisk.providers;

import android.util.Pair;

import java.util.List;

/**
 * @description: RawData
 * @author: wuzuchang
 * @date: 2022/12/8
 */
@Deprecated(since = "pro no such class")
public abstract class RawData<T> {
    public abstract List<Pair<String, T>> loadData();
}

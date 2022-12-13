package cn.tongdun.android.beans;

import java.util.List;

/**
 * @description: Device Module ItemBean
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DeviceModuleItemBean {

    private String moduleName;
    private String describe;
    private List<DetailsItemBean> detailsItemBeans;

    public DeviceModuleItemBean(String moduleName, List<DetailsItemBean> detailsItemBeans) {
        this.moduleName = moduleName;
        this.detailsItemBeans = detailsItemBeans;
    }

    public DeviceModuleItemBean(String moduleName, String describe, List<DetailsItemBean> detailsItemBeans) {
        this.moduleName = moduleName;
        this.describe = describe;
        this.detailsItemBeans = detailsItemBeans;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<DetailsItemBean> getDetailsItemBeans() {
        return detailsItemBeans;
    }

    public void setDetailsItemBeans(List<DetailsItemBean> detailsItemBeans) {
        this.detailsItemBeans = detailsItemBeans;
    }
}

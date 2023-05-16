package cn.tongdun.android.beans


data class DeviceModuleItemBean(
    val moduleName: String,
    val detailsItemBeans: List<DetailsItemBean>
) {
    constructor(
        moduleName: String,
        describe: String,
        detailsItemBeans: List<DetailsItemBean>
    ) : this(moduleName, detailsItemBeans)
}

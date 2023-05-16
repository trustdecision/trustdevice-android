package cn.tongdun.android.adapters

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import cn.tongdun.android.beans.AppItemData
import com.trustdevice.android.R

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/16
 */
class AppListRecyclerViewAdapter(private var _data: List<AppItemData>) : BaseRecyclerViewAdapter<AppItemData?>(_data) {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_app_list
    }

    override fun convert(holder: BaseViewHolder, data: AppItemData?, position: Int) {
        val iv_icon: ImageView? = holder.getView(R.id.iv_app_icon)
        iv_icon?.setImageDrawable(data?.icon)
        val tv_app_name: TextView? = holder.getView(R.id.tv_app_name)
        tv_app_name?.text = data?.appName?:""
        val tv_application: TextView? = holder.getView(R.id.tv_application)
        tv_application?.text = data?.packageName?:""
    }


    fun updateData(data: List<AppItemData>) {
        _data = data
        notifyDataSetChanged()
    }
}

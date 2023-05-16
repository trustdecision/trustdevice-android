package cn.tongdun.android.adapters

import android.text.TextUtils
import android.util.Pair
import android.view.View
import android.widget.TextView
import com.trustdevice.android.R


class DeviceInfoItemAdapter(data: List<Pair<String, String>>) : BaseRecyclerViewAdapter<Pair<String, String>>(data) {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_details
    }

    override fun convert(holder: BaseViewHolder, data: Pair<String, String>, position: Int) {
        val name = holder.getView<TextView>(R.id.tv_name)
        name?.text = data.first
        val value = holder.getView<TextView>(R.id.tv_value)
        if (TextUtils.isEmpty(data.second)) {
            value?.visibility = View.GONE
        } else {
            value?.text = data.second
        }
    }
}

package cn.tongdun.android.adapter;

import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.trustdevice.android.R;

import java.util.List;

/**
 * @description: BuildInfo Item Adapter
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DeviceInfoItemAdapter extends BaseRecyclerViewAdapter<Pair<String, String>> {

    public DeviceInfoItemAdapter(List<Pair<String, String>> data) {
        super(data);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_details;
    }

    @Override
    public void convert(BaseViewHolder holder, Pair<String, String> data, int position) {
        TextView name = holder.getView(R.id.tv_name);
        name.setText(data.first);
        TextView value = holder.getView(R.id.tv_value);
        if (TextUtils.isEmpty(data.second)) {
            value.setVisibility(View.GONE);
        } else {
            value.setText(data.second);
        }
    }
}

package cn.tongdun.android.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.trustdevice.android.R;
import cn.tongdun.android.beans.DetailsItemBean;

import java.util.List;

/**
 * @description: Details Item Adapter
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DetailsItemAdapter extends BaseRecyclerViewAdapter<DetailsItemBean> {

    public DetailsItemAdapter(List<DetailsItemBean> data) {
        super(data);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_details;
    }

    @Override
    public void convert(BaseViewHolder holder, DetailsItemBean data, int position) {
        TextView name = holder.getView(R.id.tv_name);
        name.setText(data.getName());
        TextView value = holder.getView(R.id.tv_value);
        if (TextUtils.isEmpty(data.getValue())) {
            value.setVisibility(View.GONE);
        } else {
            value.setText(data.getValue());
        }
    }
}

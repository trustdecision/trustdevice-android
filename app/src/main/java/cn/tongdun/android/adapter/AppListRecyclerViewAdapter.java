package cn.tongdun.android.adapter;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;

import com.trustdevice.android.R;

import java.util.List;

import cn.tongdun.android.beans.AppItemData;

/**
 * @description: app list RecyclerView Adapter
 * @author: wuzuchang
 * @date: 2023/3/8
 */
public class AppListRecyclerViewAdapter extends BaseRecyclerViewAdapter<AppItemData> {

    public AppListRecyclerViewAdapter(List<AppItemData> data) {
        super(data);
    }

    @Override
    public int getLayoutId(int viewType) {

        return R.layout.item_app_list;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void convert(BaseViewHolder holder, final AppItemData data, final int position) {

        ImageView iv_icon = holder.getView(R.id.iv_app_icon);
        iv_icon.setImageDrawable(data.getIcon());
        TextView tv_app_name = holder.getView(R.id.tv_app_name);
        tv_app_name.setText(data.getAppName());
        TextView tv_application = holder.getView(R.id.tv_application);
        tv_application.setText(data.getPackageName());
    }


    public void updateData(List<AppItemData> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
}

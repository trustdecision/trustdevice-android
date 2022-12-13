package cn.tongdun.android.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trustdevice.android.R;
import cn.tongdun.android.beans.DeviceModuleItemBean;
import cn.tongdun.android.view.DeviceDetailsItemView;

import java.util.List;

/**
 * @description: Device Module RecyclerView Adapter
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DeviceModuleItemAdapter extends RecyclerView.Adapter<DeviceModuleItemAdapter.ViewHolder> {

    private List<DeviceModuleItemBean> mData;

    public DeviceModuleItemAdapter(List<DeviceModuleItemBean> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DeviceDetailsItemView convertView = (DeviceDetailsItemView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_module, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeviceModuleItemBean deviceModuleItemBean = mData.get(position);
        if (position==0){
            holder.deviceDetailsItemView.setExpanded(true);
        }
        holder.deviceDetailsItemView.setModuleName(deviceModuleItemBean.getModuleName());
        holder.deviceDetailsItemView.setDescribe(deviceModuleItemBean.getDescribe());
        holder.deviceDetailsItemView.setDetailsContainer(deviceModuleItemBean.getDetailsItemBeans());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DeviceDetailsItemView deviceDetailsItemView;

        public ViewHolder(@NonNull DeviceDetailsItemView itemView) {
            super(itemView);
            deviceDetailsItemView = itemView;
        }
    }
}

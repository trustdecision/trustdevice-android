package cn.tongdun.android.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trustdevice.android.R;
import cn.tongdun.android.adapter.DetailsItemAdapter;
import cn.tongdun.android.beans.DetailsItemBean;

import java.util.List;

/**
 * @description: Device Details Item View
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DeviceDetailsItemView extends ConstraintLayout {

    private boolean isExpanded = false;
    private TextView tvModuleName;
    private TextView tvDescribe;
    private ImageView ivArrow;
    private RecyclerView rvDetailsContainer;

    public DeviceDetailsItemView(@NonNull Context context) {
        super(context);
    }

    public DeviceDetailsItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DeviceDetailsItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public void setModuleName(String name) {
        tvModuleName = findViewById(R.id.tv_module_name);
        tvModuleName.setText(name);
    }

    public void setDescribe(String desc) {
        tvDescribe = findViewById(R.id.tv_module_des);
        if (TextUtils.isEmpty(desc)) {
            tvDescribe.setVisibility(GONE);
        } else {
            tvDescribe.setText(desc);
        }
    }

    public void setDetailsContainer(List<DetailsItemBean> list) {
        ivArrow = findViewById(R.id.iv_arrow);
        rvDetailsContainer = findViewById(R.id.rv_details_container);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rvDetailsContainer.setLayoutManager(manager);
        DetailsItemAdapter adapter = new DetailsItemAdapter(list);
        rvDetailsContainer.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.detail_item_divider));
        rvDetailsContainer.addItemDecoration(decoration);
        if (isExpanded){
            rvDetailsContainer.setVisibility(VISIBLE);
            ivArrow.setImageResource(R.drawable.ic_up_arrow);
        }
        setOnClickListener(v -> toggleExpanding());
    }

    private void toggleExpanding() {
        if (!isExpanded) {
            rvDetailsContainer.setVisibility(VISIBLE);
            ivArrow.setImageResource(R.drawable.ic_up_arrow);
        } else {
            rvDetailsContainer.setVisibility(GONE);
            ivArrow.setImageResource(R.drawable.ic_down_arrow);
        }
        isExpanded = !isExpanded;
    }
}

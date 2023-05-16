package cn.tongdun.android.adapters

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/16
 */
abstract class BaseRecyclerViewAdapter<T>(val data: List<T>) : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder>() {


    abstract fun getLayoutId(viewType: Int): Int

    abstract fun convert(holder: BaseViewHolder, data: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder[parent, getLayoutId(viewType)]
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        convert(holder, data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class BaseViewHolder private constructor(private val mConvertView: View) :
        RecyclerView.ViewHolder(
            mConvertView
        ) {
        private val mViews: SparseArray<View> = SparseArray()

        fun <T : View> getView(id: Int): T? {
            var v = mViews[id]
            if (v == null) {
                v = mConvertView.findViewById(id)
                mViews.put(id, v)
            }
            return v as T?
        }

        fun setText(id: Int, value: String) {
            val view = getView<TextView>(id)
            view?.text = value
        }

        companion object {
            operator fun get(parent: ViewGroup, layoutId: Int): BaseViewHolder {
                val convertView =
                    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
                return BaseViewHolder(convertView)
            }
        }
    }
}

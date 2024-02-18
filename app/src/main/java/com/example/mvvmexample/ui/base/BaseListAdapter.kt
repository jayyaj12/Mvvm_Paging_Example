package com.example.mvvmexample.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import javax.annotation.Nullable


abstract class BaseListAdapter<T> :
    ListAdapter<T, BaseListAdapter<T>.BaseViewHolder>(DiffCallback<T>()) {
    init {
        registerAdapterDataObserver(ItemCountObserver())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, getLayoutResourceId(), parent, false
        )
        binding = itemBinding
        return BaseViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem: T = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener(View.OnClickListener { v: View? ->
            onItemClick(
                currentItem
            )
        })
    }

    var binding: ViewDataBinding? = null
    protected abstract fun getLayoutResourceId(): Int
    protected abstract fun getBindingVariableId(): Int

    protected abstract fun onItemClick(item: T)
    protected abstract fun onAdapterDataChanged(itemCount: Int)

    inner class BaseViewHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(obj: T) {
            binding.setVariable(getBindingVariableId(), obj)
            binding.executePendingBindings()
        }
    }

    class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }

    private inner class ItemCountObserver : AdapterDataObserver() {
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount)
            notifyAdapterDataChanged()
        }

        override fun onItemRangeChanged(
            positionStart: Int, itemCount: Int, @Nullable payload: Any?
        ) {
            super.onItemRangeChanged(positionStart, itemCount, payload)
            notifyAdapterDataChanged()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            notifyAdapterDataChanged()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            notifyAdapterDataChanged()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount)
            notifyAdapterDataChanged()
        }

        private fun notifyAdapterDataChanged() {
            onAdapterDataChanged(itemCount)
        }
    }
}
package com.crystal.android.recyclerview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.crystal.android.recyclerview.adapters.UserAdapter
import com.crystal.android.recyclerview.datas.User
import java.util.*

class ItemTouchSimpleCallback : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    0
) {


    interface OnItemMoveListener {
        fun onItemMove(from: Int, to: Int)
    }

    private var listener: OnItemMoveListener? = null

    fun setOnItemMoveListener(listener: OnItemMoveListener) {
        this.listener = listener
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {


        // 어댑터 획득
        val adapter = recyclerView.adapter as UserAdapter

        // 현재 포지션 획득
        val fromPosition = viewHolder.absoluteAdapterPosition

        // 옮길 포지션 획득
        val toPosition = target.absoluteAdapterPosition

        // adapter가 가지고 있는 현재 리스트 획득
        val list = arrayListOf<User>()

        list.addAll(adapter.differ.currentList)

        // 리스트 순서 바꿈
        Collections.swap(list, fromPosition, toPosition)

        // adapter.notifyItemMoved(fromPosition, toPosition)

        adapter.differ.submitList(list)

        // 추가적인 조치가 필요할 경우 인터페이스를 통해 해결
        listener?.onItemMove(fromPosition, toPosition)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)

        // 순서 조정 완료 후 투명도 다시 1f로 변경
        viewHolder.itemView.alpha = 1.0f
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {

        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            // 순서 변경 시 alpha를 0.5f
            viewHolder?.itemView?.alpha = 0.5f
        }
        super.onSelectedChanged(viewHolder, actionState)
    }
}
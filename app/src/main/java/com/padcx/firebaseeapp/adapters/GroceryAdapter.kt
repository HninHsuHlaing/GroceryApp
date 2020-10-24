package com.padc.grocery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.grocery.data.vos.GroceryVO
import com.padc.grocery.delegates.GroceryViewItemActionDelegate
import com.padc.grocery.viewholders.GroceryViewHolder
import com.padcx.firebaseeapp.R
import com.zg.burgerjoint.adapters.BaseRecyclerAdapter

class GroceryAdapter(private val mDelegate: GroceryViewItemActionDelegate,private val ViewType: Int) : BaseRecyclerAdapter<GroceryViewHolder, GroceryVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        if ( ViewType==0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item_grid,parent,false)
            return GroceryViewHolder(view, mDelegate)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item,parent,false)
            return GroceryViewHolder(view, mDelegate)
        }

    }
}
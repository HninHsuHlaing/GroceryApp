package com.padc.grocery.mvp.views

import com.padc.grocery.data.vos.GroceryVO

interface MainView : BaseView {
    fun showGroceryData(groceryList : List<GroceryVO>)
  //  fun showGroceryDataGrid(groceryList : List<GroceryVO>)
    fun showGroceryDialog(name: String, description: String, amount: String)
    fun showErrorMessage(message : String)
    fun showUserName(name: String)
    fun openGallery()
    fun displayToolbaeTitle(title:String)

//    fun SetUpRecyclerLayoutGrid()
//    fun SetUpRecyclerLayoutHorizontal()

    fun showViewType(viewType : Int)
}
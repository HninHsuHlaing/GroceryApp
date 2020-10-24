package com.padc.grocery.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padc.grocery.adapters.GroceryAdapter
import com.padc.grocery.data.vos.GroceryVO
import com.padc.grocery.dialogs.GroceryDialogFragment
import com.padc.grocery.dialogs.GroceryDialogFragment.Companion.BUNDLE_AMOUNT
import com.padc.grocery.dialogs.GroceryDialogFragment.Companion.BUNDLE_DESCRIPTION
import com.padc.grocery.dialogs.GroceryDialogFragment.Companion.BUNDLE_NAME
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.grocery.mvp.presenters.impls.MainPresenterImpl
import com.padc.grocery.mvp.views.MainView
import com.padcx.firebaseeapp.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : BaseActivity(), MainView {

    private var mGroceryDialogFragment: GroceryDialogFragment? = null
    private lateinit var mAdapter: GroceryAdapter
 //   private lateinit var mAdapterGrid : GroceryGridAdapter
    private lateinit var mPresenter: MainPresenter

    companion object {
        const val PICK_IMAGE_REQUEST = 1111

        fun newIntent(context: Context) : Intent{
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setUpPresenter()
        setUpRecyclerView()

        setUpActionListeners()

        mPresenter.onUiReady(this)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            val filePath = data.data
            try {

                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source =
                            ImageDecoder.createSource(this.contentResolver, filePath)

                        val bitmap = ImageDecoder.decodeBitmap(source)
                        mPresenter.onPhotoTaken(bitmap)
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            applicationContext.contentResolver, filePath
                        )
                        mPresenter.onPhotoTaken(bitmap)
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    private fun setUpActionListeners() {
        fab.setOnClickListener {
            GroceryDialogFragment.newFragment()
                .show(supportFragmentManager, GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG)
        }
    }

    private fun setUpRecyclerView() {
      //  mAdapter = GroceryAdapter(mPresenter)
//        rvGroceries.adapter = mAdapter

//        rvGroceries.layoutManager = GridLayoutManager(this,2)
//        rvGroceries.layoutManager =
//            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
          //  LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showGroceryData(groceryList: List<GroceryVO>) {
        mAdapter.setNewData(groceryList)
      //  mAdapterGrid.setNewData(groceryList)
    }

//    override fun showGroceryDataGrid(groceryList: List<GroceryVO>) {
//        //mAdapterGrid.setNewData(groceryList)
////    }

    override fun showGroceryDialog(name: String, description: String, amount: String) {
        mGroceryDialogFragment = GroceryDialogFragment.newFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_NAME, name)
        bundle.putString(BUNDLE_DESCRIPTION, description)
        bundle.putString(BUNDLE_AMOUNT, amount)
        mGroceryDialogFragment?.arguments = bundle
        mGroceryDialogFragment?.show(
            supportFragmentManager,
            GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG
        )
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG)
    }

    override fun showUserName(name: String) {
        txtusername.setText("Hello " + name)
    }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun displayToolbaeTitle(title: String) {
        supportActionBar?.title = title
    }

//    override fun SetUpRecyclerLayoutGrid() {
////        mAdapterGrid = GroceryGridAdapter(mPresenter)
////        rvGroceries.adapter = mAdapterGrid
////        rvGroceries.layoutManager = GridLayoutManager(this,2)
//    }
//
//    override fun SetUpRecyclerLayoutHorizontal() {
//     //   mAdapter = GroceryAdapter(mPresenter)
////        rvGroceries.adapter = mAdapter
////        rvGroceries.layoutManager =
////            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//    }

    override fun showViewType(viewType: Int) {
        mAdapter = GroceryAdapter(mPresenter,viewType)
        rvGroceries.adapter = mAdapter
        if(viewType == 0){
            rvGroceries.layoutManager = GridLayoutManager(this,2)
        }else{
            rvGroceries.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }
}
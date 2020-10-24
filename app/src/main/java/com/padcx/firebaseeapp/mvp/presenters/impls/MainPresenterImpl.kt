package com.padc.grocery.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.padc.grocery.data.models.GroceryModelImpl
import com.padc.grocery.data.vos.GroceryVO
import com.padc.grocery.mvp.presenters.AbstractBasePresenter
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.grocery.mvp.views.MainView
import com.padcx.firebaseeapp.data.models.AuthenticationModel
import com.padcx.firebaseeapp.data.models.AuthenticationModelImpl

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mGroceryModel = GroceryModelImpl
    private var mChosenGroceryForFileUpload: GroceryVO? = null
    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

//    override fun onTapAddGrocery(name: String, description: String, amount: Int) {
//        mGroceryModel.addGrocery(name, description, amount, "")
//    }
    override fun onTapAddGrocery(groceryVO: GroceryVO, bitmap: Bitmap) {
         mGroceryModel.uploadImageAndUpdateGrocery(groceryVO,bitmap)
    }

    override fun onPhotoTaken(bitmap: Bitmap) {
        mChosenGroceryForFileUpload?.let {
            mGroceryModel.uploadImageAndUpdateGrocery(it, bitmap)
        }
    }

    override fun onTapEditGrocery(name: String, description: String, amount: Int) {
        mView.showGroceryDialog(name, description, amount.toString())
    }

    override fun onTapFileUpload(grocery: GroceryVO) {
        mChosenGroceryForFileUpload = grocery
        mView.openGallery();
    }

    override fun onUiReady(owner: LifecycleOwner) {
        val layoutvalue = mGroceryModel.getRecyclerViewLayoutValue()
        mView.showViewType(layoutvalue)

        val name =mAuthenticatioModel.getUserName()
      //  Log.d("UserName ", name)
        mView.showUserName(name)




        mGroceryModel.getGroceries(
            onSuccess = {
                mView.showGroceryData(it)


            },
            onFaiure = {
                mView.showErrorMessage(it)
            }
        )
        mView.displayToolbaeTitle(mGroceryModel.getAppNameFromRemoteComfig())


    }


    override fun onTapDeleteGrocery(name: String) {
        mGroceryModel.removeGrocery(name)
    }
}
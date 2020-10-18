package com.padc.grocery.data.models

import android.graphics.Bitmap
import android.util.Log
import com.padc.grocery.data.vos.GroceryVO
import com.padc.grocery.network.FirebaseApi
import com.padcx.firebaseeapp.network.CloudFirestoreFirebaseApiImpl
import com.padcx.firebaseeapp.network.RealtimeDatabaseFirebaseApiImpl
import com.padcx.firebaseeapp.network.remoteConfig.FirebaseRemoteConfigManager

object GroceryModelImpl : GroceryModel {
    //override var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl

    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager = FirebaseRemoteConfigManager

    override fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getGroceries(onSuccess, onFaiure)
    }

    override fun addGrocery(name: String, description: String, amount: Int, image: String) {
        mFirebaseApi.addGrocery(name, description, amount, image)
    }

    override fun removeGrocery(name: String) {
        mFirebaseApi.deleteGrocery(name)
    }

    override fun uploadImageAndUpdateGrocery(grocery: GroceryVO, image: Bitmap) {
        mFirebaseApi.uploadImageAndEditGrocery(image, grocery)
    }

    override fun setRemoteConfigWithDefaultValues() {
        mFirebaseRemoteConfigManager.setUpRemoteConfigWithDefaultValues()
    }

    override fun fetchRemoteConfig() {
        mFirebaseRemoteConfigManager.fetchRemoteConfigs()
    }

    override fun getAppNameFromRemoteComfig(): String {
//        val title = mFirebaseRemoteConfigManager.getToolbarName()
//        Log.d("Firebase Title",title)
        return mFirebaseRemoteConfigManager.getToolbarName()
    }

    override fun setRemoteConfigValueForRecyclerView() {
        mFirebaseRemoteConfigManager.setUpRecyclerLayoutValue()
    }

    override fun fetchRemoteConfigForRecyclerView() {
        mFirebaseRemoteConfigManager.fetchRemoteConfigForRecyclerValue()
    }

    override fun getRecyclerViewLayoutValue(): String {
        return  mFirebaseRemoteConfigManager.getRecyclerViewLayout()
    }


}
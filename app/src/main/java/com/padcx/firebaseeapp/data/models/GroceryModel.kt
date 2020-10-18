package com.padc.grocery.data.models

import android.graphics.Bitmap
import com.padc.grocery.data.vos.GroceryVO
import com.padc.grocery.network.FirebaseApi
import com.padcx.firebaseeapp.network.remoteConfig.FirebaseRemoteConfigManager

interface GroceryModel {


    var mFirebaseApi : FirebaseApi
    var mFirebaseRemoteConfigManager :FirebaseRemoteConfigManager

    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addGrocery(name: String ,description : String, amount: Int, image: String)

    fun removeGrocery(name: String)

    fun uploadImageAndUpdateGrocery(grocery : GroceryVO, image : Bitmap)

    fun setRemoteConfigWithDefaultValues()

    fun fetchRemoteConfig()

    fun getAppNameFromRemoteComfig() : String


    fun setRemoteConfigValueForRecyclerView()

    fun fetchRemoteConfigForRecyclerView()

    fun getRecyclerViewLayoutValue() :String
}
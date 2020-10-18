package com.padcx.firebaseeapp.network.remoteConfig

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

/**
 * Created by Hnin Hsu Hlaing
 * on 10/18/2020
 */
object FirebaseRemoteConfigManager {

    private val remoteConfig = Firebase.remoteConfig

    init {

        val configSettings = remoteConfigSettings {

            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }
    fun setUpRemoteConfigWithDefaultValues(){
        val defaultValue : Map<String,String> = hashMapOf(
            "mainScreenAppBarTitle" to "Grocery-app"
        )
        remoteConfig.setDefaultsAsync(defaultValue)

    }
    fun setUpRecyclerLayoutValue(){
        val recyclerValue :Map<String,Int> = hashMapOf(
            "recyclerLayout" to 1
        )
    }
    fun fetchRemoteConfigs(){
        remoteConfig.fetch()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Log.d("Firebase","Firebase Remote Config fetch Success")
                    remoteConfig.activate().addOnCompleteListener {
                        Log.d("Firebase","Firebase Remote Config Activated")
                    }
                }else{
                    Log.d("Firebasee ", "Firebase Remote Config fetch Failed")
                }
            }
    }

    fun fetchRemoteConfigForRecyclerValue(){
        remoteConfig.fetch()
            .addOnCompleteListener {task ->
                if (task.isSuccessful){
                    Log.d("Firebase","Firebase Remote Config fetch Success")
                    remoteConfig.activate().addOnCompleteListener {
                        Log.d("Firebase","Firebase Remote Config Activated")
                    }
                }else{
                    Log.d("Firebasee ", "Firebase Remote Config fetch Failed")
                }
            }
    }


    fun getToolbarName(): String{

        return  remoteConfig.getValue("mainScreenAppBarTitle").asString()
    }

    fun getRecyclerViewLayout() :String{
        return  remoteConfig.getValue("recyclerLayout").asString()
    }
}
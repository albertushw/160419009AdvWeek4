package com.ubaya.a160419009_advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.a160419009_advweek4.model.Student

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue:RequestQueue ?= null
    fun fetch(iduser:String){

        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php?id=$iduser"

        loadingErrorLD.value = false
        loadingLD.value = true

        val stringRequest = StringRequest(
            Request.Method.GET ,url,
            {response ->
                val result = Gson().fromJson<Student>(response,Student::class.java)
                studentLD.value = result
                loadingLD.value = false
                Log.d("showvolley", response.toString())
            },
            {
                loadingLD.value = false
                loadingErrorLD.value = true
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}
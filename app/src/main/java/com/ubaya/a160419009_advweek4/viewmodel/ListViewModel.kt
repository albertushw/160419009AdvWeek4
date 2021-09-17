package com.ubaya.a160419009_advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419009_advweek4.model.Student
import com.ubaya.a160419009_advweek4.view.StudentListAdapter

class ListViewModel(application: Application):AndroidViewModel(application) {


    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue:RequestQueue ?= null

    fun refresh(){
//        //untuk loading data
//        val student1 = Student("29-3239773","Geoffrey","2015/10/16","1229868667","http://dummyimage.com/100x75.png/5fa2dd/ffffff")
//        val student2 = Student("48-2255582","Peyter","1999/01/26","1937359692","http://dummyimage.com/100x75.png/dddddd/000000")
//        val student3 = Student("32-6287437","Orsola","2009/03/19","1376841968","http://dummyimage.com/100x75.png/cc0000/ffffff")
//
//        studentsLD.value = arrayListOf<Student>(student1, student2, student3)
//        loadingErrorLD.value = false
//        loadingDoneLD.value = true
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(Request.Method.GET, url,
            {response ->
                val sType = object : TypeToken<List<Student>>() { }.type
                val result = Gson().fromJson<List<Student>>(response, sType)
                studentsLD.value = result
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

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG) //UNTUK MENGHAPUS ONGOING STRING REQUEST
    }
}
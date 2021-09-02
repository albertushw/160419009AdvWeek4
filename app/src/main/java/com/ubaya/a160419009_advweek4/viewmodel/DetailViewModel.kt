package com.ubaya.a160419009_advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.a160419009_advweek4.model.Student

class DetailViewModel:ViewModel() {
    val studentLD = MutableLiveData<Student>()

    fun fetch(){
        val student1 = Student("35-3235783","Ben","2008/12/11","8729468467","http://dummyimage.com/100x75.png/5fa2dd/00ffff")
        studentLD.value = student1
    }
}
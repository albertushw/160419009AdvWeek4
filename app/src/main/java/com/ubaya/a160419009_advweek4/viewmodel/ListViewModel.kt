package com.ubaya.a160419009_advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.a160419009_advweek4.model.Student
import com.ubaya.a160419009_advweek4.view.StudentListAdapter

class ListViewModel:ViewModel() {


    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()
    
    fun refresh(){
        //untuk loading data
        val student1 = Student("29-3239773","Geoffrey","2015/10/16","1229868667","http://dummyimage.com/100x75.png/5fa2dd/ffffff")
        val student2 = Student("48-2255582","Peyter","1999/01/26","1937359692","http://dummyimage.com/100x75.png/dddddd/000000")
        val student3 = Student("32-6287437","Orsola","2009/03/19","1376841968","http://dummyimage.com/100x75.png/cc0000/ffffff")

        studentsLD.value = arrayListOf<Student>(student1, student2, student3)
        loadingErrorLD.value = false
        loadingDoneLD.value = true

    }
}
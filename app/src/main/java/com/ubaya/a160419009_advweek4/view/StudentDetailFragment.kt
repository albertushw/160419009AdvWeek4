package com.ubaya.a160419009_advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419009_advweek4.R
import com.ubaya.a160419009_advweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.a160419009_advweek4.databinding.StudentListItemBinding
import com.ubaya.a160419009_advweek4.model.Student
import com.ubaya.a160419009_advweek4.util.loadImage
import com.ubaya.a160419009_advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener, ButtonCreateNotificationListener{
    private lateinit var dataBinding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var iduser = ""
        if(arguments!=null){
            iduser = StudentDetailFragmentArgs.fromBundle(requireArguments()).iduser
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(iduser)

        dataBinding.listenerUpdate = this
        dataBinding.listenerNotif = this

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.studentDetail = it
//            txtId.setText(it.id)
//            txtName.setText(it.name)
//            txtBod.setText(it.bod)
//            txtPhone.setText(it.phone)
//            imageView2.loadImage(it.photoUrl.toString(),progressBar2)

//            var student = it
//            btnNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new Notification created",
//                            R.drawable.ic_baseline_person_24)
//                    }
//            }
        })
    }

    override fun onButtonCreateNotificationListener(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                MainActivity.showNotification(v.tag.toString(),
                    "A new Notification created",
                    R.drawable.ic_baseline_person_24)
            }
    }

    override fun onButtonUpdateClickListerner(v: View) {
        val action = StudentDetailFragmentDirections.actionStudentList()
        Navigation.findNavController(v).navigate(action)
    }
}

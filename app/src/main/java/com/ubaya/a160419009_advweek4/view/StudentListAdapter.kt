package com.ubaya.a160419009_advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419009_advweek4.R
import com.ubaya.a160419009_advweek4.model.Student
import com.ubaya.a160419009_advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){
    class StudentViewHolder(val view: View):RecyclerView.ViewHolder(view)

    fun updateStudentList(newStudentList:List<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        //untuk meload layout pada setiap item pada recycler view
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.student_list_item, parent, false)

        return StudentViewHolder(v)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        //Untuk meload data yang sesuai dari arraylist studentnya dan render ke layoutnya
        holder.view.txtId.text = studentList[position].id
        holder.view.txtName.text = studentList[position].name
        holder.view.imageView.loadImage(studentList[position].photoUrl.toString(), holder.view.progressBar)

        holder.view.btnDetail.setOnClickListener {
            val userid = holder.view.txtId.text.toString()
            val action = StudentListFragmentDirections.actionStudentDetail(userid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}
package com.ubaya.a160419009_advweek4.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160419009_advweek4.R
import java.lang.Exception

fun ImageView.loadImage(url:String, progressBar: ProgressBar){
    Picasso.get()
        .load(url) //meload semua url yg diparsingkan di parameter functions
        .resize(400,400) //mbesarin fotonya
        .centerCrop() //besarin dan center ke tengah
        .error(R.drawable.ic_baseline_error_24) //kalo error tampilin icon error
        .into(this, object: Callback{
            override fun onSuccess() { //kalo sukses menyembunyikan progress bar
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }

        })
}
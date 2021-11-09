package com.ubaya.a160419009_advweek4.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.QuickContactBadge
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160419009_advweek4.R
import java.lang.Exception
import java.security.AccessControlContext

fun createNotificationChannel(context: Context, importance:Int, showBadge: Boolean, name: String, description: String){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description=description
        channel.setShowBadge(showBadge)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

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

@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoUrl(v:ImageView, url:String, pb:ProgressBar){
    v.loadImage(url,pb)
}
package com.ubaya.a160419009_advweek4.view

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AndroidException
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ubaya.a160419009_advweek4.R
import com.ubaya.a160419009_advweek4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    init{ //suatu block program yg dijalankan waktu constructor diciptakan
        instance = this
    }
    companion object{ //fungsi static
        private var instance:MainActivity ?= null
        fun showNotification(title: String, content:String, icon:Int){
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                //akan menjalankan fungsi2 punya builder didalem apply
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val notif = NotificationManagerCompat.from(instance!!.applicationContext)
            notif.notify(1001, builder.build())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
            true, getString(R.string.app_name), "App Channel")
//        var observable = Observable.just("hellow", "world", "!!") //just untuk mengeluarkan data
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {Log.d("Messages","data captured: $it")},
//                {Log.d("Messages", "error ${it.message.toString()}")},
//                {Log.d("Messages","Complete")},
//            )
//        val observer = object: Observer<String>{
//            override fun onSubscribe(d: Disposable?) {
//                Log.d("Messages","Start Subscribe")
//            }
//
//            override fun onNext(t: String?) { //jika ada data baru yang dimunculkan observable (menangkap data)
//                Log.d("Messages","data captured: ${t.toString()}")
//            }
//
//            override fun onError(e: Throwable?) { //Yang dipanggil kalo ada error / kesalahan
//                Log.d("Messages","error : ${e!!.message.toString()}")
//            }
//
//            override fun onComplete() { //si observer nya sdh gak memancarkan data lagi / finish
//                Log.d("Messages","Completed")
//            }
//        }

//        observable
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(observer)
    }
}
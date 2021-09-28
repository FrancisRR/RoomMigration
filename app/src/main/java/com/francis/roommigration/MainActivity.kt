package com.francis.roommigration

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import butterknife.ButterKnife
import butterknife.OnClick
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@SuppressLint("CheckResult")
class MainActivity : AppCompatActivity() {


    private var roomDb: RoomDatabaseEx? = null
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        roomDb = AppController.getAppController().getDb()
        syncData()

        varAss("1", "2", "3")
    }

    @OnClick(R.id.btNext, R.id.btDelete)
    public fun clickEvent(v: View) {
        when (v.id) {
            R.id.btNext -> insert()
            R.id.btDelete -> delete()
        }
    }


    private fun insert() {
        val name = editText.text.toString()
        val number = editText2.text.toString()

        if (name.equals("")) {
            showToast("Enter name")
            return
        } else if (number.equals("")) {
            showToast("Enter number")
            return
        }

        val subModelList = mutableListOf<SubModel>()
        subModelList.add(SubModel(name, number))

        val childModel =ChildModel(name,number)
        val obj = ContactModel(name, number, 24, true, false, subModelList,childModel)
        GlobalScope.async {
            roomDb?.getDao()?.insert(obj)

        }


    }


    internal fun delete() {
        val name = editText.text.toString()
        Completable.defer { roomDb?.getDao()?.deleteId(name) }.subscribeOn(Schedulers.io())
            .subscribe {
                Log.e(TAG, "contact deleted")
            }

//        if (!TextUtils.isEmpty(name)) {
//            val job = GlobalScope.async {
//                roomDb?.getDao()?.deleteId(name)
//                    ?.subscribeOn(Schedulers.io())?.subscribe {
//                        Log.e(TAG, "contact deleted")
//                    }
//            }
//        }
    }


    fun varAss(vararg data: String?) {
        data.forEach { Log.e(TAG, "${it}") }
    }

    private fun syncData() {
        val obj1: LiveData<MutableList<ContactModel>>? = roomDb?.getDao()?.getAll()
        obj1?.observe(this, Observer { data: MutableList<ContactModel> ->
            Log.e(TAG, "${data.toString()} : ${data.size}")
            notifyData(data)
        })


    }

    private fun notifyData(list: MutableList<ContactModel>?) {
        var stringBuilder = StringBuilder()
        list?.forEach { data ->
            stringBuilder.append("Name :${data.name}\nNumber :${data.number}\nAge :${data.age}")
            stringBuilder.append("\n\n")
            Log.e(TAG, "ddd" + data.isNew)
        }
        tvList.setText(stringBuilder.toString())
    }


    private fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}

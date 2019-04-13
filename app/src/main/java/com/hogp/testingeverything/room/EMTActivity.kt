package com.hogp.testingeverything.room

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hogp.testingeverything.R
import com.hogp.testingeverything.room.database.UserDatabase
import com.hogp.testingeverything.room.database.UserRepository
import com.hogp.testingeverything.room.model.User
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_emt.*
import java.util.*

class EMTActivity : AppCompatActivity() {
    lateinit var adapter: ArrayAdapter<*>
    var userList: MutableList<User> = ArrayList()

    private var compositeDisposable: CompositeDisposable? = null
    private var userRepository: UserRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emt)


        compositeDisposable = CompositeDisposable()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList)
        registerForContextMenu(listUsers)
        listUsers!!.adapter = adapter

        val userDatabase = UserDatabase.getInstance(this)
        userRepository = UserRepository.getInstance(UserDataSource.getInstance(userDatabase.userDao()))
        loadData()
        fbAdd.setOnClickListener {
            val disposable = Observable.create(ObservableOnSubscribe<Any> { e ->
                val user = User()
                user.name = "EDMT"
                user.email = UUID.randomUUID().toString() + "@gmail.com"
                userRepository!!.insertUser(user)
                e.onComplete()
            })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(Consumer {

                    }, Consumer { throwable ->
                        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
                    }, Action {
                        loadData()
                    })
            compositeDisposable!!.addAll(disposable)
        }
    }

    private fun loadData() {
        val disposable = userRepository!!.allUsers
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users ->
                    onGetAllUsers(users)
                }, { t: Throwable? ->
                    Toast.makeText(this, t!!.message, Toast.LENGTH_SHORT).show()
                })
        compositeDisposable!!.add(disposable)
    }

    private fun onGetAllUsers(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rrom_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.clear -> deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {

        val disposable = Observable.create(ObservableOnSubscribe<Any> { e ->
            userRepository!!.deleteAllUsers()
            e.onComplete()
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(Consumer {

                }, Consumer { throwable ->
                    Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
                }, Action {
                    loadData()
                })
        compositeDisposable!!.addAll(disposable)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        menu?.setHeaderTitle("Select Action :")

        menu?.add(Menu.NONE, 0, Menu.NONE, "UPDATE")
        menu?.add(Menu.NONE, 1, Menu.NONE, "DELETE")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val info = item?.menuInfo as AdapterView.AdapterContextMenuInfo

        val user = userList[info.position]
        when (item.itemId) {
            0 -> {
                val edtName = EditText(this@EMTActivity)
                edtName.setText(user.name.toString())
                edtName.hint = "Enter your name"
                AlertDialog.Builder(this@EMTActivity)
                        .setTitle("Edit")
                        .setMessage("Edit user name")
                        .setView(edtName)
                        .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which ->
                            if (TextUtils.isEmpty(edtName.text.toString()))
                                return@OnClickListener
                            else {
                                user.name = edtName.text.toString()
                                updateUser(user)
                            }
                        }).setNegativeButton(android.R.string.cancel) { dialog, which ->
                            dialog.dismiss()
                        }.create().show()
            }
            1 ->
                AlertDialog.Builder(this@EMTActivity)
                        .setMessage("delete " + user.name)
                        .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which ->
                            deleteUser(user)
                        }).setNegativeButton(android.R.string.cancel) { dialog, which ->
                            dialog.dismiss()
                        }.create().show()

        }
        return true
    }

    private fun deleteUser(user: User) {
        val disposable = Observable.create(ObservableOnSubscribe<Any> { e ->
            userRepository!!.deleteUser(user)
            e.onComplete()
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(Consumer {

                }, Consumer { throwable ->
                    Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
                }, Action {
                    loadData()
                })
        compositeDisposable!!.addAll(disposable)

    }

    private fun updateUser(user: User) {
        val disposable = Observable.create(ObservableOnSubscribe<Any> { e ->
            userRepository!!.updateUser(user)
            e.onComplete()
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(Consumer {

                }, Consumer { throwable ->
                    Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
                }, Action {
                    loadData()
                })
        compositeDisposable!!.addAll(disposable)
    }

}

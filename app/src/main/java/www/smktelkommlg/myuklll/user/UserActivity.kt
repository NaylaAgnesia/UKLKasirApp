package www.smktelkommlg.myuklll.user

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.floatingactionbutton.FloatingActionButton
import www.smktelkommlg.myuklll.AppDatabase
import www.smktelkommlg.myuklll.MainActivity
import www.smktelkommlg.myuklll.R

class UserActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddUser: FloatingActionButton
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        recyclerView = findViewById(R.id.listUser)
        fabAddUser = findViewById(R.id.fabAddUser)
        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{
            override fun onClick(positon: Int) {
                val dialog = AlertDialog.Builder(this@UserActivity)
                dialog.setTitle(list[positon].username)
                dialog.setItems(R.array.popup,DialogInterface.OnClickListener({dialog, which ->
                    if (which == 0){
                        val intent = Intent(this@UserActivity, UserEditActivity::class.java)
                        intent.putExtra("id", list[positon].userId)
                        startActivity(intent)
                    } else if(which == 1){
                        database.userDao().hapusUser(list[positon])
                        getData()
                    } else {
                        dialog.dismiss()
                    }
                }))
                val dialogView = dialog.create()
                dialogView.show()
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fabAddUser.setOnClickListener{
            startActivity(Intent(this, UserEditActivity::class.java))
        }
        btnBack = findViewById(R.id.btn_kembali)
        btnBack.setOnClickListener {startActivity(Intent(this, MainActivity::class.java))}
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.userDao().getAll())
        adapter.notifyDataSetChanged()
    }
}
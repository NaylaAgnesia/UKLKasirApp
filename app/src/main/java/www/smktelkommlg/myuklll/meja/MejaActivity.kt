package www.smktelkommlg.myuklll.meja

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import www.smktelkommlg.myuklll.AppDatabase
import www.smktelkommlg.myuklll.MainActivity
import www.smktelkommlg.myuklll.R
import www.smktelkommlg.myuklll.user.UserAdapter
import www.smktelkommlg.myuklll.user.UserEditActivity

class MejaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddMeja: FloatingActionButton
    private var list = mutableListOf<Meja>()
    private lateinit var adapter: MejaAdapter
    private lateinit var database: AppDatabase
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meja)
        recyclerView = findViewById(R.id.listMeja)
        fabAddMeja = findViewById(R.id.fabAddMeja)
        database = AppDatabase.getInstance(applicationContext)
        adapter = MejaAdapter(list)
        adapter.setDialog(object : MejaAdapter.Dialog{
            override fun onClick(positon: Int) {
                val dialog = AlertDialog.Builder(this@MejaActivity)
                dialog.setTitle(list[positon].namaMeja)
                dialog.setItems(R.array.popup, DialogInterface.OnClickListener({ dialog, which ->
                    if (which == 0){
                        val intent = Intent(this@MejaActivity, MejaEditActivity::class.java)
                        intent.putExtra("id", list[positon].mejaId)
                        startActivity(intent)
                    } else if(which == 1){
                        database.mejaDao().hapusMeja(list[positon])
                        getDataMeja()
                    } else {
                        dialog.dismiss()
                    }
                }))
                val dialogView = dialog.create()
                dialogView.show()
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, RecyclerView.VERTICAL))

        fabAddMeja.setOnClickListener{
            startActivity(Intent(this, MejaEditActivity::class.java))
        }
        btnBack = findViewById(R.id.btn_backmain)
        btnBack.setOnClickListener {startActivity(Intent(this, MainActivity::class.java))}
    }
    override fun onResume() {
        super.onResume()
        getDataMeja()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getDataMeja(){
        list.clear()
        list.addAll(database.mejaDao().getMeja())
        adapter.notifyDataSetChanged()
    }
}

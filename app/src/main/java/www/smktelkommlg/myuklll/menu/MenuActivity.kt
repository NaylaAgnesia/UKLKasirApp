package www.smktelkommlg.myuklll.menu

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import www.smktelkommlg.myuklll.AppDatabase
import www.smktelkommlg.myuklll.MainActivity
import www.smktelkommlg.myuklll.R
import www.smktelkommlg.myuklll.menu.makanan.Makanan
import www.smktelkommlg.myuklll.menu.makanan.MakananAdapter
import www.smktelkommlg.myuklll.menu.makanan.MakananEditActivity
import www.smktelkommlg.myuklll.menu.minuman.Minuman
import www.smktelkommlg.myuklll.menu.minuman.MinumanAdapter
import www.smktelkommlg.myuklll.menu.minuman.MinumanEditActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var recyclerViewMakanan: RecyclerView
    private lateinit var recyclerViewMinuman: RecyclerView
    private lateinit var btnAddMakanan: Button
    private lateinit var btnAddMinuman: Button
    private var listMakanan = mutableListOf<Makanan>()
    private var listMinuman = mutableListOf<Minuman>()
    private lateinit var adapterMakanan: MakananAdapter
    private lateinit var adapterMinuman: MinumanAdapter
    private lateinit var database: AppDatabase
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        recyclerViewMakanan = findViewById(R.id.recycler_view_makanan)
        recyclerViewMinuman = findViewById(R.id.recycler_view_minuman)
        btnAddMakanan = findViewById(R.id.btnAddMakanan)
        btnAddMinuman = findViewById(R.id.btnAddMinuman)
        database = AppDatabase.getInstance(applicationContext)

        adapterMakanan = MakananAdapter(listMakanan)
        adapterMakanan.setDialog(object  : MakananAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MenuActivity)
                dialog.setTitle(listMakanan[position].namaMakanan)
                dialog.setItems(R.array.popup, DialogInterface.OnClickListener{ dialog, which ->
                    if (which == 0){
                        val intent= Intent(this@MenuActivity, MakananEditActivity::class.java)
                        intent.putExtra("id", listMakanan[position].menuId)
                        startActivity(intent)
                    } else if (which == 1){
                        database.menuDao().hapusMkn(listMakanan[position])
                        getDataMakanan()
                    } else{
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }
        })

        adapterMinuman = MinumanAdapter(listMinuman)
        adapterMinuman.setDialog(object : MinumanAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MenuActivity)
                dialog.setTitle(listMinuman[position].namaMinuman)
                dialog.setItems(R.array.popup, DialogInterface.OnClickListener{ dialog, which ->
                    if (which == 0){
                        val intent = Intent(this@MenuActivity, MinumanEditActivity::class.java)
                        intent.putExtra("id", listMinuman[position].menuId)
                        startActivity(intent)
                    } else if (which == 1){
                        database.menuDao().hapusMinum(listMinuman[position])
                        getDataMinuman()
                    } else {
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }
        })

        recyclerViewMakanan.adapter = adapterMakanan
        recyclerViewMakanan.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerViewMakanan.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        recyclerViewMinuman.adapter = adapterMinuman
        recyclerViewMinuman.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerViewMinuman.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        btnAddMakanan.setOnClickListener{
            startActivity(Intent(this, MakananEditActivity::class.java))
        }
        btnAddMinuman.setOnClickListener{
            startActivity(Intent(this, MinumanEditActivity::class.java))
        }
        btnBack = findViewById(R.id.btn_backmenu)
        btnBack.setOnClickListener {startActivity(Intent(this, MainActivity::class.java))}
    }

    override fun onResume() {
        super.onResume()
        getDataMakanan()
        getDataMinuman()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getDataMakanan() {
        listMakanan.clear()
        listMakanan.addAll(database.menuDao().getMkn())
        adapterMakanan.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getDataMinuman(){
        listMinuman.clear()
        listMinuman.addAll(database.menuDao().getMinum())
        adapterMinuman.notifyDataSetChanged()
    }
}
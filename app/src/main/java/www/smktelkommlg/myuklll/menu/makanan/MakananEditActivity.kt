package www.smktelkommlg.myuklll.menu.makanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import www.smktelkommlg.myuklll.AppDatabase
import www.smktelkommlg.myuklll.R

class MakananEditActivity : AppCompatActivity() {
    private lateinit var namaMakanan: EditText
    private lateinit var deskripsi: EditText
    private lateinit var harga: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.makanan_edit_activity)

        namaMakanan = findViewById(R.id.edtNamaMakanan)
        deskripsi = findViewById(R.id.edtDeskripsi)
        harga = findViewById(R.id.edtHarga)
        btnSave = findViewById(R.id.btnSave)
        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if (intent != null){
            var id = intent.getInt("id", 0)
            var makanan = database.menuDao().ambilSemuaMkn(id)

            namaMakanan.setText(makanan.namaMakanan)
            deskripsi.setText(makanan.deskripsi)
            harga.setText(makanan.harga)
        }

        btnSave.setOnClickListener{
            if (namaMakanan.text.isNotEmpty() && deskripsi.text.isNotEmpty() && harga.text.isNotEmpty()) {
                if (intent != null){
                    database.menuDao().ubahMkn(Makanan(
                        intent.getInt("id", 0),
                        namaMakanan.text.toString(),
                        deskripsi.text.toString(),
                        harga.text.toString()
                    ))
                } else {
                    database.menuDao().tambahMkn(Makanan(
                        null,
                        namaMakanan.text.toString(),
                        deskripsi.text.toString(),
                        harga.text.toString()
                    ))
                }
                finish()
            } else{
                Toast.makeText(applicationContext, "Isi dengan benar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
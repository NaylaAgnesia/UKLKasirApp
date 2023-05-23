package www.smktelkommlg.myuklll.menu.minuman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import www.smktelkommlg.myuklll.AppDatabase
import www.smktelkommlg.myuklll.R

class MinumanEditActivity : AppCompatActivity() {
    private lateinit var namaMinuman: EditText
    private lateinit var deskripsi: EditText
    private lateinit var harga: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.minuman_edit_activity)

        namaMinuman = findViewById(R.id.edtNamaMinuman)
        deskripsi = findViewById(R.id.edtDeskripsi)
        harga = findViewById(R.id.edtHarga)
        btnSave = findViewById(R.id.btnSave)
        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if (intent != null) {
            var id = intent.getInt("id", 0)
            var minuman = database.menuDao().ambilSemuaMinum(id)

            namaMinuman.setText(minuman.namaMinuman)
            deskripsi.setText(minuman.deskripsi)
            harga.setText(minuman.harga)
        }

        btnSave.setOnClickListener {
            if (namaMinuman.text.isNotEmpty() && deskripsi.text.isNotEmpty() && harga.text.isNotEmpty()) {
                if (intent != null) {
                    database.menuDao().ubahMinum(
                        Minuman(
                            intent.getInt("id", 0),
                            namaMinuman.text.toString(),
                            deskripsi.text.toString(),
                            harga.text.toString()
                        )
                    )
                } else {
                    database.menuDao().tambahMinum(
                        Minuman(
                            null,
                            namaMinuman.text.toString(),
                            deskripsi.text.toString(),
                            harga.text.toString()
                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Isi dengan benar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
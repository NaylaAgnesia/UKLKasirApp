package www.smktelkommlg.myuklll.meja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import www.smktelkommlg.myuklll.AppDatabase
import www.smktelkommlg.myuklll.R
import www.smktelkommlg.myuklll.menu.makanan.Makanan
import www.smktelkommlg.myuklll.user.User

class MejaEditActivity : AppCompatActivity() {
    private lateinit var namaMeja: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meja_edit)

        namaMeja = findViewById(R.id.edtNamaMeja)
        btnSave = findViewById(R.id.btnSave)
        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if (intent != null) {
            var id = intent.getInt("id", 0)
            var meja = database.mejaDao().ambilSemuaMeja(id)

            namaMeja.setText(meja.namaMeja)
        }
        btnSave.setOnClickListener {
            if (namaMeja.text.isNotEmpty()) {
                if (intent != null) {
                    database.mejaDao().ubahMeja(
                        Meja(
                            intent.getInt("id", 0),
                            namaMeja.text.toString(),
                        )
                    )
                } else {
                    database.mejaDao().tambahMeja(
                        Meja(
                            null,
                            namaMeja.text.toString(),
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
package www.smktelkommlg.myuklll.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import www.smktelkommlg.myuklll.AppDatabase
import www.smktelkommlg.myuklll.R

class UserEditActivity : AppCompatActivity() {
    private  lateinit var namaUser: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var spinner: Spinner
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase
    private var jobInput = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit)

        namaUser = findViewById(R.id.edtNamaUser)
        username = findViewById(R.id.edtUsername)
        password = findViewById(R.id.edtPassword)
        spinner = findViewById(R.id.spinnerJobdesk)
        btnSave = findViewById(R.id.btnSave)
        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if(intent != null){
            var id = intent.getInt("id", 0)
            var user = database.userDao().ambilSemuaUser(id)

            namaUser.setText(user.namaUser)
            username.setText(user.username)
            password.setText(user.password)

            ArrayAdapter.createFromResource(
                this,
                R.array.jobdesk_list,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    jobInput = spinner.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }
        btnSave.setOnClickListener{
            if (namaUser.text.isNotEmpty() && username.text.isNotEmpty() && password.text.isNotEmpty()){
                if (intent != null){
                    database.userDao().ubahUser(
                        User(
                            intent.getInt("id", 0),
                            namaUser.text.toString(),
                            username.text.toString(),
                            password.text.toString(),
                            jobInput
                        )
                    )
                } else {
                    database.userDao().tambahUser(
                        User(
                            null,
                            namaUser.text.toString(),
                            username.text.toString(),
                            password.text.toString(),
                            jobInput
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
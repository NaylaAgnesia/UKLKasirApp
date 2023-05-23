package www.smktelkommlg.myuklll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import www.smktelkommlg.myuklll.meja.MejaActivity
import www.smktelkommlg.myuklll.menu.MenuActivity
import www.smktelkommlg.myuklll.user.UserActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataUser: Button = findViewById(R.id.btn_data_user)
        val menu: Button = findViewById(R.id.btn_menu)
        val meja: Button = findViewById(R.id.btn_meja)

        dataUser.setOnClickListener{
            val moveUser = Intent(this@MainActivity, UserActivity::class.java)
            startActivity(moveUser)
        }

        menu.setOnClickListener {
            val moveMenu = Intent ( this@MainActivity, MenuActivity::class.java)
            startActivity(moveMenu)
        }

        meja.setOnClickListener {
            val moveMeja = Intent (this@MainActivity, MejaActivity::class.java)
            startActivity(moveMeja)
        }

    }
}
package www.smktelkommlg.myuklll

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import www.smktelkommlg.myuklll.meja.Meja
import www.smktelkommlg.myuklll.meja.MejaDao
import www.smktelkommlg.myuklll.menu.makanan.Makanan
import www.smktelkommlg.myuklll.menu.MenuDao
import www.smktelkommlg.myuklll.menu.minuman.Minuman
import www.smktelkommlg.myuklll.user.User
import www.smktelkommlg.myuklll.user.UserDao

@Database(entities = [User::class, Makanan::class, Minuman::class, Meja::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun menuDao(): MenuDao
    abstract fun mejaDao(): MejaDao


    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            if (instance==null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "CafeDB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}
package www.smktelkommlg.myuklll.user

import www.smktelkommlg.myuklll.user.User
import androidx.room.*


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun tambahUser(user: User)

    @Update
    fun ubahUser(user: User)

    @Delete
    fun hapusUser(user: User)

    @Query("SELECT * FROM User WHERE userId= :userId")
    fun ambilSemuaUser(userId: Int): User

    @Query("SELECT * FROM user")
    fun getAll(): List<User>
}
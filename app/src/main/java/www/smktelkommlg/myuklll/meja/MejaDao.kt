package www.smktelkommlg.myuklll.meja

import androidx.room.*
import www.smktelkommlg.myuklll.meja.Meja

@Dao
interface MejaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun tambahMeja(meja: Meja)

    @Update
    fun ubahMeja(meja: Meja)

    @Delete
    fun hapusMeja(meja: Meja)

    @Query("SELECT * FROM Meja WHERE mejaId= :mejaId")
    fun ambilSemuaMeja(mejaId: Int): Meja

    @Query("SELECT * FROM meja")
    fun getMeja(): List<Meja>
}
package www.smktelkommlg.myuklll.menu

import androidx.room.*
import www.smktelkommlg.myuklll.menu.makanan.Makanan
import www.smktelkommlg.myuklll.menu.minuman.Minuman

@Dao
interface MenuDao {
    //Makanan
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun tambahMkn(makanan: Makanan)

   @Update
   fun ubahMkn(makanan: Makanan)

   @Delete
   fun hapusMkn(makanan: Makanan)

   @Query("SELECT * FROM Makanan WHERE menuId= :menuId")
   fun ambilSemuaMkn(menuId: Int): Makanan

   @Query("SELECT * FROM makanan")
   fun getMkn(): List<Makanan>

   //Minuman
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun tambahMinum(minuman: Minuman)

   @Update
   fun ubahMinum(minuman: Minuman)

   @Delete
   fun hapusMinum(minuman: Minuman)

   @Query("SELECT * FROM Minuman WHERE menuId= :menuId")
   fun ambilSemuaMinum(menuId: Int): Minuman

   @Query("SELECT * FROM minuman")
   fun getMinum(): List<Minuman>

}
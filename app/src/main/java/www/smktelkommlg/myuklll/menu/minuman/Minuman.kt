package www.smktelkommlg.myuklll.menu.minuman

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Minuman(
    @PrimaryKey(autoGenerate = true) var menuId: Int? = null,
    @ColumnInfo(name = "namaMinuman") var namaMinuman: String?,
    @ColumnInfo(name = "deskripsi") var deskripsi: String?,
    @ColumnInfo(name = "harga") var harga: String?
)
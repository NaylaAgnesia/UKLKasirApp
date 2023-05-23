package www.smktelkommlg.myuklll.menu.makanan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Makanan (
    @PrimaryKey(autoGenerate = true) var menuId: Int? = null,
    @ColumnInfo(name = "namaMakanan") var namaMakanan: String?,
    @ColumnInfo(name = "deskripsi") var deskripsi: String?,
    @ColumnInfo(name = "harga") var harga: String?
    )


package www.smktelkommlg.myuklll.meja

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Meja (
    @PrimaryKey(autoGenerate = true) var mejaId: Int? = null,
    @ColumnInfo(name = "namaMeja") var namaMeja: String?

)

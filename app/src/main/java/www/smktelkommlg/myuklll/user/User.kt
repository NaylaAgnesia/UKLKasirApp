package www.smktelkommlg.myuklll.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey(autoGenerate = true) var userId: Int? = null,
    @ColumnInfo(name = "namaUser") var namaUser: String?,
    @ColumnInfo(name = "username") var username: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "jobdesk") var jobdesk: String?
)
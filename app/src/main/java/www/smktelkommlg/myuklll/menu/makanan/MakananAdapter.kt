package www.smktelkommlg.myuklll.menu.makanan

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import www.smktelkommlg.myuklll.R

class MakananAdapter(var list: List<Makanan>) : RecyclerView.Adapter<MakananAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog{
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var namaMakanan: TextView
        var deskripsi: TextView
        var harga: TextView

        init {
            namaMakanan = view.findViewById(R.id.txtNamaMakanan)
            deskripsi = view.findViewById(R.id.txtDeskripsi)
            harga = view.findViewById(R.id.txtHarga)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.makanan_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaMakanan.text = list[position].namaMakanan
        holder.deskripsi.text = list[position].deskripsi
        holder.harga.text = list[position].harga
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
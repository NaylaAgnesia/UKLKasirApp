package www.smktelkommlg.myuklll.meja

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import www.smktelkommlg.myuklll.R
import www.smktelkommlg.myuklll.user.UserAdapter

class MejaAdapter(var list: List<Meja>) : RecyclerView.Adapter<MejaAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog{
        fun onClick(positon: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var namaMeja: TextView

        init {
            namaMeja = view.findViewById(R.id.txtMeja)
            view.setOnClickListener {
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meja_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MejaAdapter.ViewHolder, position: Int) {
        holder.namaMeja.text = list[position].namaMeja
    }
}
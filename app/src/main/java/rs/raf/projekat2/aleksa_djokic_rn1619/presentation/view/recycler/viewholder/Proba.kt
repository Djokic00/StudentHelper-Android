//package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.viewholder
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.application.R
//import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket
//import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.LoginActivity.CREDENTIAL_KEY_IS_ADMIN
//import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.LoginActivity.PACKAGE_NAME
//import java.util.function.Consumer
//
//class Proba {
//}
//
//package raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter
//import com.example.application.R
//import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket
//import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.LoginActivity
//import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.LoginActivity.CREDENTIAL_KEY_IS_ADMIN
//import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.LoginActivity.PACKAGE_NAME
//
//class ToDoAdapter(
//    diffCallback: DiffUtil.ItemCallback<Ticket?>, onImageClicked: Consumer<Ticket?>,
//    onMoveClicked: Consumer<Ticket?>, onDeleteClicked: Consumer<Ticket?>
//) : ListAdapter<Ticket?, raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter.ToDoAdapter.ViewHolder?>(
//    diffCallback
//) {
//    private val onImageClicked: Consumer<Ticket?>
//    private val onMoveClicked: Consumer<Ticket?>
//    private val onDeleteClicked: Consumer<Ticket?>
//
//    init {
//        this.onImageClicked = onImageClicked
//        this.onMoveClicked = onMoveClicked
//        this.onDeleteClicked = onDeleteClicked
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter.ToDoAdapter.ViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_todo_list, parent, false)
//        return raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter.ToDoAdapter.ViewHolder(view,
//            Consumer { position: Int? ->
//                val ticket: Ticket? = getItem(position!!)
//                onImageClicked.accept(ticket)
//            },
//            Consumer { position: Int? ->
//                val ticket: Ticket? = getItem(position!!)
//                onMoveClicked.accept(ticket)
//            },
//            Consumer { position: Int? ->
//                val ticket: Ticket? = getItem(position!!)
//                onDeleteClicked.accept(ticket)
//            })
//    }
//
//    override fun onBindViewHolder(
//        holder: raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter.ToDoAdapter.ViewHolder,
//        position: Int
//    ) {
//        val ticket: Ticket? = getItem(position) // uzima tiket na odredjenoj poziciji iz ViewModela liste
//        holder.bind(ticket)
//    }
//
//    class ViewHolder(
//        itemView: View, onImageClicked: Consumer<Int?>, onMoveClicked: Consumer<Int?>,
//        onDeleteClicked: Consumer<Int?>
//    ) : RecyclerView.ViewHolder(itemView) {
//        private var flag = true
//
//        init {
//            itemView.findViewById<View>(R.id.ticketTypePicture).setOnClickListener { view: View? ->
//                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
//                    onImageClicked.accept(bindingAdapterPosition)
//                }
//            }
//            itemView.findViewById<View>(R.id.moveTicket).setOnClickListener { move: View? ->
//                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
//                    onMoveClicked.accept(bindingAdapterPosition)
//                }
//            }
//            itemView.findViewById<View>(R.id.deleteTicket).setOnClickListener { delete: View? ->
//                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
//                    onDeleteClicked.accept(bindingAdapterPosition)
//                }
//            }
//            val sharedPreferences = itemView.context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
//            val message = sharedPreferences.getString(CREDENTIAL_KEY_IS_ADMIN, null)
//            if (message == "false") {
//                flag = false
//            }
//        }
//
//        fun bind(ticket: Ticket) {
//            val ticketType = itemView.findViewById<ImageView>(R.id.ticketTypePicture)
//            if (ticket.getTicketType().equals("Bug")) {
//                ticketType.setImageResource(R.drawable.bug_icon)
//            } else {
//                ticketType.setImageResource(R.drawable.enhancement_icon)
//            }
//            (itemView.findViewById<View>(R.id.ticketTitle) as TextView).setText(ticket.getTitle())
//            (itemView.findViewById<View>(R.id.ticketDescription) as TextView).setText(ticket.getDescription())
//            if (!flag) {
//                val deleteTicket = itemView.findViewById<ImageView>(R.id.deleteTicket)
//                deleteTicket.visibility = View.GONE
//            }
//        }
//    }
//}
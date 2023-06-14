package com.example.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.Utility.animateViewofFloat
import com.example.admin.Utility.setHeightLinearLayout


class ClientsAdapter(private val clientList: ArrayList<Clientes>) : RecyclerView.Adapter<ClientsAdapter.MyViewHolder>(){

    private lateinit var context: Context
    private var minimized = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsAdapter.MyViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.card_clientes, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ClientsAdapter.MyViewHolder, position: Int) {
        val client: Clientes = clientList[position]
         setHeightLinearLayout(holder.lyDataRunBody, 0)
         holder.lyDataRunBodyContainer.translationY = -200f

         holder.ivHeaderOpenClose.setOnClickListener{
             if (minimized){
                 setHeightLinearLayout(holder.lyDataRunBody, 600)
                 animateViewofFloat(holder.lyDataRunBodyContainer, "translationY", 0f, 300L)
                 holder.ivHeaderOpenClose.setRotation(180f)
                 minimized = false
             }else{
                 holder.lyDataRunBodyContainer.translationY = -200f
                 setHeightLinearLayout(holder.lyDataRunBody, 0)
                 holder.ivHeaderOpenClose.setRotation(0f)
                 minimized = true
             }
         }



         holder.tvnameClient.text = client.nameClient
         holder.tvGmail.text = client.gmail
         holder.tvDirection.text = client.address
         holder.tvTelefono.text = client.phone
         holder.tvTelefonoEmpresa.text = client.phoneBusiness
         holder.tvCredito.text = client.credit
         holder.tvLimiteCredito.text = client.creditLimit
         holder.tvSaldoPendiente.text = client.balance

    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    public class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        val tvnameClient: TextView = itemView.findViewById(R.id.tvNameClient)

          val tvGmail: TextView = itemView.findViewById(R.id.tvGmail)
          val tvDirection: TextView = itemView.findViewById(R.id.tvDireccion)
          val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
          val tvTelefonoEmpresa: TextView = itemView.findViewById(R.id.tvTelefonoEmpresa)
          val tvCredito: TextView = itemView.findViewById(R.id.tvCredito)
          val tvLimiteCredito: TextView = itemView.findViewById(R.id.tvLimiteCredito)
          val tvSaldoPendiente: TextView = itemView.findViewById(R.id.tvSaldoPendiente)
  val ivHeaderOpenClose: ImageView = itemView.findViewById(R.id.ivHeaderOpenClose)
          val lyDataRunBody: LinearLayout = itemView.findViewById(R.id.lyDataRunBody)
          val lyDataRunBodyContainer: LinearLayout = itemView.findViewById(R.id.lyDataRunBodyContainer)












    }
}
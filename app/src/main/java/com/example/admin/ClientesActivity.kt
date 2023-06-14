package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.Date

class ClientesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var clientesArray: ArrayList<Clientes>
    private lateinit var myAdapter: ClientsAdapter


    private lateinit var lyPopUpAddclintes: LinearLayout
    private lateinit var rlRecyclersClientes: LinearLayout

    private var minimizedAddClientes = true

    private lateinit var nombreCliente: EditText
    private lateinit var gmailClient: EditText
    private lateinit var direccionCliente: EditText
    private lateinit var telefonoCliente: EditText
    private lateinit var telefonoEmpresaCliente: EditText
    private lateinit var creditoLimete: EditText

    private lateinit var progressBar: ProgressBar
    private lateinit var loadingText: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)

        recyclerView = findViewById(R.id.rvClientes)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        clientesArray = arrayListOf()
        myAdapter = ClientsAdapter(clientesArray)
        recyclerView.adapter = myAdapter

        lyPopUpAddclintes = findViewById(R.id.lyPopUpAddclintes)

        nombreCliente = findViewById(R.id.etNombreCliente)
        gmailClient = findViewById(R.id.etGmailCliente)
        direccionCliente = findViewById(R.id.etDireccionCliente)
        telefonoCliente = findViewById(R.id.etTelefonoCliente)
        telefonoEmpresaCliente = findViewById(R.id.etTelefonoEmpresaCliente)
        creditoLimete = findViewById(R.id.etLimiteCreditoliente)

        progressBar = findViewById(R.id.progressBar)

        loadingText = findViewById(R.id.loadingText)

        mostrarMensajeCargandoDatos()



        val fab: View = findViewById(R.id.fab)

        fab.setOnClickListener {
            cerrarAbrirPopUpAddClintes()
        }


        cargarDatosRecycleView("user", Query.Direction.ASCENDING)
    }

    fun añadirCliente(view: View){
        addClients(nombreCliente.text.toString(), gmailClient.text.toString(), direccionCliente.text.toString(), telefonoCliente.text.toString(), telefonoEmpresaCliente.text.toString(), creditoLimete.text.toString())
        Toast.makeText(this, "Agregando Cliente", Toast.LENGTH_LONG).show()
    }


    private fun cargarDatosRecycleView(field: String, order: Query.Direction){

        var db = FirebaseFirestore.getInstance()
        db.collection("clientes")
            .whereEqualTo("userName", "maria23@gmail.com")
            .get()
            .addOnSuccessListener { documents ->
                Toast.makeText(this, "Data: ${documents}", Toast.LENGTH_SHORT).show()

                for (document in documents) {

                    clientesArray.add(document.toObject(Clientes::class.java))
                    myAdapter.notifyDataSetChanged()

                    Toast.makeText(this, "Data: ${document.data}", Toast.LENGTH_SHORT).show()
                }
                ocultarMensajeCargandoDatos()
            }
            .addOnFailureListener { exception ->

                Toast.makeText(this, "Error: ${exception}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addClients(nombreCliente : String, gmail: String, direccion: String, telefono: String, telefonoEmpresa: String, creditoLimete: String)
    {

        var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())
        var dbClient = FirebaseFirestore.getInstance()
        val useremail = "maria23@gmail.com"
        var id: String = useremail + nombreCliente + dateRegister
        id = id.replace(" ", "")
        id = id.replace(":", "")
        id = id.replace("/", "")

        dbClient.collection("clientes").document(id).set(
            hashMapOf(
                "dateRegistrer" to "dateRegister",
                "nameClient" to nombreCliente,
                "userName" to useremail,
                "gmail" to gmail,
                "address" to direccion,
                "phone" to telefono,
                "phoneBusiness" to telefonoEmpresa,
                "balance" to "0",
                "credit" to "0",
                "creditLimit" to creditoLimete,

                )
        )
        cerrarAbrirPopUpAddClintes()
        cargarDatosRecycleView("user", Query.Direction.ASCENDING)

    }


    public fun cerrarAbrirPopUpAddClintes(){
        if(minimizedAddClientes){
            minimizedAddClientes = false

            lyPopUpAddclintes.isVisible = true


        }else{
            minimizedAddClientes = true


            lyPopUpAddclintes.isVisible = false

        }
    }

    private fun mostrarMensajeCargandoDatos() {
        progressBar.visibility = View.VISIBLE
        loadingText.visibility = View.VISIBLE
    }

    private fun ocultarMensajeCargandoDatos() {
        progressBar.visibility = View.GONE
        loadingText.visibility = View.GONE
    }
}
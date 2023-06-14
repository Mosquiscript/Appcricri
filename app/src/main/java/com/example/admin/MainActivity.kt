package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout

    companion object {

        lateinit var Nombre: Clientes
        lateinit var RFC:Clientes
        lateinit var Direcion: Clientes
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolBar()
        initNavigationView()

        val cardViewGastosActivity: CardView = findViewById(R.id.cvGastos)
        val cardViewServiciosActivity: CardView = findViewById(R.id.cvServicios)
        val cardViewClientesActivity: CardView = findViewById(R.id.cvClientes)

        cardViewGastosActivity.setOnClickListener {
            goActivityGastos()
        }

        cardViewServiciosActivity.setOnClickListener {
            goActivityServicios()
        }

        cardViewClientesActivity.setOnClickListener {
            goActivityClientes()
        }
    }





    private fun initNavigationView() {
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var headerView: View = LayoutInflater.from(this).inflate(R.layout.nav_header_main, navigationView, false)
        navigationView.removeHeaderView(headerView)
        navigationView.addHeaderView(headerView)

        /* var tvUser: TextView = headerView.findViewById(R.id.tvUser)
        tvUser.text = useremail*/
    }

    private fun initToolBar() {
        var toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        var toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){


            R.id.nav_item_algo2 -> goActivityClientes()
            R.id.nav_item_algo1 -> goActivityServicios()
            R.id.nav_item_algo3 -> goActivityGastos()
        }
        return true
    }



    private fun goActivityClientes() {
        val intent = Intent(this, ClientesActivity::class.java)
        startActivity(intent)
    }

    private fun goActivityServicios() {
        val intent = Intent(this, ServiciosActivity::class.java)
        startActivity(intent)
    }

    private fun goActivityGastos() {
        val intent = Intent(this, GastosActivity::class.java)
        startActivity(intent)
    }
}
package net.azarquiel.tapitasroom.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.tapitasroom.R
import net.azarquiel.tapitasroom.adapter.AdapterTapa
import net.azarquiel.tapitasroom.databinding.ActivityMainBinding
import net.azarquiel.tapitasroom.model.TapaWithEstablecimiento
import net.azarquiel.tapitasroom.util.Util
import net.azarquiel.tapitasroom.viewmodel.TapaViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var tapas: List<TapaWithEstablecimiento>
    private lateinit var searchView: SearchView
    private lateinit var adapter: AdapterTapa
    private lateinit var tapaViewModel: TapaViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        Util.inyecta(this, "tapitas.db")
        initRV()

        tapaViewModel = ViewModelProvider(this).get(TapaViewModel::class.java)

        tapaViewModel.getTapasWithEstablecimiento().observe(this, Observer { tapas ->
            // Update the cached copy of the products in the adapter.
            this.tapas = tapas
            adapter.setTapas(tapas)
        })
    }

    private fun initRV() {
        adapter = AdapterTapa(this, R.layout.rowtapa)
        binding.cm.rvtapa.adapter = adapter
        binding.cm.rvtapa.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        // ************* <Filtro> ************
        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
        // ************* </Filtro> ************

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_star -> {
                // pulsaste la estrella
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // ************* <Filtro> ************
    override fun onQueryTextChange(query: String): Boolean {
        val original = ArrayList<TapaWithEstablecimiento>(tapas)
        adapter.setTapas(original.filter { tapa -> tapa.tapa.nombre.contains(query,true) })
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }

    fun onClickTapa(v: View) {
        val tapapulsada = v.tag as TapaWithEstablecimiento

        val intent = Intent(this, TapaActivity::class.java)
        intent.putExtra("tapa", tapapulsada)
        startActivity(intent)
    }
}
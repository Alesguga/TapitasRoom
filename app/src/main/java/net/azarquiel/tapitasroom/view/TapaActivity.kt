package net.azarquiel.tapitasroom.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import net.azarquiel.tapitasroom.R
import net.azarquiel.tapitasroom.model.TapaWithEstablecimiento

class TapaActivity : AppCompatActivity() {
    private lateinit var tapawe: TapaWithEstablecimiento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tapa)

        tapawe = intent.getSerializableExtra("tapa") as TapaWithEstablecimiento
        showTapa()

    }

    private fun showTapa() {
        val tapa = tapawe.tapa
        val establecimiento = tapa.establecimiento
        val ivtapa = findViewById<ImageView>(R.id.ivtapa)
        // foto de internet a traves de Picasso
        Picasso.get().load("http://82.223.108.85/storage/${tapa.url_imagen}").into(ivtapa)

    }

}
package net.azarquiel.tapitasroom.model

import android.app.Application
import androidx.lifecycle.LiveData

class TapaRepository(application: Application) {

    val tapaDao = TapitasDB.getDatabase(application)!!.tapaDao()

    fun getTapasWithEstablecimiento(): LiveData<List<TapaWithEstablecimiento>> {
        return tapaDao.getTapasWithEstablecimiento()
    }
}
class EstablecimientoRepository(application: Application) {

    val establecimientoDao = TapitasDB.getDatabase(application)!!.establecimientoDao()

    fun getEstablecimientoWithTapas(idestablecimiento:Int): LiveData<EstablecimientoWithTapas> {
        return establecimientoDao.getEstablecimientoWithTapas(idestablecimiento)
    }
}

package net.azarquiel.tapitasroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.azarquiel.tapitasroom.model.Establecimiento
import net.azarquiel.tapitasroom.model.EstablecimientoRepository
import net.azarquiel.tapitasroom.model.EstablecimientoWithTapas
import net.azarquiel.tapitasroom.model.Tapa
import net.azarquiel.tapitasroom.model.TapaRepository
import net.azarquiel.tapitasroom.model.TapaWithEstablecimiento

class TapaViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: TapaRepository = TapaRepository(application)


    fun getTapasWithEstablecimiento(): LiveData<List<TapaWithEstablecimiento>>{
        return repository.getTapasWithEstablecimiento()
    }

}
class EstablecimientoViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: EstablecimientoRepository = EstablecimientoRepository(application)

    fun getEstablecimientoWithTapas(idestablecimiento:Int): LiveData<EstablecimientoWithTapas>{
        return repository.getEstablecimientoWithTapas(idestablecimiento)
    }
}

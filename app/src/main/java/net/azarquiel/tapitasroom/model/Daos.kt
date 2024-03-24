package net.azarquiel.tapitasroom.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TapaDao {
    @Transaction
    @Query("SELECT * FROM tapa")
    fun getTapasWithEstablecimiento(): LiveData<List<TapaWithEstablecimiento>>
}
@Dao
interface EstablecimientoDao {
    @Transaction
    @Query("SELECT * FROM establecimiento where id=:idestablecimiento")
    fun getEstablecimientoWithTapas(idestablecimiento:Int): LiveData<EstablecimientoWithTapas>
}


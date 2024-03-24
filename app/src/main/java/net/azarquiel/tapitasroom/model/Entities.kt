package net.azarquiel.tapitasroom.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable

@Entity(tableName = "establecimiento")
data class Establecimiento(@PrimaryKey
                var id: Int=0,          // atributo en entity
                var nombre:String="",
                var direccion:String="",
                var telefono:String="",
                var url_imagen_exterior:String="",
                var latitud:Float=0F,
                var longitud:Float=0F):Serializable

@Entity(tableName = "tapa",
    foreignKeys = [ForeignKey(entity = Establecimiento::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("establecimiento"))])
data class Tapa(@PrimaryKey
                var id: Int=0,          // atributo en entity
                var establecimiento: Int= 0,
                var nombre:String="",
                var descripcion:String="",
                var url_imagen:String="",
                var fav:Int=0):Serializable

// Relación uno a uno (una tapa es de un establecimiento)
data class TapaWithEstablecimiento(
    @Embedded val tapa: Tapa,
    @Relation(
        parentColumn = "establecimiento",
        entityColumn = "id"
    )
    val establecimiento: Establecimiento
):Serializable
// Relación uno a muchos (un establecimiento muchas tapas)
data class EstablecimientoWithTapas(
    @Embedded val establecimiento: Establecimiento,
    @Relation(
        parentColumn = "id",
        entityColumn = "establecimiento"
    )
    val tapas: List<Tapa>
)
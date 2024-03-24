package net.azarquiel.tapitasroom.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tapa::class, Establecimiento::class], version = 1)
abstract class TapitasDB: RoomDatabase() {
    abstract fun tapaDao(): TapaDao
    abstract fun establecimientoDao(): EstablecimientoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE:  TapitasDB? = null

        fun getDatabase(context: Context): TapitasDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TapitasDB::class.java,   "tapitas.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

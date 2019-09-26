package br.com.pu.pu_challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.pu.pu_challenge.data.Converters
import br.com.pu.pu_challenge.data.database.local.DealLocal

@Database(entities = [DealLocal::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun dealDao(): DealDao
}
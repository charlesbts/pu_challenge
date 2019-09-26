package br.com.pu.pu_challenge.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.pu.pu_challenge.data.database.local.DealLocal

@Dao
interface DealDao {

    @Query("select * from DealLocal")
    fun retrieveDeals(): DataSource.Factory<Int, DealLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeals(deals: List<DealLocal>)
}
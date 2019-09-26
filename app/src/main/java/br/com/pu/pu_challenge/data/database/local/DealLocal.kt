package br.com.pu.pu_challenge.data.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DealLocal(
    @PrimaryKey val id: String,
    @ColumnInfo val thumbUrls: List<String>,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String,
    @ColumnInfo val salePrice: Float
)
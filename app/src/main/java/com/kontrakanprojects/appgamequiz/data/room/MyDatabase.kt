package com.kontrakanprojects.appgamequiz.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kontrakanprojects.appgamequiz.data.entity.CategoryEntity
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.entity.StudentScoreEntity
import com.kontrakanprojects.appgamequiz.util.Converter


@Database(entities = [QuestionEntity::class, OptionEntity::class,CategoryEntity::class,StudentScoreEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class MyDatabase: RoomDatabase(){
    abstract fun questionDao(): QuestionDao

    companion object{
        @Volatile
        private var instance: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase =
            instance ?: synchronized(this){
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MyDatabase::class.java, "gamequiz.db")
                .fallbackToDestructiveMigration()
//                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build()

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE options ADD fk_option FOREIGN_KEY"
                )
            }
        }
    }
}
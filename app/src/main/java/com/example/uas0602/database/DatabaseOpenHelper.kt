package com.example.uas0602.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.uas0602.model.FavoriteMatchField
import com.example.uas0602.model.FavoriteTeamField
import org.jetbrains.anko.db.*

class DatabaseOpenHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {


    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatchField.TABLE_FAVORITE, true,
            FavoriteMatchField.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatchField.ID_EVENT to TEXT + UNIQUE,
            FavoriteMatchField.STR_EVENT to TEXT,
            FavoriteMatchField.SCORE_AWAY to TEXT,
            FavoriteMatchField.SCORE_HOME to TEXT,
            FavoriteMatchField.DATE_EVENT to TEXT,
            FavoriteMatchField.ID_HOME_TEAM to TEXT,
            FavoriteMatchField.ID_AWAY_TEAM to TEXT)

        db?.createTable(FavoriteTeamField.TABLE_FAVORITE, true,
            FavoriteTeamField.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeamField.ID_TEAM to TEXT + UNIQUE,
            FavoriteTeamField.STR_TEAM to TEXT,
            FavoriteTeamField.STR_DESCRIPTION to TEXT,
            FavoriteTeamField.STR_COUNTRY to TEXT,
            FavoriteTeamField.STR_LEAGUE to TEXT,
            FavoriteTeamField.STR_STADIUM to TEXT,
            FavoriteTeamField.STR_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatchField.TABLE_FAVORITE, true)
    }
}

val Context.database : DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)
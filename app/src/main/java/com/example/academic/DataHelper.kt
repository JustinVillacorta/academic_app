package com.example.academic

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "academic.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "new_account"
        const val COLUMN_ID = "id"
        const val COLUMN_FIRSTNAME = "firstname"
        const val COLUMN_LASTNAME = "lastname"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_QUIZ = "quiz"
        const val COLUMN_EXAM = "exam"
        const val COLUMN_GRADE = "grade"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_FIRSTNAME TEXT, "
                + "$COLUMN_LASTNAME TEXT,  "
                + "$COLUMN_USERNAME TEXT UNIQUE, "
                + "$COLUMN_PASSWORD TEXT, "
                + "$COLUMN_QUIZ INTEGER, "
                + "$COLUMN_EXAM INTEGER, "
                + "$COLUMN_GRADE TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(firstname: String, lastname: String, username: String, password: String, quiz: Int, exam: Int, grade: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FIRSTNAME, firstname)
        contentValues.put(COLUMN_LASTNAME, lastname)
        contentValues.put(COLUMN_USERNAME, username)
        contentValues.put(COLUMN_PASSWORD, password)
        contentValues.put(COLUMN_QUIZ, quiz)
        contentValues.put(COLUMN_EXAM, exam)
        contentValues.put(COLUMN_GRADE, grade)
        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return (result != -1L)
    }

    fun isUsernameAvailable(username: String): Boolean {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_ID),
            "$COLUMN_USERNAME = ?",
            arrayOf(username),
            null, null, null
        )
        val available = !cursor.moveToFirst()
        cursor.close()
        db.close()
        return available
    }

    fun isUserExist(username: String): Boolean {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_ID),
            "$COLUMN_USERNAME = ?",
            arrayOf(username),
            null, null, null
        )
        val exists = cursor.moveToFirst()
        cursor.close()
        db.close()
        return exists
    }
}

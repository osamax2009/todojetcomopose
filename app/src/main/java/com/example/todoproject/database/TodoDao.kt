package com.example.todoproject.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.Update
import com.example.todoproject.model.ToDoItem
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos ORDER BY createdAt DESC")
    fun getAllTodos(): Flow<List<ToDoItem>>

    @Query("SELECT * FROM todos WHERE isComplete = 0 ORDER BY createdAt DESC")
    fun getActiveTodos(): Flow<List<ToDoItem>>

    @Query("SELECT * FROM todos WHERE isComplete = 1 ORDER BY createdAt DESC")
    fun getCompletedTodos(): Flow<List<ToDoItem>>

    @Query("SELECT COUNT(*) FROM todos")
    fun getTotalCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM todos WHERE isComplete = 1")
    fun getCompletedCount(): Flow<Int>

    @Insert
    suspend fun insertTodo(todo: ToDoItem)

    @Update
    suspend fun updateTodo(todo: ToDoItem)

    @Delete
    suspend fun deleteTodo(todo: ToDoItem)

    @Query("DELETE FROM todos WHERE isComplete = 1")
    suspend fun deleteCompletedTodos()

    @Query("DELETE FROM todos")
    suspend fun deleteAllTodos()
}

@Database(
    entities = [ToDoItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: android.content.Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

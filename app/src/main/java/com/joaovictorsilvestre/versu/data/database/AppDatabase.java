package com.joaovictorsilvestre.versu.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.joaovictorsilvestre.versu.data.dao.HistoricoDao;
import com.joaovictorsilvestre.versu.data.dao.VersiculoDao;
import com.joaovictorsilvestre.versu.data.entity.Historico;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Versiculo.class, Historico.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "versu_db";
    private static volatile AppDatabase INSTANCE;
    public static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public abstract VersiculoDao versiculoDao();
    public abstract HistoricoDao historicoDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DB_NAME
                    ).build();
                    // Popula versículos na primeira abertura
                    executor.execute(() -> {
                        if (INSTANCE.versiculoDao().contar() == 0) {
                            INSTANCE.versiculoDao().inserirTodos(VersiculoData.getAllVersiculos());
                        }
                    });
                }
            }
        }
        return INSTANCE;
    }
}

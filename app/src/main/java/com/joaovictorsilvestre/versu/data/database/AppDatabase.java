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

/**
 * Classe principal do banco de dados Room.
 * Declara as entidades e versão do esquema.
 * Implementa o padrão Singleton para garantir uma única instância em toda a aplicação.
 */
@Database(
    entities = {Versiculo.class, Historico.class},
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "versu_database";
    private static volatile AppDatabase INSTANCE;

    /** Executor com uma thread dedicada para operações de escrita no banco. */
    static final ExecutorService databaseWriteExecutor =
            Executors.newSingleThreadExecutor();

    // ── DAOs ─────────────────────────────────────────────────────────────

    public abstract VersiculoDao versiculoDao();
    public abstract HistoricoDao historicoDao();

    // ── Singleton ────────────────────────────────────────────────────────

    /**
     * Retorna a instância única do banco de dados.
     * Na primeira chamada, cria o banco e popula os versículos iniciais.
     */
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME
                    ).build();

                    // Popula os versículos na primeira abertura do banco
                    populateInitialData();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Verifica se o banco está vazio e, em caso positivo, insere os versículos iniciais.
     * Executa em thread de background para não bloquear a UI.
     */
    private static void populateInitialData() {
        databaseWriteExecutor.execute(() -> {
            VersiculoDao dao = INSTANCE.versiculoDao();
            if (dao.contar() == 0) {
                dao.inserirTodos(VersiculoData.getAllVersiculos());
            }
        });
    }
}

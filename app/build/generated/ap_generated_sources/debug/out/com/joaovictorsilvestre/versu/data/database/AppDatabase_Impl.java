package com.joaovictorsilvestre.versu.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.joaovictorsilvestre.versu.data.dao.HistoricoDao;
import com.joaovictorsilvestre.versu.data.dao.HistoricoDao_Impl;
import com.joaovictorsilvestre.versu.data.dao.VersiculoDao;
import com.joaovictorsilvestre.versu.data.dao.VersiculoDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile VersiculoDao _versiculoDao;

  private volatile HistoricoDao _historicoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `versiculos` (`id` INTEGER NOT NULL, `texto` TEXT, `referencia` TEXT, `categoria` TEXT, `favorito` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `historico` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `versiculo_id` INTEGER NOT NULL, `data_sorteio` INTEGER NOT NULL, FOREIGN KEY(`versiculo_id`) REFERENCES `versiculos`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_historico_versiculo_id` ON `historico` (`versiculo_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8c5555e1eb98222a629e6161841f052a')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `versiculos`");
        db.execSQL("DROP TABLE IF EXISTS `historico`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsVersiculos = new HashMap<String, TableInfo.Column>(5);
        _columnsVersiculos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVersiculos.put("texto", new TableInfo.Column("texto", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVersiculos.put("referencia", new TableInfo.Column("referencia", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVersiculos.put("categoria", new TableInfo.Column("categoria", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVersiculos.put("favorito", new TableInfo.Column("favorito", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVersiculos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVersiculos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVersiculos = new TableInfo("versiculos", _columnsVersiculos, _foreignKeysVersiculos, _indicesVersiculos);
        final TableInfo _existingVersiculos = TableInfo.read(db, "versiculos");
        if (!_infoVersiculos.equals(_existingVersiculos)) {
          return new RoomOpenHelper.ValidationResult(false, "versiculos(com.joaovictorsilvestre.versu.data.entity.Versiculo).\n"
                  + " Expected:\n" + _infoVersiculos + "\n"
                  + " Found:\n" + _existingVersiculos);
        }
        final HashMap<String, TableInfo.Column> _columnsHistorico = new HashMap<String, TableInfo.Column>(3);
        _columnsHistorico.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistorico.put("versiculo_id", new TableInfo.Column("versiculo_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistorico.put("data_sorteio", new TableInfo.Column("data_sorteio", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHistorico = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysHistorico.add(new TableInfo.ForeignKey("versiculos", "CASCADE", "NO ACTION", Arrays.asList("versiculo_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesHistorico = new HashSet<TableInfo.Index>(1);
        _indicesHistorico.add(new TableInfo.Index("index_historico_versiculo_id", false, Arrays.asList("versiculo_id"), Arrays.asList("ASC")));
        final TableInfo _infoHistorico = new TableInfo("historico", _columnsHistorico, _foreignKeysHistorico, _indicesHistorico);
        final TableInfo _existingHistorico = TableInfo.read(db, "historico");
        if (!_infoHistorico.equals(_existingHistorico)) {
          return new RoomOpenHelper.ValidationResult(false, "historico(com.joaovictorsilvestre.versu.data.entity.Historico).\n"
                  + " Expected:\n" + _infoHistorico + "\n"
                  + " Found:\n" + _existingHistorico);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8c5555e1eb98222a629e6161841f052a", "4d9eb22005bd3f4439eca7bbfd3a384c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "versiculos","historico");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `versiculos`");
      _db.execSQL("DELETE FROM `historico`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(VersiculoDao.class, VersiculoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HistoricoDao.class, HistoricoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public VersiculoDao versiculoDao() {
    if (_versiculoDao != null) {
      return _versiculoDao;
    } else {
      synchronized(this) {
        if(_versiculoDao == null) {
          _versiculoDao = new VersiculoDao_Impl(this);
        }
        return _versiculoDao;
      }
    }
  }

  @Override
  public HistoricoDao historicoDao() {
    if (_historicoDao != null) {
      return _historicoDao;
    } else {
      synchronized(this) {
        if(_historicoDao == null) {
          _historicoDao = new HistoricoDao_Impl(this);
        }
        return _historicoDao;
      }
    }
  }
}

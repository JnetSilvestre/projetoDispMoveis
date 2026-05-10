package com.joaovictorsilvestre.versu.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VersiculoDao_Impl implements VersiculoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Versiculo> __insertionAdapterOfVersiculo;

  private final EntityDeletionOrUpdateAdapter<Versiculo> __updateAdapterOfVersiculo;

  public VersiculoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVersiculo = new EntityInsertionAdapter<Versiculo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `versiculos` (`id`,`texto`,`referencia`,`categoria`,`favorito`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Versiculo entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTexto() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTexto());
        }
        if (entity.getReferencia() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getReferencia());
        }
        if (entity.getCategoria() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategoria());
        }
        final int _tmp = entity.isFavorito() ? 1 : 0;
        statement.bindLong(5, _tmp);
      }
    };
    this.__updateAdapterOfVersiculo = new EntityDeletionOrUpdateAdapter<Versiculo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `versiculos` SET `id` = ?,`texto` = ?,`referencia` = ?,`categoria` = ?,`favorito` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Versiculo entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTexto() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTexto());
        }
        if (entity.getReferencia() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getReferencia());
        }
        if (entity.getCategoria() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategoria());
        }
        final int _tmp = entity.isFavorito() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public void inserirTodos(final List<Versiculo> versiculos) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfVersiculo.insert(versiculos);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void atualizar(final Versiculo versiculo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfVersiculo.handle(versiculo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Versiculo sortearAleatorio() {
    final String _sql = "SELECT * FROM versiculos ORDER BY RANDOM() LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTexto = CursorUtil.getColumnIndexOrThrow(_cursor, "texto");
      final int _cursorIndexOfReferencia = CursorUtil.getColumnIndexOrThrow(_cursor, "referencia");
      final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
      final int _cursorIndexOfFavorito = CursorUtil.getColumnIndexOrThrow(_cursor, "favorito");
      final Versiculo _result;
      if (_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTexto;
        if (_cursor.isNull(_cursorIndexOfTexto)) {
          _tmpTexto = null;
        } else {
          _tmpTexto = _cursor.getString(_cursorIndexOfTexto);
        }
        final String _tmpReferencia;
        if (_cursor.isNull(_cursorIndexOfReferencia)) {
          _tmpReferencia = null;
        } else {
          _tmpReferencia = _cursor.getString(_cursorIndexOfReferencia);
        }
        final String _tmpCategoria;
        if (_cursor.isNull(_cursorIndexOfCategoria)) {
          _tmpCategoria = null;
        } else {
          _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
        }
        _result = new Versiculo(_tmpId,_tmpTexto,_tmpReferencia,_tmpCategoria);
        final boolean _tmpFavorito;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfFavorito);
        _tmpFavorito = _tmp != 0;
        _result.setFavorito(_tmpFavorito);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Versiculo sortearPorCategoria(final String categoria) {
    final String _sql = "SELECT * FROM versiculos WHERE categoria = ? ORDER BY RANDOM() LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (categoria == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, categoria);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTexto = CursorUtil.getColumnIndexOrThrow(_cursor, "texto");
      final int _cursorIndexOfReferencia = CursorUtil.getColumnIndexOrThrow(_cursor, "referencia");
      final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
      final int _cursorIndexOfFavorito = CursorUtil.getColumnIndexOrThrow(_cursor, "favorito");
      final Versiculo _result;
      if (_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTexto;
        if (_cursor.isNull(_cursorIndexOfTexto)) {
          _tmpTexto = null;
        } else {
          _tmpTexto = _cursor.getString(_cursorIndexOfTexto);
        }
        final String _tmpReferencia;
        if (_cursor.isNull(_cursorIndexOfReferencia)) {
          _tmpReferencia = null;
        } else {
          _tmpReferencia = _cursor.getString(_cursorIndexOfReferencia);
        }
        final String _tmpCategoria;
        if (_cursor.isNull(_cursorIndexOfCategoria)) {
          _tmpCategoria = null;
        } else {
          _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
        }
        _result = new Versiculo(_tmpId,_tmpTexto,_tmpReferencia,_tmpCategoria);
        final boolean _tmpFavorito;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfFavorito);
        _tmpFavorito = _tmp != 0;
        _result.setFavorito(_tmpFavorito);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> listarCategorias() {
    final String _sql = "SELECT DISTINCT categoria FROM versiculos ORDER BY categoria ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Versiculo>> listarFavoritos() {
    final String _sql = "SELECT * FROM versiculos WHERE favorito = 1 ORDER BY referencia ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"versiculos"}, false, new Callable<List<Versiculo>>() {
      @Override
      @Nullable
      public List<Versiculo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTexto = CursorUtil.getColumnIndexOrThrow(_cursor, "texto");
          final int _cursorIndexOfReferencia = CursorUtil.getColumnIndexOrThrow(_cursor, "referencia");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfFavorito = CursorUtil.getColumnIndexOrThrow(_cursor, "favorito");
          final List<Versiculo> _result = new ArrayList<Versiculo>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Versiculo _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTexto;
            if (_cursor.isNull(_cursorIndexOfTexto)) {
              _tmpTexto = null;
            } else {
              _tmpTexto = _cursor.getString(_cursorIndexOfTexto);
            }
            final String _tmpReferencia;
            if (_cursor.isNull(_cursorIndexOfReferencia)) {
              _tmpReferencia = null;
            } else {
              _tmpReferencia = _cursor.getString(_cursorIndexOfReferencia);
            }
            final String _tmpCategoria;
            if (_cursor.isNull(_cursorIndexOfCategoria)) {
              _tmpCategoria = null;
            } else {
              _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            }
            _item = new Versiculo(_tmpId,_tmpTexto,_tmpReferencia,_tmpCategoria);
            final boolean _tmpFavorito;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorito);
            _tmpFavorito = _tmp != 0;
            _item.setFavorito(_tmpFavorito);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int contar() {
    final String _sql = "SELECT COUNT(*) FROM versiculos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

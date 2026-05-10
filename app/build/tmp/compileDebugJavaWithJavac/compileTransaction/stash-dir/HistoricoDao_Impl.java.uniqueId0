package com.joaovictorsilvestre.versu.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.joaovictorsilvestre.versu.data.entity.Historico;
import com.joaovictorsilvestre.versu.data.entity.Versiculo;
import com.joaovictorsilvestre.versu.data.model.HistoricoComVersiculo;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HistoricoDao_Impl implements HistoricoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Historico> __insertionAdapterOfHistorico;

  private final EntityDeletionOrUpdateAdapter<Historico> __deletionAdapterOfHistorico;

  private final SharedSQLiteStatement __preparedStmtOfLimparTudo;

  public HistoricoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistorico = new EntityInsertionAdapter<Historico>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `historico` (`id`,`versiculo_id`,`data_sorteio`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Historico entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getVersiculoId());
        statement.bindLong(3, entity.getDataSorteio());
      }
    };
    this.__deletionAdapterOfHistorico = new EntityDeletionOrUpdateAdapter<Historico>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `historico` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Historico entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfLimparTudo = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM historico";
        return _query;
      }
    };
  }

  @Override
  public void inserir(final Historico historico) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfHistorico.insert(historico);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletar(final Historico historico) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfHistorico.handle(historico);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void limparTudo() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfLimparTudo.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfLimparTudo.release(_stmt);
    }
  }

  @Override
  public LiveData<List<HistoricoComVersiculo>> listarTodos() {
    final String _sql = "SELECT * FROM historico ORDER BY data_sorteio DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"versiculos",
        "historico"}, true, new Callable<List<HistoricoComVersiculo>>() {
      @Override
      @Nullable
      public List<HistoricoComVersiculo> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfVersiculoId = CursorUtil.getColumnIndexOrThrow(_cursor, "versiculo_id");
            final int _cursorIndexOfDataSorteio = CursorUtil.getColumnIndexOrThrow(_cursor, "data_sorteio");
            final LongSparseArray<Versiculo> _collectionVersiculo = new LongSparseArray<Versiculo>();
            while (_cursor.moveToNext()) {
              final Long _tmpKey;
              if (_cursor.isNull(_cursorIndexOfVersiculoId)) {
                _tmpKey = null;
              } else {
                _tmpKey = _cursor.getLong(_cursorIndexOfVersiculoId);
              }
              if (_tmpKey != null) {
                _collectionVersiculo.put(_tmpKey, null);
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipversiculosAscomJoaovictorsilvestreVersuDataEntityVersiculo(_collectionVersiculo);
            final List<HistoricoComVersiculo> _result = new ArrayList<HistoricoComVersiculo>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final HistoricoComVersiculo _item;
              final Historico _tmpHistorico;
              if (!(_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfVersiculoId) && _cursor.isNull(_cursorIndexOfDataSorteio))) {
                _tmpHistorico = new Historico();
                final int _tmpId;
                _tmpId = _cursor.getInt(_cursorIndexOfId);
                _tmpHistorico.setId(_tmpId);
                final int _tmpVersiculoId;
                _tmpVersiculoId = _cursor.getInt(_cursorIndexOfVersiculoId);
                _tmpHistorico.setVersiculoId(_tmpVersiculoId);
                final long _tmpDataSorteio;
                _tmpDataSorteio = _cursor.getLong(_cursorIndexOfDataSorteio);
                _tmpHistorico.setDataSorteio(_tmpDataSorteio);
              } else {
                _tmpHistorico = null;
              }
              final Versiculo _tmpVersiculo;
              final Long _tmpKey_1;
              if (_cursor.isNull(_cursorIndexOfVersiculoId)) {
                _tmpKey_1 = null;
              } else {
                _tmpKey_1 = _cursor.getLong(_cursorIndexOfVersiculoId);
              }
              if (_tmpKey_1 != null) {
                _tmpVersiculo = _collectionVersiculo.get(_tmpKey_1);
              } else {
                _tmpVersiculo = null;
              }
              _item = new HistoricoComVersiculo();
              _item.historico = _tmpHistorico;
              _item.versiculo = _tmpVersiculo;
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
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
    final String _sql = "SELECT COUNT(*) FROM historico";
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

  private void __fetchRelationshipversiculosAscomJoaovictorsilvestreVersuDataEntityVersiculo(
      @NonNull final LongSparseArray<Versiculo> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, false, (map) -> {
        __fetchRelationshipversiculosAscomJoaovictorsilvestreVersuDataEntityVersiculo(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`texto`,`referencia`,`categoria`,`favorito` FROM `versiculos` WHERE `id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfTexto = 1;
      final int _cursorIndexOfReferencia = 2;
      final int _cursorIndexOfCategoria = 3;
      final int _cursorIndexOfFavorito = 4;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final Versiculo _item_1;
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
          _item_1 = new Versiculo(_tmpId,_tmpTexto,_tmpReferencia,_tmpCategoria);
          final boolean _tmpFavorito;
          final int _tmp;
          _tmp = _cursor.getInt(_cursorIndexOfFavorito);
          _tmpFavorito = _tmp != 0;
          _item_1.setFavorito(_tmpFavorito);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}

# VersU 📖✨
**Sua caixinha de promessas bíblicas digital**

> Projeto acadêmico — Programação para Dispositivos Móveis  
> Engenharia da Computação — UTFPR Câmpus Cornélio Procópio  
> Aluno: João Victor da Cruz Silvestre | RA: 2144263

---

## 📱 Sobre o App

O **VersU** digitaliza a experiência da "caixinha de promessas bíblicas".  
Funciona **100% offline** — sorteio aleatório, filtro por categoria, favoritos e histórico persistente.

---

## 🏗️ Arquitetura — MVVM

```
data/
├── entity/      → Versiculo.java  |  Historico.java
├── dao/         → VersiculoDao.java  |  HistoricoDao.java
├── database/    → AppDatabase.java  |  VersiculoData.java (150 versículos)
├── model/       → HistoricoComVersiculo.java
└── repository/  → VersiculoRepository.java

ui/
├── SplashActivity.java
├── main/        → MainActivity.java  |  MainViewModel.java
├── historico/   → HistoricoActivity.java  |  HistoricoViewModel.java  |  HistoricoAdapter.java
└── favoritos/   → FavoritosActivity.java  |  FavoritosViewModel.java  |  FavoritosAdapter.java
```

### Relacionamento entre entidades
```
Versiculo (1) ──── (N) Historico
  id (PK)               id (PK autoGenerate)
  texto                 versiculo_id (FK → CASCADE DELETE)
  referencia            data_sorteio (timestamp Unix ms)
  categoria
  favorito (boolean)
```

---

## 🛠️ Tecnologias

| Tecnologia        | Versão  | Uso                      |
|-------------------|---------|--------------------------|
| Java              | 17      | Linguagem principal      |
| Android Studio    | Hedgehog+ | IDE                    |
| Room (SQLite)     | 2.6.1   | Persistência local       |
| LiveData+ViewModel| 2.7.0   | Arquitetura MVVM         |
| Material Design 3 | 1.11.0  | Componentes visuais      |
| minSdk            | 26      | Android 8.0+             |

---

## 🚀 Como executar

```bash
git clone https://github.com/JnetSilvestre/projetoDispMoveis.git
# Abra a pasta VersU no Android Studio
# File > Open > VersU
# Aguarde o Gradle sincronizar (~2 min)
# Shift+F10 para rodar
```

---

## 📋 Status das Entregas

| # | Descrição                              | Status |
|---|----------------------------------------|--------|
| 1 | Proposta de Projeto (documento)        | ✅     |
| 2 | Banco Room + Tela principal + Sorteio  | ✅     |
| 3 | Histórico + Favoritos + Navegação      | ✅     |
| 4 | Dark Mode + Compartilhamento + Testes  | 🔄     |
| Final | APK + Documentação final          | ⏳     |

---

## 🐛 Correções aplicadas na Entrega 3

- **Crash após splash corrigido**: `ColorStateList` dos Chips criado programaticamente com `ContextCompat` (eliminado crash de `getColorStateList` com tema)
- **Bug `toggleFavorito` corrigido**: estado invertido uma única vez no ViewModel antes de persistir
- **Gradle Wrapper incluído**: `gradle-wrapper.properties` presente para build funcional
- **Ícones adaptive**: `mipmap-anydpi-v26` com foreground vetorial — sem necessidade de PNGs
- **Splash**: referência de logo usa `@mipmap/ic_launcher` — sempre disponível

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
└── main/
    ├── MainActivity.java
    └── MainViewModel.java
```

### Relacionamento entre entidades
```
Versiculo (1) ──── (N) Historico
  id (PK)               id (PK autoGenerate)
  texto                 versiculo_id (FK → CASCADE DELETE)
  referencia            data_sorteio (timestamp)
  categoria
  favorito
```

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 17 | Linguagem principal |
| Android Studio | Hedgehog+ | IDE |
| Room (SQLite) | 2.6.1 | Persistência local |
| LiveData + ViewModel | 2.7.0 | Arquitetura MVVM |
| Material Design 3 | 1.11.0 | Componentes visuais |
| minSdk | 26 (Android 8.0) | Compatibilidade |

---

## 🚀 Como executar

```bash
# 1. Clone o repositório
git clone https://github.com/JnetSilvestre/projetoDispMoveis.git

# 2. Abra no Android Studio
# File > Open > selecione a pasta VersU

# 3. Aguarde o Gradle sincronizar (~2 min na primeira vez)

# 4. Run > Run 'app'  (ou Shift+F10)
```

---

## 📋 Status das entregas

| # | Descrição | Status |
|---|---|---|
| 1 | Proposta de Projeto (documento) | ✅ |
| 2 | Banco Room + Tela principal + Sorteio MVP | ✅ |
| 3 | Histórico + Navegação + Favoritos | 🔄 |
| 4 | Dark Mode + Compartilhamento + Polimento | ⏳ |
| Final | Testes + Documentação + APK | ⏳ |

# VersU 📖✨
**Sua caixinha de promessas bíblicas digital**

> Projeto acadêmico — Disciplina de Programação para Dispositivos Móveis  
> Engenharia da Computação — UTFPR Câmpus Cornélio Procópio  
> Aluno: João Victor da Cruz Silvestre | RA: 2144263

---

## 📱 Sobre o App

O **VersU** é um aplicativo Android nativo que digitaliza a experiência da tradicional "caixinha de promessas bíblicas". O usuário pode sortear versículos bíblicos aleatoriamente, filtrar por categoria temática, salvar favoritos e consultar o histórico de sorteios — tudo **100% offline**.

---

## 🏗️ Arquitetura

```
MVVM (Model - View - ViewModel)
│
├── data/
│   ├── entity/       → Versiculo.java, Historico.java (entidades Room)
│   ├── dao/          → VersiculoDao.java, HistoricoDao.java
│   ├── database/     → AppDatabase.java, VersiculoData.java (150 versículos)
│   ├── model/        → HistoricoComVersiculo.java (POJO de relacionamento)
│   └── repository/   → VersiculoRepository.java
│
└── ui/
    ├── SplashActivity.java
    └── main/
        ├── MainActivity.java
        └── MainViewModel.java
```

### Entidades e Relacionamento

```
Versiculo (1) ──────── (N) Historico
  - id (PK)                - id (PK, autoGenerate)
  - texto                  - versiculo_id (FK)
  - referencia             - data_sorteio (timestamp)
  - categoria
  - favorito
```

---

## 🛠️ Tecnologias

| Tecnologia | Uso |
|---|---|
| **Java** | Linguagem principal |
| **Android Studio** | IDE de desenvolvimento |
| **Room (SQLite)** | Persistência de dados local |
| **LiveData** | Observação reativa de dados |
| **ViewModel** | Separação UI / lógica |
| **Material Design 3** | Componentes visuais |
| **API 24+** | Android 7.0 ou superior |

---

## 🎨 Design

- Paleta: **Azul Noite** (`#0D1A35`) + **Dourado Sagrado** (`#F5C842`)
- Animação de "virar carta" no sorteio de versículos
- Chips de filtro por categoria (Fé, Esperança, Proteção, Amor, Sabedoria, Força)
- Splash screen com logo e slogan

---

## 📦 Como executar

### Pré-requisitos
- Android Studio (versão **Hedgehog** ou superior)
- JDK 17
- Android SDK API 24+

### Passos
```bash
# 1. Clone o repositório
git clone https://github.com/JnetSilvestre/projetoDispMoveis.git

# 2. Abra no Android Studio
# File > Open > selecione a pasta do projeto

# 3. Aguarde a sincronização do Gradle

# 4. Execute no emulador ou dispositivo físico
# Run > Run 'app'
```

---

## 📋 Entregas

| Entrega | Descrição | Status |
|---|---|---|
| Entrega 1 | Proposta de Projeto (documento acadêmico) | ✅ Concluída |
| Entrega 2 | Banco de dados Room + Tela principal + Sorteio MVP | ✅ Concluída |
| Entrega 3 | Histórico + Navegação + Favoritos + Filtros | 🔄 Em andamento |
| Entrega 4 | Polimento visual + Dark Mode + Compartilhamento | ⏳ Pendente |
| Entrega Final | Testes + Documentação final + APK | ⏳ Pendente |

---

## 📄 Licença

Projeto acadêmico — sem fins comerciais.

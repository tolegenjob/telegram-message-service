# Telegram Message Service

Проект для привязки пользователей Telegram к их аккаунтам с помощью одноразового токена.

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Telegram Bot API](https://img.shields.io/badge/Telegram%20Bot-API-blue.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)

---

## ✨ Основные возможности

- 🔐 Привязка Telegram к аккаунту по одноразовому токену
- 🤖 Интеграция с Telegram Bot API
- 📦 REST API для генерации токенов, отправке и получения сообщений и работа с пользователем
- 🛡 Аутентификация на основе JWT
- ✅ Валидация входных данных
- 📝 Глобальная обработка исключений
- 🐘 Интеграция с PostgreSQL

---

## 🧩 Технологии

- Java 17
- Spring Boot 3.x
- Spring Security
- TelegramBots Java Library
- PostgreSQL
- JPA/Hibernate
- JWT
- Lombok
- Jakarta Validation
- Docker

---

## 🚀 Быстрый старт

### Предварительные требования

- Java 17
- Docker
- Telegram Bot Token (через [@BotFather](https://t.me/BotFather))

### Запуск

1. Клонируйте репозиторий:

```bash
git clone https://github.com/tolegenjob/telegram-message-service.git
cd telegram-message-service
```

2. Настройте переменные окружения в корне проекта:

```env
DB_NAME=your_db_name
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
TELEGRAM_BOT_USERNAME=your_telegram_bot_username
TELEGRAM_BOT_TOKEN=your_telegram_bot_token
JWT_SECRET=your_jwt_token
JWT_EXPIRATION_MS=your_jwt_expiration_ms
```

3. Запустите проект:

```bash
./gradlew clean build -x test
docker-compose up -d
```

---

## 📬 Использование Telegram бота

1. Отправка команды ``` /start ``` в телеграме боту 

2. Аутентифицированный пользователь отправляет:

```
POST /api/me/telegram-token
Authorization: Bearer <JWT>
```

3. Возвращается одноразовый токен

4. Пользователь отправляет токен боту

5. Бот сохраняет `chatId` в базу, привязывая к пользователю

6. Пользователь отправляет сообщение телеграм боту

---

## 📑 Примеры API

| Метод  | Endpoint                   | Назначение                         |
|--------|----------------------------|------------------------------------|
| POST   | `/api/auth/register`       | Регистрация пользователя           |
| POST   | `/api/auth/login`          | Аутентификация пользователя        |
| POST   | `/api/me/telegram-token`   | Генерация одноразового токена      |
| DELETE | `/api/me/telegram-token`   | Удаление привязки телеграм         |
| PUT    | `/api/me`                  | Обновление информации пользователя |
| GET    | `/api/me`                  | Получение информации о себе        |
| DELETE | `/api/me`                  | Удаление информации о себе         |
| POST   | `/api/messages`            | Отправка сообщения в телеграм бот  |
| GET    | `/api/messages`            | Получение сообщений пользователя   |

---

## 🔒 Безопасность

- JWT для авторизации
- Хеширование пароля через BCrypt
- Глобальная обработка ошибок

---

## 📫 Автор

[@tolegenjob](https://github.com/tolegenjob)

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

- Java 17+
- Docker
- Telegram Bot Token (через [@BotFather](https://t.me/BotFather))

### Запуск

1. Клонируйте репозиторий:

```bash
git clone https://github.com/tolegenjob/telegram-message-service.git
cd telegram-message-service
```

2. Запустите PostgreSQL:

```bash
docker-compose up -d
```

3. Настройте переменные окружения (или `application.yml`):

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/telegram_service
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
TELEGRAM_BOT_USERNAME=your_bot_name
TELEGRAM_BOT_TOKEN=your_bot_token
```

4. Запустите приложение:

```bash
./mvnw spring-boot:run
```

---

## 📬 Использование Telegram бота

1. Аутентифицированный пользователь отправляет:

```
POST /api/me/telegram-token
Authorization: Bearer <JWT>
```

2. Возвращается одноразовый токен

3. Пользователь отправляет токен боту

4. Бот сохраняет `chatId` в базу, привязывая к пользователю

---

## 📑 Примеры API

| Метод | Endpoint                   | Назначение                         |
|-------|----------------------------|------------------------------------|
| POST  | `/api/me/telegram-token`   | Генерация одноразового токена      |
| PUT   | `/api/me`                  | Обновление информации пользователя |
| GET   | `/api/me`                  | Получение информации о себе        |

---

## 🔒 Безопасность

- JWT для авторизации
- Хеширование пароля через BCrypt
- Глобальная обработка ошибок
- Ограничение доступа по ролям

---

## 🧪 Тестирование

Используется **Testcontainers** для запуска PostgreSQL в тестах.

```bash
./mvnw test
```

---

## 🤝 Контрибьюция

Pull Request'ы приветствуются. Открывайте issue для обсуждения больших изменений.

---

## 📄 Лицензия

Проект распространяется под лицензией MIT.

---

## 📫 Автор

[@tolegenjob](https://github.com/tolegenjob)

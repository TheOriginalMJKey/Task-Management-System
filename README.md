### Task Management System

#### Описание
Task Management System — это веб-приложение для управления задачами, которое позволяет пользователям создавать, обновлять, удалять и просматривать задачи. Проект включает в себя аутентификацию пользователей, роли и права доступа, уведомления о задачах, историю изменений задач, фильтрацию и поиск задач, а также экспорт и импорт задач в формате CSV.

#### 🛠 Технологии

- **Spring Boot** 🌱
  Основной фреймворк для создания веб-приложения.

- **Hibernate** 📚
  ORM для работы с базой данных.

- **Swagger** 📜
  Документация API.

- **JUnit** 🧪
  Тестирование.

- **H2** 🗄️
  База данных

- **Kafka** 📡
  Очередь сообщений для асинхронной обработки задач.

- **Redis** 🔒
  Кэширование

- **Hibernate Envers** 🕵️
  История изменений задач.

- **Apache Commons CSV** 📄
  Работа с CSV файлами.

#### Установка и запуск

1. **Установите зависимости:**
   - Убедитесь, что у вас установлены Kafka и Redis.
   - Скачайте и установите Kafka и Redis, следуя официальной документации.
   - Запустите Kafka и Redis серверы.

2. **Скачайте проект:**
   ```bash
   git clone https://github.com/TheOriginalMJKey/Task-Management-System.git
   cd Task-Management-System
   ```

3. **Соберите и запустите проект:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Документация Swagger:**
   После запуска приложения документация Swagger будет доступна по адресу [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

---

Удачи с вашим проектом! 🌟

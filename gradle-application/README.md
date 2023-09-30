Теги: `Docker` `Java` `Gradle`

# Gradle Application

## Задача

Разработчики проекта совсем не дружат с Docker, контейнерами и "всей этой вашей новомодной \[подставьте наименование по желанию\]", поэтому нам выпала задача упаковать это всё в образ

Что имеем: проект на Java 17 с системой сборки Gradle

Используемый порт: `9999`

Команда для тестирования:
```shell
curl -X GET http://localhost:9999
```

Ожидаемый ответ:
```shell
{"uuid": "рандомный UUID"}
```

Также есть Healthcheck:
```shell
curl -X GET http://localhost:9999/health
```

Ожидаемый ответ:
```shell
{"status": "UP"}
```

Приоритеты по использованию базового образа:
1. Scratch
2. Distroless (не `debug`)
3. Eclipse Temurin

Запуск не от `root`'а

<details>
<summary>Спойлеры</summary>

Вполне возможно, что первые два варианта использовать нецелесообразно по техническим соображениям: подготовьте обоснованный ответ на вопрос "почему"
</details>


## Инструкции по сборке проекта

Есть плагин [`application`](https://docs.gradle.org/current/userguide/application_plugin.html), который умеет собирать дистрибутив проекта

Делается это с помощью следующей команды:

```shell
./gradlew assemble
```

Файл `gradle-application-1.0.0.tar` и будет содержать дистрибутив для развёртывания

## Реализация

В качестве реализации CI/CD пайплайна и Docker Registry можно использовать любые, например (из облачных и бесплатных), [GitHub Actions](https://docs.github.com/en/actions) и [GitHub Packages](https://docs.github.com/packages)


Теги: `Docker` `Java` `Maven` `GraalVM` `Native`

# GraalVM Native Image (Static)

## Задача

Есть проект на чистой Java 17, система сборки &mdash; Maven, разработчики хотят попробовать [GraalVM Native Image](https://www.graalvm.org/jdk17/reference-manual/native-image/)

Для Maven есть [Maven Native Plugin](https://graalvm.github.io/native-build-tools/latest/maven-plugin.html), который и позволит скомпилировать приложение в бинарный исполняемый файл формата ELF

Разработчики хотят сделать так, чтобы выбор между сборкой "обычного приложения" и Native Image осуществлялся через [профили Maven](https://maven.apache.org/guides/introduction/introduction-to-profiles.html), определяемые в самом проекте

По умолчанию, активным должен быть профиль, который собирает "обычное приложение" (не Native Image)

### Сборка

Сборка проходит стандартным образом, через `mvn package`, никаких внешних зависимостей у проекта (в виде подключаемых при исполнении библиотек нет)

### API

Сервер запускается на порту, указанном через параметр `port` или переменную окружения `PORT` и реагирует на команду `UUID`, выдавая в ответ случайный [UUID](https://en.wikipedia.org/wiki/Universally_unique_identifier)

Как проверить:
1. Подключаемся с помощью `nc` (`netcat`) по нужному порту
2. Вводим `UUID⏎`, (где `⏎` &mdash; Enter для отправки данных)
3. Получаем в ответ: `b022e6b9-957a-4f22-b519-2fb57ca76caf` (пример)

<details>
<summary>Спойлеры: пример вызова nc для тестирования</summary>

```shell
$ nc -u localhost 9999
UUID
b022e6b9-957a-4f22-b519-2fb57ca76caf
```

</details>

### Что нужно сделать

1. Собрать всё с помощью Maven и Native Image (параллелить ничего не нужно, т.к. в приложении нет авто-тестов, проверок стиля кода и т.д.)
2. Упаковать всё в [`Scratch`](https://hub.docker.com/_/scratch)
3. Запускать приложение не от root

## Реализация

В качестве реализации CI/CD пайплайна и Docker Registry можно использовать любые, например (из облачных и бесплатных), [GitHub Actions](https://docs.github.com/en/actions) и [GitHub Packages](https://docs.github.com/packages)

## Полезные ссылки

1. [GraalVM Native Image](https://www.graalvm.org/jdk17/reference-manual/native-image/)
2. [Netcat MAN](https://linux.die.net/man/1/nc)

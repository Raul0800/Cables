# Cables
Вебсервис, который делает поиск по базе

1-я итерация
Поиск container по name, где name = искомая строка, либо name содержит искомую строку

Критерии

1. Предсказуемость работы (помогут скрипты деплоя; тесты: unit, интеграционные, типизация; README)
2. Изменяемость (тесты: unit, интеграционные, типизация)

Стек:
¬! docker
¬! sbt
¬! postgres

¬! http4s
¬! doobie
¬! circa
¬! cats-effect (IO)
¬ pureconfig(?)
¬ testcontainers(!)

jdbc:postgresql://localhost:5432/postgres
PostgreSQL JDBC Driver

WORK
docker run --name pg -v "$(PWD)/:/docker-entrypoint-initdb.d/" -p 5432:5432 postgres
docker exec -it pg bash
docker exec -it pg psql -U postgres

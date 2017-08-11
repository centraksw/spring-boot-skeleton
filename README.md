This is a skeleton sprig boot project that:
- uses liquibase for creating a database
- uses spring jpa data for persistence layer
- shows Create/Replace/Update/Delete REST controller and service layer

Note:
This currently uses h2 database so the example is self contained. This can be switched out to another database easily (e.g. mysql) at a later time

Usage:
For developers, this can be launched via ./gradlew bootRun. For users, simply run the jar (./spring-boot-skeleton.jar)

Create two entries:
curl -X POST http://127.0.0.1:8966/foobar -H 'content-type: application/json' -d '{"value": 1}'
curl -X POST http://127.0.0.1:8966/foobar -H 'content-type: application/json' -d '{"value": 2}'

Query all:
curl http://127.0.0.1:8966/foobar

Query the first one:
curl http://127.0.0.1:8966/foobar/1

Modify the first one:
curl -X POST http://127.0.0.1:8966/foobar -H 'content-type: application/json' -d '{"id":1,"value": 99}'

Delete the first one:
curl -X DELETE http://127.0.0.1:8966/foobar/1

To distribute: run './gradlew build' to build, then save and distribute build/libs/spring-boot-skeleton.jar
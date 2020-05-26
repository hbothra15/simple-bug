![CI](https://github.com/hbothra15/simple-bug/workflows/CI/badge.svg)
# SIMPLE-BUG
This Project is a pet project which is insipred by simp[le-bug-tracker as mentioned in reference

## Set ups
- To Set up MySQL Docker image
```bash
docker run -d -p 3306:3306 --name=simple-bug-db --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=simple-bug" mysql
```
- To Setup MySQL DB via docker compose
```bash
docker-compose -f docker/docker-mysql.yml up --remove-orphans
```
- To Setup Sonar via docker compose
```bash
docker-compose -f docker/docker-sonar.yml up --remove-orphans
```

- To backup sonar report run below command from project home directory
```bash
docker exec docker_db_1 /bin/bash -c "export PGPASSWORD=sonar && /usr/bin/pg_dump -U sonar sonar" > sonar-backup\backup.sql
```

- To restore sonar report run below command from project home directory
```bash
# For Windows
type sonar-backup\backup.sql | docker exec -i docker_db_1 /bin/bash -c "export PGPASSWORD=sonar && /usr/bin/psql -U sonar sonar"
# For Linux
cat sonar-backup\backup.sql | docker exec -i docker_db_1 /bin/bash -c "export PGPASSWORD=sonar && /usr/bin/psql -U sonar sonar"
```

```bash
# To run Sonar scan make sure to get update from backup.sql and generate new one post scan 
mvn clean verify -P sonar
```
## References
- https://gregorbowie.wordpress.com/category/pet-projects/simple-bug-tracker/ Which inspired me to build this app
- https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/ For Implementing token based Spring security
- https://www.devglan.com/spring-security/spring-oauth2-role-based-authorization for enabling Secured annotation for role-based security
- https://vladmihalcea.com/ which I referred a lot for all my JPA related Queries.
- https://www.baeldung.com/ Almost for Every setup in this entire project i did end up here
- https://www.baeldung.com/spring-security-method-security for JUnit around Spring Security
- https://github.com/SonarSource/docker-sonarqube/blob/master/example-compose-files/sq-with-postgres/docker-compose.yml To Setup sonar for local machine to have better code quality
- https://devopsheaven.com/postgresql/pg_dump/databases/docker/backup/2017/09/10/backup-postgresql-database-using-docker.html To have sonar report backup which can be used whenever we bring back the Sonar compose up and run maven report
- https://devopsheaven.com/postgresql/psql/databases/docker/backup/restore/2017/09/10/restore-postgresql-database-using-docker.html To restore sonar report post bringing docker compose up and use the same data
- https://vladmihalcea.com/the-best-way-to-map-the-discriminatorcolumn-with-jpa-and-hibernate/ For using lookup table

## Release Note
* 2020.05
    - Change in the version pattern, will use YYYY.MM and adding .DD for patch release
    - Leverage the new Secured annotation
    - Updating JUnit for UserController 
* 0.0.1-SNAPSHOT
    - Initial release

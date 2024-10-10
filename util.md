# Sonar
## container
```
docker run --name=sonar -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p=9000:9000 sonarqube:9.9.0-community -d
```

## token
```
sqp_53e428835021c17700a139d70deaa4526446a390
```

## Local
```
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=gestao_vagas \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=sqp_53e428835021c17700a139d70deaa4526446a390
```

#DATABASE_URL= jdbc:postgresql://localhost:5434/gestao_vagas



# Steps

to run the project, in folder of the project do:

1. `docker-compose -f src/main/docker/infra.yml up`
2. `curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @src/main/docker/postgresql-source.json`
3. Set your `Github Personal Access Token` in the environment variable `GITHUB_PERSONAL_ACCESS_TOKEN`.
4. then run the web application using `./gradlew bootRun`
5. Then you can curl the server on `localhost:8080`. The create contact resource is accepting `POST` not `put`. 


# Highlights

Spring boot is the main skeleton of the app.

Right after saving each contact, A message will be created by `debezium` and will be stored in the `kafka`, then a kafka streams sink (consumer) in the application will take the message related to that and via Github Api and then the repos will be fetched and persisted into another table in the postgresql. this Step must be Idempotent and it is.

I've Implemented search mechanism in two ways, one is Spring Data `Example` , which is used and the other is with `QueryDSL`. the querydsl is not used as the main search facility.

I've Used `TestContainers` as a tool for loading infrastructure in container format before each test. It will do the load and cleanups itself.

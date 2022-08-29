systemctl start docker

# Avoid using sudo with docker
sudo groupadd docker
sudo usermod -aG docker $USER
newgrp docker

docker network create visits-postgresql
docker network ls

sudo mkdir -p /var/lib/postgresql/data

docker container run --name postgresqldb --network visits-postgresql -e POSTGRES_USER=vanliedekerkea -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=vanliedekerkea -p 5432:5432 -v /data:/var/lib/postgresql/data:Z -d postgres:12.12-bullseye

If it's the first time you run the postgres container then you will need to create the database called "vanliedekerkea". You can do this by runing pgadmin4, connecting to postgres and using the wizard for creating a database.

docker container logs -f postgresqldb
docker container inspect postgresqldb ,for checking the IP address, so that you can connect with the pgadmin to it

docker run --name pgadmin --network visits-postgresql -p 8000:80 -d -e 'PGADMIN_DEFAULT_EMAIL=user@domain.com' -e 'PGADMIN_DEFAULT_PASSWORD=SuperSecret' dpage/pgadmin4

docker container exec -it postgresqldb bash
Enter as postgres user, so that you can run the postgress command: su postgres
For interfacing with PostgreSQL terminal: psql
For listing up the schemas: \l

Running the java app on docker: 
mvn clean install -DskipTests
docker image build -t visits .

docker container run --network visits-postgresql --name visits-container -p 8080:8080 -d visits


# How to run this project
You can run this project by executing the following:
sh compileAndRun.sh






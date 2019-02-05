# Build
mvn clean package && docker build -t nl.fhict.jea/basic-jee .

# RUN

docker rm -f basic-jee || true && docker run -d -p 8080:8080 -p 4848:4848 --name basic-jee nl.fhict.jea/basic-jee 
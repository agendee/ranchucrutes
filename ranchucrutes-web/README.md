#Ranchucrutes web camada web do ranchucrutes
--------------------------------------------------------------------------------
Para deploy execute
mvn clean install -Phml1

Para deploy em desenv
mvn clean install -Djetty.port=9090 jetty:run


#Ranchucrutes jobs
--------------------------------------------------------------------------------
Para deploy execute
mvn clean install -Pjobs1

Para deploy em desenv
mvn clean install -Djetty.port=9292 jetty:run


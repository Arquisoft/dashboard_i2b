[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2f5e9b234d9b4cbd8669629c299990ad)](https://www.codacy.com/app/jelabra/dashboard_i2b?utm_source=github.com&utm_medium=referral&utm_content=Arquisoft/dashboard_i2b&utm_campaign=badger)
[![Build Status](https://travis-ci.org/Arquisoft/dashboard_i2b.svg?branch=master)](https://travis-ci.org/Arquisoft/dashboard_i2b)
[![codecov](https://codecov.io/gh/Arquisoft/dashboard_i2b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/dashboard_i2b)


# dashboard_i2b

[![Join the chat at https://gitter.im/Arquisoft/dashboard_i2b](https://badges.gitter.im/Arquisoft/dashboard_i2b.svg)](https://gitter.im/Arquisoft/dashboard_i2b?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
<p style="text-align: justify;">
This is the repository for the Dashboard project of the subject Software Architecture of the group dashboard_i2b.
</p>

# Kafka
For this project you need a local Kafka server running. You may download Kafka [here](https://www.apache.org/dyn/closer.cgi?path=/kafka/0.10.2.0/kafka_2.12-0.10.2.0.tgz)
For Kafka to work, you need to run first the Zookeeper server and then the Kafka server, according to the instructions [here](https://kafka.apache.org/quickstart).

In the wiki page you can find a gist with a .bat file which you have to pass the Kafka folder to, and it will run both servers.

## Load/Stress tests
<p style="text-align: justify;">
In order to test the properly behaviour of the application under certain conditions, and as it was recommended in the laboratory course, we have used Gatling (http://gatling.io/). On that purpose we have created a load test whith the code available on the directory gatling/LoadLanding.scala. That situation was tested in a machine with the following specifications:
</p>
<p align="center">
  Intel Core i5-6400 @2.70GHz<br>
  16.00 GB RAM DDR4
</p>
<p style="text-align: justify;">
This audition results are available in the same folder as the test source code and can be visualied downloading the project and deploying the index.html file of each one. Due to the results obtained and considering the specifications of the machine, we can ensure that at this moment the application remains very scalable and performs really good under heavy stress situations.
</p>

# Authors

- Herminio García González (@herminiogg)
- Jose Emilio Labra Gayo (@labra)
- Jorge Zapatero Sánchez (@JorgeZapa)
- Damián Rubio Cuervo (@DamianRubio)
- Antonio Nicolás Rivero Gómez (@Lan5432)

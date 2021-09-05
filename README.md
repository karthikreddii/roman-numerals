# Roman Numerals

This is a simple project to convert integers from 1-3999 to Roman Numbers
To understand more on what are Roman Numbers, visit this wikipedia page `https://en.wikipedia.org/wiki/Roman_numerals`

## Prerequisites

- Java 8 (check the version by running this command `java -version`)
- Maven 3.3+ (check the version by running this command `mvn -v`)
- Docker

## Getting Started

-- After cloning the project, navigate to your project location(for example /Users/username/workspace/roman-numerals) 
   and run this command `make run`. This will start the docker container and will deploy your code.
-- If you make any changes to your code and want to deploy and test it, run this command `make deploy`
   or you can deploy with this command too `mvn clean install -P autoInstallBundle`
-- If you want to run just the test case, run this command `make test` or 
   you can test with this command too `mvn clean test`

## Other Useful commands

-- To stop the docker instance run this command `docker stop roman-numerals`
-- To start the docker instance run this command `docker start roman-numerals`

## ssh and logging

-- To ssh into docker container using this command `docker exec -it roman-numerals sh`
-- To tail the logs after ssh, use this command `tail -f  sling/logs/error.log`

## How to use

- Navigate to `http://localhost:8080/romannumeral?query={integer}`, replace `{integer}` with any number between 1-3999.
- This will give you Json output with input and output. Here is an example
  `http://localhost:8080/romannumeral?query=77` will return
  `{
  input: "77",
  output: "LXXVII"
  }`

## Alternative ways

-- You can build the docker with this command `docker build -t romannumerals:v0.1.0 .`
-- Run this command `docker run -p 8080:8080 -it romannumerals:v0.1.0` to run the instance on port 8080

-- You can also download the latest sling jar from apache website `https://sling.apache.org/downloads.cgi`
-- Run this command from the downloaded location `java -jar org.apache.sling.starter-11.jar start`
-- Once the sling application is up on port 8080, deploy this bundle by using `mvn clean install -P autoInstallBundle`
-- Once the bundle is deployed you can test by hitting this request `http://localhost:8080/romannumeral?query={integer}`

## Known Problems

- Starting and building the project with docker build, sometimes this roman-numerals bundle may not start.
- The quick workaround is to navigate to web console(http://localhost:8080/system/console/bundles) 
  and manually start the bundle.
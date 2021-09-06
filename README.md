# Roman Numerals

This is a simple project to convert integers from 1-3999 to Roman Numbers
To understand more on what are Roman Numbers, visit this wikipedia page `https://en.wikipedia.org/wiki/Roman_numerals`

## Prerequisites

- Java 8 (check the version by running this command `java -version`)
- Maven 3.3+ (check the version by running this command `mvn -v`)
- Docker

## Getting Started

- After cloning the project, navigate to your project location(for example /Users/username/workspace/roman-numerals) 
   and run command `make run`. This will start the docker container and will deploy your code.
- If you make any changes to your code and want to deploy and test it, run command `make deploy`
   or you can deploy by using this command too `mvn clean install -P autoInstallBundle`
- If you want to run just the test cases, run command `make test` or 
   you can also test by using this command too `mvn clean test`

## Other Useful commands

- To stop the docker instance run command `docker stop roman-numerals`
- To start the docker instance run command `docker start roman-numerals`

## ssh and logging

- To ssh into docker container, use command `docker exec -it roman-numerals sh`
- To tail the logs after ssh, use command `tail -f  sling/logs/error.log`

## How to use

- Navigate to `http://localhost:8080/romannumeral?query={integer}`, replace `{integer}` with any number between 1-3999.
- This will give you Json output with input and output. Here is an example
  `http://localhost:8080/romannumeral?query=77` will return
  `{
  input: "77",
  output: "LXXVII"
  }`
  
## Metrics

- There are two simple metrics added to this project
- Navigate to metrics WebConsole (http://localhost:8080/system/console/slingmetrics) and search for 
  `romanNumeral-pageHits` metric to see the number of page hits. 
  - Search for `romanNumeral-MeterInfo` metric to find more information on rate of requests per second.
  - Search for `romanNumeral-intInRange` metric to see the number of hits with correct input range(1-3999) hits.

## Alternative ways

- You can build the docker with the command `docker build -t romannumerals:v0.1.0 .`
- Run the command `docker run -p 8080:8080 -it romannumerals:v0.1.0` to run the instance on port 8080

- You can also download the latest sling jar from apache website `https://sling.apache.org/downloads.cgi`
- Run this command from the downloaded location `java -jar org.apache.sling.starter-11.jar start`
- By this point the sling application should be up on port 8080, deploy this bundle by using `mvn clean install -P autoInstallBundle`
- Now your bundle is deployed, and you can test the functionality by hitting this request `http://localhost:8080/romannumeral?query={integer}`

## Known Problems

- With starting and building the project with DockerFile, sometimes this roman-numerals bundle may not start.
- The quick workaround is to navigate to web console(http://localhost:8080/system/console/bundles) 
  and manually start the bundle.
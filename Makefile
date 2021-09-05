run:
	docker run --name roman-numerals -d -p 8080:8080 apache/sling:11
	mvn clean install -P autoInstallBundle
deploy:
	mvn clean install -P autoInstallBundle
test:
	mvn clean test

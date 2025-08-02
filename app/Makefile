.DEFAULT_GOAL := build-run
.PHONY: build

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean installDist

run-dist:
	./build/install/app/bin/app $(ARGS)

run:
	./gradlew run

gendiff:
	java -jar build/libs/app-1.0-SNAPSHOT-all.jar

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build

# Detect OS (Windows or Unix)
ifeq ($(OS),Windows_NT)
    GRADLE_CMD = gradlew.bat
    APP_PATH = build\install\app\bin\app.bat
    SLASH = \\
else
    GRADLE_CMD = ./gradlew
    APP_PATH = ./build/install/app/bin/app
    SLASH = /
endif

.DEFAULT_GOAL := build-run
.PHONY: build

setup:
	$(GRADLE_CMD) wrapper --gradle-version 8.5

clean:
	$(GRADLE_CMD) clean

build:
	$(GRADLE_CMD) clean build

install:
	$(GRADLE_CMD) clean installDist

run-dist:
	$(APP_PATH) $(ARGS)

run:
	$(GRADLE_CMD) run

gendiff:
	java -jar build/libs/app-1.0-SNAPSHOT-all.jar

test:
	$(GRADLE_CMD) test

report:
	$(GRADLE_CMD) jacocoTestReport

lint:
	$(GRADLE_CMD) checkstyleMain

check-deps:
	$(GRADLE_CMD) dependencyUpdates -Drevision=release

build-run: build run
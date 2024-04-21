#!/bin/sh

export TERM=xterm

# Start the application with remote debugging enabled and run in the background
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" &

# Use nodemon to watch the src directory for changes in Java or XML files
nodemon --watch src -e java,xml --exec "mvn compile"

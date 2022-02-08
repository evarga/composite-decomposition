#!/usr/bin/env bash

mvn clean compile exec:java -Dexec.mainClass=App -Dexec.args="$*"

#!/usr/bin/env bash

mvn exec:java -Dexec.mainClass=App -Dexec.args="$*"

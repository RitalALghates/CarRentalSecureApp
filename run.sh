#!/usr/bin/env sh
# Compile and run the Secure Car Rental Recommendation System (macOS / Linux).
set -e
mkdir -p out
find src -name '*.java' > sources.txt
javac -encoding UTF-8 -d out @sources.txt
rm sources.txt
java -cp out com.cccy225.carrental.app.Main
@echo off
rem Compile and run the Secure Car Rental Recommendation System (Windows).
if not exist out mkdir out
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d out @sources.txt || exit /b 1
del sources.txt
java -cp out com.cccy225.carrental.app.Main

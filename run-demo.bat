@echo off
cd /d "%~dp0"
echo [FastAIReasoner] Running Demo...
cd examples\Demo
call mvn compile exec:java -Dexec.mainClass=fastaireasoner.Demo
cd ..\..

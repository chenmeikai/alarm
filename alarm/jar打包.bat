@echo off
cd /d %~sdp0
call mvn assembly:assembly -Dmaven.test.skip=true -f ./pom.xml 
pause

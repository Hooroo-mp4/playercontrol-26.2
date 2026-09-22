@echo off
setlocal

set "APP_HOME=%~dp0"
set "WRAPPER_SOURCE=%APP_HOME%gradle\wrapper\gradle-wrapper.jar"
set "WRAPPER_TEMP=%TEMP%\playercontrol-gradle-wrapper.jar"
set "WRAPPER_PROPERTIES_SOURCE=%APP_HOME%gradle\wrapper\gradle-wrapper.properties"
set "WRAPPER_PROPERTIES_TEMP=%TEMP%\playercontrol-gradle-wrapper.properties"

if not exist "%WRAPPER_PROPERTIES_SOURCE%" (
    echo ERROR: Gradle wrapper properties file was not found:
    echo %WRAPPER_PROPERTIES_SOURCE%
    exit /b 1
)

if not exist "%WRAPPER_SOURCE%" (
    echo ERROR: Gradle wrapper JAR was not found:
    echo %WRAPPER_SOURCE%
    exit /b 1
)

rem Copy the wrapper JAR to TEMP before launching it. This avoids Windows
rem path/ZIP extraction/Zone.Identifier issues that can make Java reject the
rem original extracted JAR even though it is present.
copy /Y /B "%WRAPPER_SOURCE%" "%WRAPPER_TEMP%" >NUL
copy /Y /B "%WRAPPER_PROPERTIES_SOURCE%" "%WRAPPER_PROPERTIES_TEMP%" >NUL
if errorlevel 1 (
    echo ERROR: Could not copy the Gradle wrapper JAR to TEMP.
    exit /b 1
)

set "JAVA_EXE=java.exe"
if defined JAVA_HOME set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"

"%JAVA_EXE%" -version >NUL 2>&1
if errorlevel 1 (
    echo ERROR: Java could not be found. Make sure Java 25 is installed and JAVA_HOME is correct.
    exit /b 1
)

"%JAVA_EXE%" -Xmx64m -Xms64m -Dorg.gradle.appname=gradlew -jar "%WRAPPER_TEMP%" %*
set "EXIT_CODE=%ERRORLEVEL%"

del /Q "%WRAPPER_TEMP%" >NUL 2>&1
del /Q "%WRAPPER_PROPERTIES_TEMP%" >NUL 2>&1
endlocal & exit /b %EXIT_CODE%

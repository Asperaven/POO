@echo off
echo Compiling Java project with SQLite dependencies...
if not exist bin mkdir bin
javac -d bin -cp "lib/*" -sourcepath src src/Main.java
if %errorlevel% equ 0 (
    echo Compilation successful! Starting application...
    java -cp "bin;lib/sqlite-jdbc-3.53.1.0.jar" Main
) else (
    echo Compilation failed!
    pause
)

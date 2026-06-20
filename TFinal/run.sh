#!/bin/bash
echo "Compiling Java project with SQLite dependencies..."
mkdir -p bin
javac -d bin -cp "lib/*" -sourcepath src src/Main.java
if [ $? -eq 0 ]; then
    echo "Compilation successful! Starting application..."
    java -cp "bin:lib/sqlite-jdbc-3.53.1.0.jar" Main
else
    echo "Compilation failed!"
fi

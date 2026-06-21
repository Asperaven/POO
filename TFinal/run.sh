#!/bin/bash
echo "Compiling Java project with SQLite dependencies..."
rm -rf bin
mkdir -p bin
javac -cp "lib/sqlite-jdbc-3.53.1.0.jar" -d bin src/*.java src/TFinalGUI/*.java src/TFinalClasses/*.java src/TFinalDados/*.java src/TFinalExcecoes/*.java
if [ $? -eq 0 ]; then
    echo "Compilation successful! Starting application..."
    java --enable-native-access=ALL-UNNAMED -cp "bin:lib/sqlite-jdbc-3.53.1.0.jar" Main
else
    echo "Compilation failed!"
fi

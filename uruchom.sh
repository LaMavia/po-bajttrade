#!/bin/bash

# Import funkcji
cd "$(dirname "$0")" || exit 1
source ./cli/kolory.sh

# Znajdowanie ścieżek
sciezka_target='./target'
sciezka_jar=$(find "$sciezka_target" -name "*.jar")
klasa_main=$(find src/main/java/ -name "App.java" | sed 's/src\/main\/java\///' | sed 's/\.java//' | sed 's/\//./g')

if [ -z "$sciezka_jar" ]; then
    kprint "$BLAD" "Nie znaleziono pliku .jar w $(realpath "$sciezka_target")"
    exit 1
fi

# Wykonywanie

if java -cp "$sciezka_jar" "$klasa_main"; then
    kprint "$UDANE" 'Wykonanie zakończone poprawnie'
else
    kprint "$BLAD" "Błąd wykoniania: ${?}. Szczegóły powyżej"
fi
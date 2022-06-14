#!/bin/bash

# ustawienie cwd
oryginalne_wd=$(pwd)
skrypt_wd="$(dirname "$0")"

cd "$skrypt_wd" || exit 1

# Import funkcji
source ./cli/kolory.sh
source ./cli/sciezki.sh

waliduj_parametry () {
    if [ $# -lt 2 ]; then
        blad_print "Zła ilość argumentów: oczekiwano \"sciezka_wejsciowa sciezka_wyjsciowa\"; otrzymano (${#}) \"$*\""
        exit 1
    fi
    
    wejscie="$1"
    wyjscie="$2"
    
    # zmień ścieżkę na oryginalną
    cd "$oryginalne_wd" || exit 1

    # sprawdź, czy istnieje wejście
    if [ ! -f "$wejscie" ]; then
        blad_print "Plik wejściowy \"${wejscie}\" nie istnieje."
        exit 1
    else 
        info_print "Plik wejściowy istnieje 👍"
    fi
    
    # stwórz wyjście, jeśli nie istnieje
    if (mkdir -p "$(dirname "$wyjscie")" && touch "$wyjscie"); then 
        info_print "Plik wyjściowy pomyślnie utworzony 👍"
    else
        blad_print "Błąd tworzenia ścieżki wyjściowej \"${wyjscie}\""
    fi

    # wróć do skryptowego wd
    cd "$skrypt_wd" || exit 1
}


# parametry wejściowe
waliduj_parametry "$@"
wejscie=$(pelna_sciezka "$oryginalne_wd" "$skrypt_wd" "$1")
wyjscie=$(pelna_sciezka "$oryginalne_wd" "$skrypt_wd" "$2")

# znajdowanie ścieżek
sciezka_target='./target'
sciezka_jar=$(find "$sciezka_target" -name "*.jar")
klasa_main=$(find src/main/java/ -name "App.java" | sed 's/src\/main\/java\///' | sed 's/\.java//' | sed 's/\//./g')

if [ -z "$sciezka_jar" ]; then
    blad_print "Nie znaleziono pliku .jar w \"$(realpath "$sciezka_target")\""
    exit 1
fi

# wykonywanie
kod_wyjscia=0

if java -cp "$sciezka_jar" "$klasa_main" "$wejscie" "$wyjscie"; then
    ok_print 'Wykonanie zakończone pomyślnie'
else
    kod_wyjscia="$?"
    blad_print "Błąd wykoniania: ${kod_wyjscia}. Szczegóły powyżej"
fi

# propagowanie kodu wyjścia
exit "$kod_wyjscia"

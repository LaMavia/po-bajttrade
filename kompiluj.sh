#!/bin/bash

# ustawienie cwd
cd "$(dirname "$0")" || exit 1

# import funkcji
source ./cli/kolory.sh


# kompilacja
kod_wyjscia=0

info_print 'Kompilowanie projektu...'

if mvn package; then
    ok_print 'Kompilacja udana.'
else
    kod_wyjscia="$?"
    blad_print "Błąd kompilacji: ${kod_wyjscia}. Szczegóły powyżej."
fi

# propagowanie kodu wyjścia
exit "$kod_wyjscia"

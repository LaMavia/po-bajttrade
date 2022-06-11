#!/bin/bash

# Import funkcji
cd "$(dirname "$0")" || exit 1
source ./cli/kolory.sh

mvn package

if [ $? -eq 0 ]; then
    kprint "$UDANE" 'Kompilacja udana'
else
    kprint "$BLAD" "Błąd kompilacji; kod wyjścia: ${?}"
fi

exit $?
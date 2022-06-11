#!/bin/bash

pelna_sciezka () {
    local sciezka_wd="$1"
    local skrypt_wd="$2"
    local scizeka="$3"
    local wyjscie
    
    cd "$sciezka_wd" || exit 1

    wyjscie=$(realpath "$scizeka")

    cd "$skrypt_wd" || exit 1
    
    echo "$wyjscie"
}
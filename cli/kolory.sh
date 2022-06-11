#!/bin/bash

kprint () {
  local tekst="$1"
  shift
  local patern=("$@")

  local typ=${patern[0]}
  local kolor=${patern[1]}

  printf '[\e[%sm%s\e[0m] %s\n' "$kolor" "$typ" "$tekst"
}

UDANE=('OK' '1;32')
BLAD=('BŁĄD' '1;31')
INFO=('INFO' '1;34')

ok_print () {
  kprint "$1" "${UDANE[@]}"
}

blad_print () {
  kprint "$1" "${BLAD[@]}"
}

info_print () {
  kprint "$1" "${INFO[@]}"
}
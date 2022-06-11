#!/bin/bash

kprint () {
  local kolor=$1
  local tekst=$2

  printf '\e[%sm%s\e[0m\n' "$kolor" "$tekst"
}

UDANE='1;32'
BLAD='1;31'
INFO='1;36'

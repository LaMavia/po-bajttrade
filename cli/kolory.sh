#!/bin/bash

kprint () {
  local kolor=$1
  local tekst=$2

  printf '\e[%sm%s\e[0m\n' "$kolor" "$tekst"
}

export UDANE='1;32'
export BLAD='1;31'
export INFO='1;36'

package pl.edu.mimuw.bajtTrade.symulacja.zadoby.poziomowe;

import java.util.Map;
import java.util.Map.Entry;

import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbPoziomowy;

public class ZasóbUbrań extends ZasóbPoziomowy {

  public ZasóbUbrań(Agent właściciel_) {
    super(właściciel_, TypyZasobów.Ubrania, (poziom) -> poziom * poziom);
  }

  @Override
  public void zużyj(double ilość, Robotnik robotnik, Map<TypyZasobów, Integer> premie,
      KonfiguracjaSymulacji konfiguracja) {
    if (ilość > this.ilość()) {
      for (Entry<TypyZasobów, Integer> wejście : premie.entrySet()) {
        premie.compute(wejście.getKey(), (k_, p) -> p - konfiguracja.karaZaBrakUbrań());
      }
    }

    super.zużyj(ilość, robotnik, premie, konfiguracja);
  }

}

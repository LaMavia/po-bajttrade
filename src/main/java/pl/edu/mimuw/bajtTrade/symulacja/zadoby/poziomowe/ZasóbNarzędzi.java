package pl.edu.mimuw.bajtTrade.symulacja.zadoby.poziomowe;

import java.util.Map;
import java.util.Map.Entry;

import pl.edu.mimuw.bajtTrade.struktury.Para;
import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbPoziomowy;

public class ZasóbNarzędzi extends ZasóbPoziomowy {

  public ZasóbNarzędzi(Agent właściciel_) {
    super(właściciel_, TypyZasobów.Narzędzia, (p_) -> 1);
  }

  @Override
  public void zużyj(Robotnik robotnik, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja) {

    int sumaPremii = 0;

    for (Entry<Para<Integer, Integer>, Integer> wejście : przedmioty.entrySet()) {
      sumaPremii += wejście.getKey().q() * wejście.getValue();
    }

    final int premia = sumaPremii;

    for (Entry<TypyZasobów, Integer> wejście : premie.entrySet()) {
      premie.compute(wejście.getKey(), (k_, p) -> p + premia);
    }

    super.zużyj(robotnik, premie, konfiguracja);
  }

}

package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.produkcji;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaProdukcji;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class Perspektywiczny implements StrategiaProdukcji {
  private int historiaPerspektywy;

  public Perspektywiczny(int historiaPerspektywy) {
    this.historiaPerspektywy = historiaPerspektywy;
  }

  @Override
  public TypyZasobów coProdukować(Robotnik robotnik, int numerDnia, Historia historia,
      Map<TypyZasobów, Integer> premie) {
    Map<TypyZasobów, Double> wzrosty = new HashMap<>();

    // Ustal wzrosty cen
    for (TypyZasobów typ : premie.keySet()) {
      int deltaDni = Math.min(historiaPerspektywy, numerDnia - 1);

      wzrosty.put(typ, (historia.dajDzień(numerDnia - 1).dajCenęŚrednią(typ)
          - historia.dajDzień(numerDnia - historiaPerspektywy).dajCenęŚrednią(typ)) / deltaDni);

    }

    // Wybierz najlepszą
    TypyZasobów proponowanyTyp = TypyZasobów.Jedzenie;
    Double proponowanyWzrost = wzrosty.get(TypyZasobów.Jedzenie);

    for (Entry<TypyZasobów, Double> wejście : wzrosty.entrySet()) {
      if (proponowanyWzrost < wejście.getValue()
          || (proponowanyWzrost == wejście.getValue() && proponowanyTyp.compareTo(wejście.getKey()) < 0)) {
        proponowanyTyp = wejście.getKey();
        proponowanyWzrost = wejście.getValue();
      }
    }

    return proponowanyTyp;
  }

}

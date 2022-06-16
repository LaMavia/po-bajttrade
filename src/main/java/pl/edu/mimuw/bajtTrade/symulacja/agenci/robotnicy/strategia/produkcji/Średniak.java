package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.produkcji;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaProdukcji;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.OpisDnia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class Średniak implements StrategiaProdukcji {

  private int historiaŚredniejProdukcji;

  public Średniak(int historiaŚredniejProdukcji) {
    this.historiaŚredniejProdukcji = historiaŚredniejProdukcji;
  }

  @Override
  public TypyZasobów coProdukować(Robotnik robotnik, int numerDnia, Historia historia,
      Map<TypyZasobów, Integer> premie) {
    Map<TypyZasobów, Double> maksymalneCenyŚrednie = new HashMap<>();

    // Ustal maksymalne ceny średnie
    for (int di = 1; di <= historiaŚredniejProdukcji && numerDnia - di >= 0; di++) {
      OpisDnia opisDnia = historia.dajDzień(numerDnia - di);

      for (TypyZasobów typ : premie.keySet()) {
        maksymalneCenyŚrednie.compute(typ,
            (k_, cena) -> Math.max(cena == null ? 0. : cena, opisDnia.dajCenęŚrednią(typ)));
      }
    }

    // Uzupełnij dniem zerowym, jeśli `historiaŚredniejProdukcji = 0`
    OpisDnia dzień0 = historia.dajDzień(0);

    for (TypyZasobów typ : premie.keySet()) {
      maksymalneCenyŚrednie.putIfAbsent(typ, dzień0.dajCenęŚrednią(typ));
    }

    // Wybierz najlepszą
    TypyZasobów proponowanyTyp = TypyZasobów.Jedzenie;
    Double proponowanaCena = maksymalneCenyŚrednie.get(TypyZasobów.Jedzenie);

    for (Entry<TypyZasobów, Double> wejście : maksymalneCenyŚrednie.entrySet()) {
      if (proponowanaCena < wejście.getValue()
          || (proponowanaCena == wejście.getValue() && proponowanyTyp.compareTo(wejście.getKey()) < 0)) {
        proponowanyTyp = wejście.getKey();
        proponowanaCena = wejście.getValue();
      }
    }

    return proponowanyTyp;
  }

}

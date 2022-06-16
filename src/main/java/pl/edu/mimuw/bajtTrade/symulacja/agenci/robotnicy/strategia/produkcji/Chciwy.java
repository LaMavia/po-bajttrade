package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.produkcji;

import java.util.Map;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaProdukcji;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class Chciwy implements StrategiaProdukcji {

  @Override
  public TypyZasobów coProdukować(Robotnik robotnik, int numerDnia, Historia historia,
      Map<TypyZasobów, Integer> premie) {
    TypyZasobów proponentTypu = robotnik.obecnaKariera();
    double proponowaneZarobki = 0.;

    for (TypyZasobów typ : premie.keySet()) {
      double przewidywaneZarobki = historia.dajDzień(numerDnia - 1).dajCenęŚrednią(typ)
          * robotnik.produkuj(typ, premie).ilość();

      if (proponowaneZarobki < przewidywaneZarobki
          || (proponowaneZarobki == przewidywaneZarobki && proponentTypu.compareTo(typ) < 0)) {
        proponentTypu = typ;
        proponowaneZarobki = przewidywaneZarobki;
      }
    }

    return proponentTypu;
  }

}

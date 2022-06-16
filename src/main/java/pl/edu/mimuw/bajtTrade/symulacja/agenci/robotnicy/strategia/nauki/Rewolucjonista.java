package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.nauki;

import java.util.Hashtable;
import java.util.Map.Entry;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera.Kariera;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaNauki;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;

public class Rewolucjonista implements StrategiaNauki {

  @Override
  public TypyZasobów zadecydujOKarierze(Robotnik robotnik, Hashtable<TypyZasobów, Kariera> kariery, Historia historia,
      int numerDnia) {
    if (numerDnia % 7 != 0) {
      return robotnik.obecnaKariera();
    }

    int n = Math.max(1, robotnik.id() % 17);
    Hashtable<TypyZasobów, Integer> wystąpienia = new Hashtable<>();

    for (TypyZasobów typ : TypyZasobów.values()) {
      if (Giełda.czyHandlowalny(typ)) {
        wystąpienia.put(typ, 0);
      }
    }

    for (int di = 1; di <= n; di++) {
      for (TypyZasobów typ : TypyZasobów.values()) {
        if (Giełda.czyHandlowalny(typ)) {
          int ds = historia.dajDzień(numerDnia - di).ileWystawionoNaSprzedaż(typ);
          
          wystąpienia.compute(typ, (t, s) -> s + ds);
        }
      }
    }

    TypyZasobów wynikowaKariera = robotnik.obecnaKariera();
    int maxWystąpień = 0;

    for (Entry<TypyZasobów, Integer> wejście : wystąpienia.entrySet()) {
      if (wejście.getValue() > maxWystąpień || (wejście.getValue() == maxWystąpień
          && Zasób.numerTypu(wejście.getKey()) - Zasób.numerTypu(wynikowaKariera) > 0)) {
        wynikowaKariera = wejście.getKey();
        maxWystąpień = wejście.getValue();
      }
    }

    return wynikowaKariera;
  }

}

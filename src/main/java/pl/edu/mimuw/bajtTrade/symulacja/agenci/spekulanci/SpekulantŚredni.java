package pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażySpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class SpekulantŚredni extends Spekulant {
  private int historiaSpekulantaŚredniego;

  public SpekulantŚredni(int id_, Hashtable<TypyZasobów, Integer> zasoby_, int historiaSpekulantaŚredniego_) {
    super(id_, zasoby_);
    historiaSpekulantaŚredniego = historiaSpekulantaŚredniego_;
  }

  private double liczCenęŚrednią(int numerDnia, Historia historia, TypyZasobów typ) {
    int di = 1;
    double sumaCenŚrednich = 0.;

    for (; di <= historiaSpekulantaŚredniego && numerDnia - di >= 0; di++) {
      sumaCenŚrednich += historia.dajDzień(numerDnia - di).dajCenęŚrednią(typ);
    }

    return sumaCenŚrednich / di;
  }

  @Override
  protected List<OfertaKupnaSpekulanta> coKupuje(int numerDnia, Historia historia) {
    List<OfertaKupnaSpekulanta> oferty = new ArrayList<>();

    for (TypyZasobów typ : zasoby.keySet()) {
      if (!Giełda.czyHandlowalny(typ)) {
        continue;
      }

      double średnia = liczCenęŚrednią(numerDnia, historia, typ);

      boolean posiada = zasoby.get(typ).ilość() > 0;
      double procent = posiada ? 0.9 : 0.95;

      oferty.add(new OfertaKupnaSpekulanta(this, new Produkt(typ, 100), procent * średnia));
    }

    return oferty;
  }

  @Override
  protected List<OfertaSprzedażySpekulanta> coSprzedaje(int numerDnia, Historia historia) {
    List<OfertaSprzedażySpekulanta> oferty = new ArrayList<>();

    for (TypyZasobów typ : zasoby.keySet()) {
      boolean posiada = zasoby.get(typ).ilość() > 0;

      if (!Giełda.czyHandlowalny(typ) || !posiada) {
        continue;
      }

      double średnia = liczCenęŚrednią(numerDnia, historia, typ);
      double procent = 1.1;

      zasoby.get(typ).doProduktów().forEach((produkt) -> {
        oferty.add(new OfertaSprzedażySpekulanta(this, produkt, procent * średnia));
      });
    }

    return oferty;
  }

  @Override
  protected boolean czyCośRobi(int numerDnia, Historia historia) {
    return true;
  }

}

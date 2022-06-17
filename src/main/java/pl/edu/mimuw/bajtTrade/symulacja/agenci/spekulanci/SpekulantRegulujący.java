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

public class SpekulantRegulujący extends Spekulant {

  public SpekulantRegulujący(int id_, Hashtable<TypyZasobów, Integer> zasoby_) {
    super(id_, zasoby_);
  }

  private double obliczCenę(int numerDnia, Historia historia, TypyZasobów typ) {
    double pi = historia.dajDzień(numerDnia).ileWystawionoNaSprzedażRobotników(typ);
    double pii = historia.dajDzień(numerDnia - 1).ileWystawionoNaSprzedażRobotników(typ);

    return historia.dajDzień(numerDnia - 1).dajCenęŚrednią(typ) * pi / Math.max(pii, 1);
  }

  @Override
  protected List<OfertaKupnaSpekulanta> coKupuje(int numerDnia, Historia historia) {
    List<OfertaKupnaSpekulanta> oferty = new ArrayList<>();

    for (TypyZasobów typ : zasoby.keySet()) {
      if (!Giełda.czyHandlowalny(typ)) {
        continue;
      }

      double cena = obliczCenę(numerDnia, historia, typ);

      oferty.add(new OfertaKupnaSpekulanta(this, new Produkt(typ, 100), 0.9 * cena));
    }

    return oferty;
  }

  @Override
  protected List<OfertaSprzedażySpekulanta> coSprzedaje(int numerDnia, Historia historia) {
    List<OfertaSprzedażySpekulanta> oferty = new ArrayList<>();

    for (TypyZasobów typ : zasoby.keySet()) {
      if (!Giełda.czyHandlowalny(typ)) {
        continue;
      }

      double cena = obliczCenę(numerDnia, historia, typ);

      zasoby.get(typ).doProduktów().forEach((produkt) -> {
        oferty.add(new OfertaSprzedażySpekulanta(this, produkt, 1.1 * cena));
      });
    }

    return oferty;
  }

  @Override
  protected boolean czyCośRobi(int numerDnia, Historia historia) {
    return numerDnia > 1;
  }

}

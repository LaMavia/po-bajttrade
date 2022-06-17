package pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pl.edu.mimuw.bajtTrade.struktury.Para;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażySpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.OpisDnia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public class SpekulantWypukły extends Spekulant {
  private static enum TypFunkcji {
    ŚciśleWypukła, ŚciśleWklęsła, Nijaka
  }

  public SpekulantWypukły(int id_, Hashtable<TypyZasobów, Integer> zasoby_) {
    super(id_, zasoby_);
    // TODO Auto-generated constructor stub
  }

  private Para<Double, Double> dajLinię(int numerDnia, Historia historia, TypyZasobów typ) {
    int xl = numerDnia - 3;
    int dx = 2;

    OpisDnia lewy = historia.dajDzień(numerDnia - 3);
    OpisDnia prawy = historia.dajDzień(numerDnia - 1);

    double yl = lewy.dajCenęŚrednią(typ);
    double yp = prawy.dajCenęŚrednią(typ);

    double m = (yp - yl) / dx;

    return new Para<Double, Double>(m, yl - xl * m);
  }

  private double obliczLinię(Para<Double, Double> linia, int x) {
    return linia.p() * x + linia.q();
  }

  private TypFunkcji opiszFunkcję(int numerDnia, Historia historia, TypyZasobów typ) {
    Para<Double, Double> linia = dajLinię(numerDnia, historia, typ);

    double różnicWCentrum = obliczLinię(linia, numerDnia - 2) - historia.dajDzień(numerDnia - 2).dajCenęŚrednią(typ);

    if (różnicWCentrum > 0) {
      return TypFunkcji.ŚciśleWypukła;
    } else if (różnicWCentrum < 0) {
      return TypFunkcji.ŚciśleWklęsła;
    }

    return TypFunkcji.Nijaka;
  }

  @Override
  protected List<OfertaKupnaSpekulanta> coKupuje(int numerDnia, Historia historia) {
    List<OfertaKupnaSpekulanta> oferty = new ArrayList<>();

    for (TypyZasobów typ : zasoby.keySet()) {
      if (!Giełda.czyHandlowalny(typ)) {
        continue;
      }

      if (opiszFunkcję(numerDnia, historia, typ) == TypFunkcji.ŚciśleWypukła) {
        double cena = historia.dajDzień(numerDnia - 1).dajCenęŚrednią(typ) * 0.9;

        oferty.add(new OfertaKupnaSpekulanta(this, new Produkt(typ, 100),
            cena));
      }
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

      if (opiszFunkcję(numerDnia, historia, typ) == TypFunkcji.ŚciśleWklęsła) {
        double cena = historia.dajDzień(numerDnia - 1).dajCenęŚrednią(typ) * 1.1;

        zasoby.get(typ).doProduktów().forEach((produkt) -> {
          oferty.add(new OfertaSprzedażySpekulanta(this, produkt, cena));
        });
      }
    }

    return oferty;
  }

  @Override
  protected boolean czyCośRobi(int numerDnia, Historia historia) {
    return numerDnia >= 3;
  }

}

package pl.edu.mimuw.bajtTrade.symulacja.historia;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import pl.edu.mimuw.bajtTrade.struktury.Para;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Oferta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.Ocenowana;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażySpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class OpisDnia {
  private int dzień;
  private Hashtable<TypyZasobów, Double> cenyMin = new Hashtable<>();
  private Hashtable<TypyZasobów, Double> cenyMax = new Hashtable<>();
  private Hashtable<TypyZasobów, Double> cenyŚrednie = new Hashtable<>();
  private Hashtable<TypyZasobów, Integer> wystawieniaSprzedaży = new Hashtable<>();

  public OpisDnia(Hashtable<TypyZasobów, Double> ceny) {
    dzień = 0;
    cenyMin = ceny;
    cenyŚrednie = ceny;
    cenyMax = ceny;

    wystawieniaSprzedaży = new Hashtable<>();
    for (TypyZasobów typ : TypyZasobów.values()) {
      if (Giełda.czyHandlowalny(typ)) {
        wystawieniaSprzedaży.put(typ, 0);
      }
    }
  }

  public OpisDnia(int dzień_, Giełda giełda, OpisDnia dzień0) {
    dzień = dzień_;

    obliczCenyMin(giełda, dzień0);
    obliczCenyMax(giełda, dzień0);
    obliczCenyŚrednie(giełda, dzień0);
    obliczWystawienia(giełda);
  }

  private <O extends Oferta<?, ?> & Ocenowana> void pomObliczExt(Hashtable<TypyZasobów, Double> ceny,
      BiFunction<Double, Double, Double> wybierz, Set<TypyZasobów> zmienione, List<O> oferty) {
    oferty.forEach((oferta) -> ceny.computeIfPresent(oferta.typ(), (t, cena) -> {
      zmienione.add(t);
      return wybierz.apply(cena, oferta.cenaZaSztukę());
    }));
  }

  private void obliczCenyMin(Giełda giełda, OpisDnia dzień0) {
    for (TypyZasobów t : TypyZasobów.values()) {
      if (Giełda.czyHandlowalny(t)) {
        cenyMin.put(t, Double.MAX_VALUE);
      }
    }

    Set<TypyZasobów> zmienione = new HashSet<>();

    pomObliczExt(cenyMin, (a, b) -> Math.min(a, b), zmienione, giełda.ofertySprzedażySpekulantów());
    pomObliczExt(cenyMin, (a, b) -> Math.min(a, b), zmienione, giełda.ofertyKupnaSpekulantów());

    for (TypyZasobów typ : TypyZasobów.values()) {
      if (!zmienione.contains(typ) && Giełda.czyHandlowalny(typ)) {
        cenyMin.put(typ, dzień0.cenyMin.get(typ));
      }
    }
  }

  private void obliczCenyMax(Giełda giełda, OpisDnia dzień0) {
    for (TypyZasobów t : TypyZasobów.values()) {
      if (Giełda.czyHandlowalny(t)) {
        cenyMax.put(t, 0.0);
      }
    }

    Set<TypyZasobów> zmienione = new HashSet<>();

    pomObliczExt(cenyMax, (a, b) -> Math.max(a, b), zmienione, giełda.ofertySprzedażySpekulantów());
    pomObliczExt(cenyMax, (a, b) -> Math.max(a, b), zmienione, giełda.ofertyKupnaSpekulantów());

    for (TypyZasobów typ : TypyZasobów.values()) {
      if (!zmienione.contains(typ) && Giełda.czyHandlowalny(typ)) {
        cenyMax.put(typ, dzień0.cenyMax.get(typ));
      }
    }
  }

  private void obliczCenyŚrednie(Giełda giełda, OpisDnia dzień0) {
    Hashtable<TypyZasobów, Double> sumyWażone = new Hashtable<>();
    Hashtable<TypyZasobów, Double> ilości = new Hashtable<>();

    for (TypyZasobów t : TypyZasobów.values()) {
      if (Giełda.czyHandlowalny(t)) {
        cenyŚrednie.put(t, 0.0);
        sumyWażone.put(t, 0.);
        ilości.put(t, 0.);
      }
    }

    for (OfertaSprzedażySpekulanta oferta : giełda.ofertySprzedażySpekulantów()) {
      if (oferta.ilośćWypełnionych() == 0.) {
        continue;
      }

      TypyZasobów typ = oferta.typ();
      sumyWażone.computeIfPresent(typ, (t, s) -> s + oferta.ilośćWypełnionych() * oferta.cenaZaSztukę());
      ilości.computeIfPresent(typ, (t, n) -> n + oferta.ilośćWypełnionych());
    }

    for (OfertaKupnaSpekulanta oferta : giełda.ofertyKupnaSpekulantów()) {
      if (oferta.ilośćWypełnionych() == 0.) {
        continue;
      }

      TypyZasobów typ = oferta.typ();
      sumyWażone.computeIfPresent(typ, (t, s) -> s + oferta.ilośćWypełnionych() * oferta.cenaZaSztukę());
      ilości.computeIfPresent(typ, (t, n) -> n + oferta.ilośćWypełnionych());
    }

    for (TypyZasobów typ : sumyWażone.keySet()) {
      cenyŚrednie.compute(typ,
          (t, avg) -> ilości.get(t) == 0 ? dzień0.dajCenęŚrednią(t) : sumyWażone.get(typ) / ilości.get(typ));
    }
  }

  private void obliczWystawienia(Giełda giełda) {
    for (TypyZasobów t : TypyZasobów.values()) {
      if (Giełda.czyHandlowalny(t)) {
        wystawieniaSprzedaży.put(t, 0);
      }
    }

    giełda.ofertySprzedażySpekulantów()
        .forEach((oferta) -> wystawieniaSprzedaży.computeIfPresent(oferta.typ(), (t, s) -> s + 1));
    giełda.ofertySprzedażyRobotników()
        .forEach((oferta) -> wystawieniaSprzedaży.computeIfPresent(oferta.typ(), (t, s) -> s + 1));
  }

  public Double dajCenęŚrednią(TypyZasobów typ) {
    return this.cenyŚrednie.getOrDefault(typ, 0.);
  }

  public Double dajCenęMax(TypyZasobów typ) {
    return this.cenyMax.getOrDefault(typ, 0.);
  }

  public Double dajCenęMin(TypyZasobów typ) {
    return this.cenyMin.getOrDefault(typ, 0.);
  }

  public int ileWystawionoNaSprzedaż(TypyZasobów typ) {
    return this.wystawieniaSprzedaży.getOrDefault(typ, 0);
  }

  public Para<TypyZasobów, Double> dajMaxCenęŚrednią() {
    double maksymalna = 0;
    TypyZasobów typ = TypyZasobów.values()[0];

    for (Entry<TypyZasobów, Double> wejście : cenyŚrednie.entrySet()) {
      if (maksymalna < wejście.getValue()
          || (maksymalna == wejście.getValue() && typ.compareTo(wejście.getKey()) < 0)) {
        maksymalna = wejście.getValue();
        typ = wejście.getKey();
      }
    }

    return new Para<TypyZasobów, Double>(typ, maksymalna);
  }

  public OpisDnia ustawDzień(int d) {
    dzień = d;

    return this;
  }

  public int dzień() {
    return dzień;
  }
}

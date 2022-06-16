package pl.edu.mimuw.bajtTrade.symulacja.historia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;
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

  private void obliczCenyMin(Giełda giełda, OpisDnia dzień0) {
    for (TypyZasobów t : TypyZasobów.values()) {
      if (Giełda.czyHandlowalny(t)) {
        cenyMin.put(t, Double.MAX_VALUE);
      }
    }

    Set<TypyZasobów> zmienione = new HashSet<>();

    for (OfertaSprzedażySpekulanta oferta : giełda.ofertySprzedażySpekulantów()) {
      if (oferta.ilośćWypełnionych() == 0.) {
        continue;
      }

      TypyZasobów typ = oferta.typ();

      cenyMin.put(typ, Math.min(cenyMin.get(typ), oferta.cenaZaSztukę()));
      zmienione.add(typ);
    }

    for (OfertaKupnaSpekulanta oferta : giełda.ofertyKupnaSpekulantów()) {
      if (oferta.ilośćWypełnionych() == 0.) {
        continue;
      }

      TypyZasobów typ = oferta.typ();

      cenyMin.put(typ, Math.min(cenyMin.get(typ), oferta.cenaZaSztukę()));
      zmienione.add(typ);
    }

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

    for (OfertaSprzedażySpekulanta oferta : giełda.ofertySprzedażySpekulantów()) {
      if (oferta.ilośćWypełnionych() == 0.) {
        continue;
      }

      TypyZasobów typ = oferta.typ();

      cenyMax.put(typ, Math.min(cenyMax.get(typ), oferta.cenaZaSztukę()));
      zmienione.add(typ);
    }

    for (OfertaKupnaSpekulanta oferta : giełda.ofertyKupnaSpekulantów()) {
      if (oferta.ilośćWypełnionych() == 0.) {
        continue;
      }

      TypyZasobów typ = oferta.typ();

      cenyMax.put(typ, Math.min(cenyMax.get(typ), oferta.cenaZaSztukę()));
      zmienione.add(typ);
    }

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

  public OpisDnia ustawDzień(int d) {
    dzień = d;

    return this;
  }

  public int dzień() {
    return dzień;
  }
}

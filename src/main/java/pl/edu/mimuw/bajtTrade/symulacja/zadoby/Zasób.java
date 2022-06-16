package pl.edu.mimuw.bajtTrade.symulacja.zadoby;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.ProduktPoziomowy;

public abstract class Zasób {
  protected Agent właściciel;
  protected final TypyZasobów typ;

  public static int numerTypu(TypyZasobów typ) {
    int i = 0;

    for (TypyZasobów t : TypyZasobów.values()) {
      if (typ == t) {
        break;
      } else {
        i++;
      }
    }

    return i;
  }

  public Zasób(Agent właściciel_, TypyZasobów typ_) {
    właściciel = właściciel_;
    typ = typ_;
  }

  public Zasób(TypyZasobów typ_) {
    właściciel = null;
    typ = typ_;
  }

  public final void dodaj(Produkt p) {
    if (p.typ() != typ) {
      return;
    }

    dodajBezpieczny(p);
  };

  protected abstract void dodajBezpieczny(Produkt p);

  public abstract void zużyj(double ilość, Robotnik robotnik, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja);

  public abstract void zużyj(Robotnik robotnik, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja);

  public abstract List<Produkt> zużyj(BiFunction<ProduktPoziomowy, ProduktPoziomowy, ProduktPoziomowy> f, Robotnik robotnik, ProduktPoziomowy produkt);

  public abstract double ilość();
}

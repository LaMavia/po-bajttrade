package pl.edu.mimuw.bajtTrade.symulacja.zadoby;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

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

  public final void użyj(Produkt p, Historia historia) {
    if (p.typ() != typ) {
      return;
    }

    użyjBezpieczny(p, historia);
  };

  protected abstract void użyjBezpieczny(Produkt p, Historia historia);
}

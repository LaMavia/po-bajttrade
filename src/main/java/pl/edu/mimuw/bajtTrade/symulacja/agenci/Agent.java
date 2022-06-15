package pl.edu.mimuw.bajtTrade.symulacja.agenci;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbIlościowy;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbPoziomowy;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbWytrzymałościowy;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.ilosciowe.Diamenty;

public abstract class Agent {
  private final int id;
  protected Hashtable<TypyZasobów, Zasób> zasoby;

  public Agent(int id_, Hashtable<TypyZasobów, Integer> zasoby_) {
    id = id_;
    zasoby = new Hashtable<>();

    for (TypyZasobów t : TypyZasobów.values()) {
      zasoby.put(t, zasóbTypu(t));
    }
  }

  private Zasób zasóbTypu(TypyZasobów t) {
    switch (t) {
      case Diamenty:
      case Jedzenie:
        return new ZasóbIlościowy(this, t);
      case Narzędzia:
      case ProgramyKomputerowe:
        return new ZasóbPoziomowy(this, t);
      case Ubrania:
        return new ZasóbWytrzymałościowy(this, t);
      default:
        return null;
    }
  }

  public void daj(Produkt p) {
    zasoby.get(p.typ()).dodaj(p);
  }

  public void nagrodź(double ilośćDiamentów) {
    this.zasoby.get(TypyZasobów.Diamenty).dodaj(new Diamenty(ilośćDiamentów));
  }

  public abstract ZeznanieOfert<?, ?, ?> rozegrajDzień(int numerDnia, Historia historia);

  public int id() {
    return id;
  }
}

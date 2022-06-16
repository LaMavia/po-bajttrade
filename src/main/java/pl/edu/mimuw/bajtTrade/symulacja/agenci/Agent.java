package pl.edu.mimuw.bajtTrade.symulacja.agenci;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ilościowe.ZasóbDiamentów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ilościowe.ZasóbJedzenia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.poziomowe.ZasóbNarzędzi;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.poziomowe.ZasóbProgramówKomputerowych;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.poziomowe.ZasóbUbrań;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;

public abstract class Agent {
  private final int id;
  protected Hashtable<TypyZasobów, Zasób> zasoby;
  public final static transient Integer x = 5;

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
        return new ZasóbDiamentów(this);
      case Jedzenie:
        return new ZasóbJedzenia(this);
      case Narzędzia:
        return new ZasóbNarzędzi(this);
      case ProgramyKomputerowe:
        return new ZasóbProgramówKomputerowych(this);
      case Ubrania:
        return new ZasóbUbrań(this);
      default:
        return null;
    }
  }

  public void daj(Produkt p) {
    zasoby.get(p.typ()).dodaj(p);
  }

  public void nagrodź(double ilośćDiamentów) {
    this.zasoby.get(TypyZasobów.Diamenty).dodaj(Produkt.diament(ilośćDiamentów));
  }

  public abstract ZeznanieOfert<?, ?, ?> rozegrajDzień(int numerDnia, Historia historia,
      KonfiguracjaSymulacji konfiguracjaSymulacji);

  public int id() {
    return id;
  }

  public double wynik() {
    return zasoby.get(TypyZasobów.Diamenty).ilość();
  }
}

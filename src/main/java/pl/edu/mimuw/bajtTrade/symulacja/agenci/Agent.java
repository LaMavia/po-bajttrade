package pl.edu.mimuw.bajtTrade.symulacja.agenci;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.struktury.ListaPoziomowa;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class Agent {
  private final int id;
  protected Hashtable<TypyZasobów, ListaPoziomowa> zasoby;

  public Agent(int id_, Hashtable<TypyZasobów, ListaPoziomowa> zasoby_) {
    id = id_;
    zasoby = zasoby_;

    for (TypyZasobów t : TypyZasobów.values()) {
      zasoby.putIfAbsent(t, new ListaPoziomowa());
    }
  }
}

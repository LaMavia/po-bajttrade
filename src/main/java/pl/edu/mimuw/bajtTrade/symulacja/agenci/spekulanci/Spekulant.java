package pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.struktury.ListaPoziomowa;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class Spekulant extends Agent {

  public Spekulant(int id_, Hashtable<TypyZasobów, Integer> zasoby_) {
    super(id_, zasoby_);
  }
}

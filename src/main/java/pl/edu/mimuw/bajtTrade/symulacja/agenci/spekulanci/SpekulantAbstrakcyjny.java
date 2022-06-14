package pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.struktury.ListaPoziomowa;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class SpekulantAbstrakcyjny extends Agent {

  public SpekulantAbstrakcyjny(int id_, Hashtable<TypyZasobów, ListaPoziomowa> zasoby_) {
    super(id_, zasoby_);
    //TODO Auto-generated constructor stub
  }
  
}

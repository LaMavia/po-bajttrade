package pl.edu.mimuw.bajtTrade.symulacja.zadoby.ilościowe;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbIlościowy;

public class ZasóbDiamentów extends ZasóbIlościowy {

  public ZasóbDiamentów(Agent właściciel_) {
    super(właściciel_, TypyZasobów.Diamenty);
  }

}

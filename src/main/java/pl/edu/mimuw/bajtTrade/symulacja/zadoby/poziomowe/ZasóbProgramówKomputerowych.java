package pl.edu.mimuw.bajtTrade.symulacja.zadoby.poziomowe;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbPoziomowy;

public class ZasóbProgramówKomputerowych extends ZasóbPoziomowy {

  public ZasóbProgramówKomputerowych(Agent właściciel_) {
    super(właściciel_, TypyZasobów.ProgramyKomputerowe, (p_) -> 1);
  }

}

package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera;

import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class Programista extends Kariera {

  public Programista() {
    super(TypyZasobów.ProgramyKomputerowe);
  }

  @Override
  public int dajPoziomProgramu() {
    return poziom;
  }

}

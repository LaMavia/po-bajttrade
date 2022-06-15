package pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;

public abstract class Produkt implements Comparable<Produkt> {
  private final TypyZasobów typ;
  public int ilość;

  public Produkt(TypyZasobów typ, int ilość) {
    this.typ = typ;
    this.ilość = ilość;
  }

  public TypyZasobów typ() {
    return typ;
  }

  public int ilość() {
    return ilość;
  }

  public abstract void zaaplikujDo(Robotnik robotnik, Historia historia);

  public void zaaplikujDo(Spekulant spekulant, Historia historia) {

  };

  public final void zaaplikujDo(Agent agent, Historia historia) {
    if (agent instanceof Robotnik) {
      zaaplikujDo((Robotnik) agent, historia);
    } else if (agent instanceof Spekulant) {
      zaaplikujDo((Spekulant) agent, historia);
    }
  }

  @Override
  public int compareTo(Produkt inny) {
    return Zasób.numerTypu(typ()) - Zasób.numerTypu(inny.typ());
  }
}

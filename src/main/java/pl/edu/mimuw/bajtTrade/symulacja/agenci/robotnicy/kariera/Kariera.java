package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.kariera;

import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class Kariera {
  private TypyZasobów produkt;
  protected int poziom = 1;

  public Kariera(TypyZasobów produkt_) {
    produkt = produkt_;
  }

  public int dajPremię() {
    switch (poziom) {
      case 1:
        return 50;
      case 2:
        return 150;
      case 3:
        return 300;
      default:
        return 300 + (poziom - 3) * 25;
    }
  }

  public int dajPoziomProgramu() {
    return 1;
  }

  public TypyZasobów produkt() {
    return produkt;
  }
}

package pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty;

import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class ProduktPoziomowy extends Produkt {
  protected int poziom = 1;

  public ProduktPoziomowy(TypyZasobów typ, int ilość, int poziom_) {
    super(typ, ilość);

    poziom = poziom_;
  }

  @Override
  public int compareTo(Produkt inny) {
    int c = super.compareTo(inny);

    if (!(inny instanceof ProduktPoziomowy) || c != 0) {
      return c;
    }

    return poziom - ((ProduktPoziomowy) inny).poziom;
  }
}

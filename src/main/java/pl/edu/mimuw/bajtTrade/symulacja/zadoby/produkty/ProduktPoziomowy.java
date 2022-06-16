package pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty;

import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public class ProduktPoziomowy extends Produkt {
  protected int poziom = 1;

  public ProduktPoziomowy(TypyZasobów typ, double ilość, int poziom_) {
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

  public int poziom() {
    return poziom;
  }

  public ProduktPoziomowy ustawPoziom(int poziom_) {
    poziom = poziom_;

    return this;
  }

  public ProduktPoziomowy zdegraduj() {
    poziom--;

    return this;
  }

  public static ProduktPoziomowy ubrania(double ilość, int poziom) {
    return new ProduktPoziomowy(TypyZasobów.Ubrania, ilość, poziom);
  }

  public static ProduktPoziomowy narzędzia(double ilość, int poziom) {
    return new ProduktPoziomowy(TypyZasobów.Narzędzia, ilość, poziom);
  }

  public static ProduktPoziomowy programyKomputerowe(double ilość, int poziom) {
    return new ProduktPoziomowy(TypyZasobów.ProgramyKomputerowe, ilość, poziom);
  }
}

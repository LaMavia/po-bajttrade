package pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty;

import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;

public class Produkt implements Comparable<Produkt> {
  protected final TypyZasobów typ;
  protected double ilość;

  public Produkt(TypyZasobów typ, double ilość) {
    this.typ = typ;
    this.ilość = ilość;
  }

  public Produkt zIlości(double ilość) {
    return new Produkt(typ(), ilość);
  };

  public TypyZasobów typ() {
    return typ;
  }

  public double ilość() {
    return ilość;
  }

  public final Produkt wydziel(double ilośćDoWydzielenia) {
    double ilośćWydzielona = Math.min(ilość, ilośćDoWydzielenia);
    ilość -= ilośćWydzielona;

    return zIlości(ilośćWydzielona);
  }

  @Override
  public int compareTo(Produkt inny) {
    return Zasób.numerTypu(typ()) - Zasób.numerTypu(inny.typ());
  }

  public static Produkt diament(double ilość) {
    return new Produkt(TypyZasobów.Diamenty, ilość);
  }

  public static Produkt jedzenie(double ilość) {
    return new Produkt(TypyZasobów.Jedzenie, ilość);
  }
}

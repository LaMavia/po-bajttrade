package pl.edu.mimuw.bajtTrade.symulacja.zadoby;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.ProduktPoziomowy;

public class ZasóbIlościowy extends Zasób {
  protected double ilość = 0;

  public ZasóbIlościowy(Agent właściciel_, TypyZasobów typ_) {
    super(właściciel_, typ_);
  }

  @Override
  protected void dodajBezpieczny(Produkt p) {
    ilość += p.ilość();
  }

  @Override
  public double ilość() {
    return ilość;
  }

  @Override
  public void zużyj(double ilość, Robotnik robotnik, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja) {
    this.ilość = Math.max(0, ilość() - ilość);
  }

  @Override
  public void zużyj(Robotnik robotnik, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja) {
    throw new RuntimeException("Niepoprawne zużycie (całościowe) na zasobie ilościowym.");
  }

  @Override
  public List<Produkt> zużyj(BiFunction<ProduktPoziomowy, ProduktPoziomowy, ProduktPoziomowy> f, Robotnik robotnik,
      ProduktPoziomowy produkt) {
    throw new RuntimeException("Niepoprawne zużycie (mapowe) na zasobie ilościowym.");
  }

  @Override
  public List<Produkt> doProduktów() {
    List<Produkt> wyjście = new ArrayList<>();

    if(ilość > 0) {
      wyjście.add(new Produkt(typ, ilość));
    }

    ilość = 0;

    return wyjście;
  }
}

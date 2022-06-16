package pl.edu.mimuw.bajtTrade.symulacja.zadoby;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import pl.edu.mimuw.bajtTrade.struktury.Para;
import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.Produkt;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.produkty.ProduktPoziomowy;

public class ZasóbPoziomowy extends ZasóbIlościowy {
  private final Function<Integer, Integer> wytrzymałośćPoczątkowa;
  protected Map<Para<Integer, Integer>, Integer> przedmioty = new HashMap<>();

  public ZasóbPoziomowy(Agent właściciel_, TypyZasobów typ_,
      Function<Integer, Integer> wytrzymałośćPoczątkowa_) {
    super(właściciel_, typ_);

    wytrzymałośćPoczątkowa = wytrzymałośćPoczątkowa_;
  }

  @Override
  protected void dodajBezpieczny(Produkt p) {
    final int poziom = p instanceof ProduktPoziomowy ? ((ProduktPoziomowy) p).poziom() : 1;

    przedmioty.compute(new Para<>(poziom, wytrzymałośćPoczątkowa.apply(poziom)),
        (_w, ilość) -> ilość + (int) (p.ilość()));
  }

  @Override
  public void zużyj(double ilość, Robotnik robotnik, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja) {
    zużyj(przedmioty.keySet(), (a, b) -> null, null, ilość, robotnik, premie);
  }

  private <T> T zużyj(Collection<Para<Integer, Integer>> klucze, BiFunction<T, Para<Integer, Integer>, T> f,
      T akumulator, double ilość, Robotnik robotnik,
      Map<TypyZasobów, Integer> premie) {
    List<Para<Para<Integer, Integer>, Integer>> zdegradowane = new ArrayList<>();

    for (Para<Integer, Integer> klucz : klucze) {
      if (ilość == 0) {
        break;
      }

      int staraIlość = przedmioty.get(klucz);
      int nowaIlość = (int) Math.max(0, staraIlość - ilość);
      int ilośćZdegradowanych = staraIlość - nowaIlość;

      przedmioty.put(klucz, nowaIlość);

      ilość -= ilośćZdegradowanych;

      akumulator = f.apply(akumulator, new Para<Integer, Integer>(ilośćZdegradowanych, klucz.q()));

      int nowaWytrzymałość = klucz.q() - 1;

      if (nowaWytrzymałość == 0) {
        this.ilość -= ilośćZdegradowanych;
      } else {
        zdegradowane.add(
            new Para<>(new Para<>(klucz.p(), nowaWytrzymałość), ilośćZdegradowanych));
      }

      if (ilość < 0) {
        throw new ArithmeticException("Ujemna ilość");
      }
    }

    zdegradowane.forEach(wejście -> {
      przedmioty.merge(wejście.p(), wejście.q(), (stara, nowa) -> stara + nowa);
    });

    return akumulator;
  }

  @Override
  public void zużyj(Robotnik robotnik, Map<TypyZasobów, Integer> premie, KonfiguracjaSymulacji konfiguracja) {
    zużyj(ilość(), robotnik, premie, konfiguracja);
  }

  @Override
  public List<Produkt> zużyj(BiFunction<ProduktPoziomowy, ProduktPoziomowy, ProduktPoziomowy> f,
      Robotnik robotnik,
      ProduktPoziomowy produkt) {
    BiFunction<Para<List<Produkt>, ProduktPoziomowy>, Para<Integer, Integer>, Para<List<Produkt>, ProduktPoziomowy>> wrapperF = (
        u, k) -> {

      u.p().add(f.apply(u.q(), ProduktPoziomowy.programyKomputerowe(k.p(), k.q())));
      return u;
    };

    Collection<Para<Integer, Integer>> klucze = przedmioty.keySet().stream().sorted((a, b) -> {
      int cp = b.p() - a.p();

      if (cp != 0) {
        return cp;
      }

      return b.q() - a.q();
    }).collect(Collectors.toList());

    return zużyj(klucze, wrapperF, new Para<List<Produkt>, ProduktPoziomowy>(new ArrayList<>(), produkt),
        produkt.ilość(),
        robotnik,
        new Hashtable<>()).p();

  }

}

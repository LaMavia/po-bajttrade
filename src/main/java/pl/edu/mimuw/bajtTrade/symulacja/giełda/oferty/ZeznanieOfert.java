package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty;

import java.util.Iterator;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupna;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedaży;

public class ZeznanieOfert<K extends OfertaKupna, S extends OfertaSprzedaży> {
  private final List<K> ofertyKupna;
  private final List<S> ofertySprzedaży;

  public ZeznanieOfert(List<K> ofertyKupna_, List<S> ofertySprzedaży_) {
    ofertyKupna = ofertyKupna_;
    ofertySprzedaży = ofertySprzedaży_;
  }

  public Iterator<K> ofertyKupna() {
    return ofertyKupna.iterator();
  }

  public Iterator<S> ofertySprzedaży() {
    return ofertySprzedaży.iterator();
  }
}

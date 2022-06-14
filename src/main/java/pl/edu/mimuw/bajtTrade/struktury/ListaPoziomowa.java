package pl.edu.mimuw.bajtTrade.struktury;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;

public class ListaPoziomowa<T extends Number> implements Iterable<Para<Integer, Integer>> {
  private static class Komórka<T> implements Iterable<Integer> {
    private class KomórkaIterator implements Iterator<Integer> {
      private Integer[] indeksy;
      private int i = 0;

      public KomórkaIterator() {
        indeksy = (Integer[]) wartości.keySet().toArray();

        Arrays.sort(indeksy);
      }

      @Override
      public boolean hasNext() {
        return i < indeksy.length - 1;
      }

      @Override
      public Integer next() {
        return indeksy[i++];
      }
    }

    private Hashtable<Integer, T> wartości;

    public Komórka() {
      wartości = new Hashtable<>();
    }

    public T daj(int indeks) {
      return wartości.get(indeks);
    }

    public T ustaw(int indeks, T wartość) {
      return wartości.put(indeks, wartość);
    }

    public T ustawJeśliPusty(int indeks, T wartość) {
      T wynik = daj(indeks);

      if (wynik == null) {
        wynik = ustaw(indeks, wartość);
      }

      return wynik;
    }

    @Override
    public Iterator<Integer> iterator() {
      return new KomórkaIterator();
    }

    @Override
    public int hashCode() {
      return wartości.hashCode();
    }
  }

  private class ListaPoziomowaIterator implements Iterator<Para<Integer, Integer>> {

    private Iterator<Integer> poziomIterator;
    private Iterator<Integer> wierszIterator;

    private Integer poziom;
    private Integer wiersz;

    @Override
    public boolean hasNext() {
      return wierszIterator.hasNext() || poziomIterator.hasNext();
    }

    @Override
    public Para<Integer, Integer> next() {
      if (!wierszIterator.hasNext()) {
        poziom = poziomIterator.next();

        wierszIterator = poziomy.daj(poziom).iterator();
      }

      wiersz = wierszIterator.next();

      return new Para<Integer, Integer>(poziom, wiersz);
    }
  }

  private Komórka<Komórka<T>> poziomy;

  public ListaPoziomowa() {
    poziomy = new Komórka<>();
  }

  public T daj(int poziomIndeks, int wierszIndeks) {
    Komórka<T> p = poziomy.daj(poziomIndeks);

    if (p == null) {
      return null;
    }

    return p.daj(wierszIndeks);
  }

  public T dodaj(int poziomIndeks, int wierszIndeks, T wartość) {
    Komórka<T> p = poziomy.ustawJeśliPusty(poziomIndeks, new Komórka<T>());

    if (p == null) {
      return null;
    }

    return p.ustaw(wierszIndeks, wartość); //  p.daj(wierszIndeks) + wartość
  }

  @Override
  public Iterator<Para<Integer, Integer>> iterator() {
    return new ListaPoziomowaIterator();
  }
}

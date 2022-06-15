package pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty;

import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupna;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedaży;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażyRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażySpekulanta;

public class ZeznanieOfert<W extends Agent, K extends OfertaKupna<W, ?>, S extends OfertaSprzedaży<W, ?>> {
  private final W wystawiający;
  private final List<K> ofertyKupna;
  private final List<S> ofertySprzedaży;

  public ZeznanieOfert(W wystawiający_, List<K> ofertyKupna,
      List<S> ofertySprzedaży) {
    wystawiający = wystawiający_;
    this.ofertyKupna = ofertyKupna;
    this.ofertySprzedaży = ofertySprzedaży;
  }

  public Iterable<K> ofertyKupna() {
    return ofertyKupna;
  }

  public Iterable<S> ofertySprzedaży() {
    return ofertySprzedaży;
  }

  public W wystawiający() {
    return wystawiający;
  }

  public static final class ZeznanieOfertRobotnika
      extends ZeznanieOfert<Robotnik, OfertaKupnaRobotnika, OfertaSprzedażyRobotnika> {

    public ZeznanieOfertRobotnika(Robotnik wystawiający_, List<OfertaKupnaRobotnika> ofertyKupna,
        List<OfertaSprzedażyRobotnika> ofertySprzedaży) {
      super(wystawiający_, ofertyKupna, ofertySprzedaży);
      // TODO Auto-generated constructor stub
    }
  }

  public static final class ZeznanieOfertSpekulanta
      extends ZeznanieOfert<Spekulant, OfertaKupnaSpekulanta, OfertaSprzedażySpekulanta> {

    public ZeznanieOfertSpekulanta(Spekulant wystawiający_, List<OfertaKupnaSpekulanta> ofertyKupna,
        List<OfertaSprzedażySpekulanta> ofertySprzedaży) {
      super(wystawiający_, ofertyKupna, ofertySprzedaży);
      // TODO Auto-generated constructor stub
    }

  }
}

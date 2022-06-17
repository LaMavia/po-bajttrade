package pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażySpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class Spekulant extends Agent {
  public Spekulant(int id_, Hashtable<TypyZasobów, Integer> zasoby_) {
    super(id_, zasoby_);
  }

  protected abstract List<OfertaKupnaSpekulanta> coKupuje(int numerDnia, Historia historia);

  protected abstract List<OfertaSprzedażySpekulanta> coSprzedaje(int numerDnia, Historia historia);

  protected abstract boolean czyCośRobi(int numerDnia, Historia historia);

  @Override
  public ZeznanieOfertSpekulanta rozegrajDzień(int numerDnia, Historia historia,
      KonfiguracjaSymulacji konfiguracjaSymulacji) {
    List<OfertaKupnaSpekulanta> ofertyKupna = new ArrayList<>();
    List<OfertaSprzedażySpekulanta> ofertySprzedaży = new ArrayList<>();

    if (czyCośRobi(numerDnia, historia)) {
      ofertyKupna = coKupuje(numerDnia, historia);
      ofertySprzedaży = coSprzedaje(numerDnia, historia);
    } 

    return new ZeznanieOfertSpekulanta(this, ofertyKupna, ofertySprzedaży);
  }
}

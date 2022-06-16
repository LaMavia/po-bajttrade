package pl.edu.mimuw.bajtTrade.symulacja.giełda;

import java.util.ArrayList;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedaży;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażyRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażySpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupna;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.OpisDnia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class Giełda {
  private List<OfertaKupnaSpekulanta> ofertyKupnaSpekulantów = new ArrayList<>();
  private List<OfertaSprzedażySpekulanta> ofertySprzedażySpekulantów = new ArrayList<>();
  private List<OfertaKupnaRobotnika> ofertyKupnaRobotników = new ArrayList<>();
  private List<OfertaSprzedażyRobotnika> ofertySprzedażyRobotników = new ArrayList<>();
  protected final int numerDnia;


  public Giełda(int numerDnia) {
    this.numerDnia = numerDnia;
  }

  public static boolean czyHandlowalny(TypyZasobów typ) {
    return typ != TypyZasobów.Diamenty;
  }

  private void dopełnijOfertęKupna(OfertaKupnaRobotnika oferta,
      List<OfertaSprzedażySpekulanta> ofertyKomplementacyjne) {
    oferta.wystawiający();
  }

  private void dopełnijOfertęSprzedaży(OfertaSprzedażyRobotnika oferta,
      List<OfertaKupnaSpekulanta> ofertyKomplementacyjne) {
    oferta.wystawiający();
  }

  protected abstract Iterable<ZeznanieOfertRobotnika> ustawRobotników(
      List<ZeznanieOfertRobotnika> ofertyRobotników);

  private void skupuj(int numerDnia, Historia historia, List<OfertaSprzedażyRobotnika> oferty) {
    for (OfertaSprzedażyRobotnika oferta : oferty) {
      oferta.wystawiający().nagrodź(historia.dajDzień(numerDnia - 1).dajCenęŚrednią(oferta.typ()) * oferta.ilość());
    }
  }

  private <Z extends ZeznanieOfert<?, K, S>, K extends OfertaKupna<?, ?>, S extends OfertaSprzedaży<?, ?>> void rozdzielOferty(
      Z zeznanie, List<K> ofertyKupna, List<S> ofertySprzedaży) {
    for (K oferta : zeznanie.ofertyKupna()) {
      ofertyKupna.add(oferta);
    }

    for (S oferta : zeznanie.ofertySprzedaży()) {
      ofertySprzedaży.add(oferta);
    }
  }

  public void przeprowadźHandel(int numerDnia, Historia historia,
      List<ZeznanieOfertRobotnika> ofertyRobotników,
      List<ZeznanieOfertSpekulanta> ofertySpekulantów) {

    // rozdziel oferty
    for (ZeznanieOfertSpekulanta zeznanie : ofertySpekulantów) {
      rozdzielOferty(zeznanie, ofertyKupnaSpekulantów, ofertySprzedażySpekulantów);
    }

    for (ZeznanieOfertRobotnika zeznanie : ofertyRobotników) {
      rozdzielOferty(zeznanie, ofertyKupnaRobotników, ofertySprzedażyRobotników);
    }

    // zapisz w statystyce
    historia.zapiszDzień(new OpisDnia(numerDnia, this, historia.dajDzień(0)));

    // rozlicz oferty robotników
    List<OfertaSprzedażyRobotnika> ofertyDoSkupu = new ArrayList<>();

    for (ZeznanieOfertRobotnika zeznanie : ustawRobotników(
        ofertyRobotników)) {
      // rozlicz oferty sprzedaży
      // Zakładamy, że Zeznanie sortuje odpowiednio oferty w zeznaniu.
      for (OfertaSprzedażyRobotnika oferta : zeznanie.ofertySprzedaży()) {
        // przefiltruj odpowiednie oferty
        List<OfertaKupnaSpekulanta> ofertyKomplementacyjne = new ArrayList<>();

        for (OfertaKupnaSpekulanta ofertaKupna : ofertyKupnaSpekulantów) {
          if (!ofertaKupna.czyWypełniona() && ofertaKupna.typ() == oferta.typ()) {
            ofertyKomplementacyjne.add(ofertaKupna);
          }
        }

        ofertyKomplementacyjne.sort((a, b) -> a.compareTo(b));
        dopełnijOfertęSprzedaży(oferta, ofertyKomplementacyjne);

        if (!oferta.czyWypełniona()) {
          ofertyDoSkupu.add(oferta);
        }
      }

      // rozliczenie ofert kupna
      for (OfertaKupnaRobotnika oferta : zeznanie.ofertyKupna()) {
        // przefiltruj odpowiednie oferty
        List<OfertaSprzedażySpekulanta> ofertyKomplementacyjne = new ArrayList<>();

        for (OfertaSprzedażySpekulanta ofertaSprzedaży : ofertySprzedażySpekulantów) {
          if (!ofertaSprzedaży.czyWypełniona() && ofertaSprzedaży.typ() == oferta.typ()) {
            ofertyKomplementacyjne.add(ofertaSprzedaży);
          }
        }

        ofertyKomplementacyjne.sort((a, b) -> a.compareTo(b));
        dopełnijOfertęKupna(oferta, ofertyKomplementacyjne);
      }
    }

    skupuj(numerDnia, historia, ofertyDoSkupu);
  };

  public List<OfertaKupnaSpekulanta> ofertyKupnaSpekulantów() {
    return ofertyKupnaSpekulantów;
  }

  public List<OfertaSprzedażySpekulanta> ofertySprzedażySpekulantów() {
    return ofertySprzedażySpekulantów;
  }

  public List<OfertaKupnaRobotnika> ofertyKupnaRobotników() {
    return ofertyKupnaRobotników;
  }

  public List<OfertaSprzedażyRobotnika> ofertySprzedażyRobotników() {
    return ofertySprzedażyRobotników;
  }
}

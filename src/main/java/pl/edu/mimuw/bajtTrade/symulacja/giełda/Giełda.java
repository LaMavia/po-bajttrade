package pl.edu.mimuw.bajtTrade.symulacja.giełda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedaży;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażyRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.sprzedaży.OfertaSprzedażySpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupna;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.kupna.OfertaKupnaSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;

public abstract class Giełda {
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

  public void przeprowadźHandel(int numerDnia, Historia historia,
      List<ZeznanieOfertRobotnika> ofertyRobotników,
      List<ZeznanieOfertSpekulanta> ofertySpekulantów) {

    // Preprocessuj zeznania spekulantów
    List<OfertaKupnaSpekulanta> ofertyKupnaSpekulantów = new ArrayList<>();
    List<OfertaSprzedażySpekulanta> ofertySprzedażySpekulantów = new ArrayList<>();

    for (ZeznanieOfertSpekulanta zeznanie : ofertySpekulantów) {
      for (OfertaKupnaSpekulanta ofertaSpekulanta : zeznanie.ofertyKupna()) {
        ofertyKupnaSpekulantów.add(ofertaSpekulanta);
      }

      for (OfertaSprzedażySpekulanta ofertaSpekulanta : zeznanie.ofertySprzedaży()) {
        ofertySprzedażySpekulantów.add(ofertaSpekulanta);
      }
    }

    // rozlicz oferty robotników
    for (ZeznanieOfertRobotnika zeznanie : ustawRobotników(
        ofertyRobotników)) {
      // rozlicz oferty sprzedaży
      // Zakładamy, że Zeznanie sortuje odpowiednio oferty w zeznaniu.
      for (OfertaSprzedażyRobotnika oferta : zeznanie.ofertySprzedaży()) {
        // przefiltruj odpowiednie oferty
        List<OfertaKupnaSpekulanta> ofertyKomplementacyjne = new ArrayList<>();

        for (OfertaKupnaSpekulanta ofertaKupna : ofertyKupnaSpekulantów) {
          if (!ofertaKupna.czyWypełniona()) { // !!zmatchuj typ <- dodaj accessory do produktu?
            ofertyKomplementacyjne.add(ofertaKupna);
          }
        }

        ofertyKomplementacyjne.sort((a, b) -> a.compareTo(b));
        dopełnijOfertęSprzedaży(oferta, ofertyKomplementacyjne);
      }

      for (OfertaKupnaRobotnika oferta : zeznanie.ofertyKupna()) {
        // przefiltruj odpowiednie oferty
        List<OfertaSprzedażySpekulanta> ofertyKomplementacyjne = new ArrayList<>();

        for (OfertaSprzedażySpekulanta ofertaKupna : ofertySprzedażySpekulantów) {
          if (!ofertaKupna.czyWypełniona()) { // !!zmatchuj typ <- dodaj accessory do produktu?
            ofertyKomplementacyjne.add(ofertaKupna);
          }
        }

        ofertyKomplementacyjne.sort((a, b) -> a.compareTo(b));
        dopełnijOfertęKupna(oferta, ofertyKomplementacyjne);
      }
    }
  };
}

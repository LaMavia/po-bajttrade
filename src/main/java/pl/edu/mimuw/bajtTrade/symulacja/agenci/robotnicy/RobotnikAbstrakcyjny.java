package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.struktury.ListaPoziomowa;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;

public abstract class RobotnikAbstrakcyjny extends Agent {
  private Hashtable<TypyZasobów, Integer>  produktywność;

  public RobotnikAbstrakcyjny(int id_, Hashtable<TypyZasobów, ListaPoziomowa> zasoby_, Hashtable<TypyZasobów, Integer>  produktywność_) {
    super(id_, zasoby_);
    
    produktywność = produktywność_;
  }
  
}

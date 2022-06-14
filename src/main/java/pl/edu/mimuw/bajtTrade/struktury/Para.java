package pl.edu.mimuw.bajtTrade.struktury;

public class Para<T, U> {
  private T p;
  private U q;

  public Para(T p_, U q_) {
    p = p_;
    q = q_;
  }

  public T p() {
    return p;
  }

  public U q() {
    return q;
  }
}

package pl.edu.mimuw.bajtTrade;

/**
 * Hello world!
 *
 */
public class App 
{
    private static class A {
        public String a() {return "a";}
    }

    private static class B extends A {
        @Override
        public String a() {
          return "b:a";
        }

        public String b() {
            return "b:b";
        }
    }

    public static class C {
        public String f(A a) {
            return a.a();
        }
    }

    public static class D extends C {
        @Override
        public String f(A a) {
            return a.a();
        }

        public String f(B b) {
            return b.b();
        } 
    }

    public static void main( String[] args )
    {
        D d = new D();
        System.out.println(d.f(new B()));
        // System.out.println(Arrays.toString(args));
    }
}

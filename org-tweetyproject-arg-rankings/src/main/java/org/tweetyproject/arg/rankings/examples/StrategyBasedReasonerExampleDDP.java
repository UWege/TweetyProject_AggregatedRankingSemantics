package org.tweetyproject.arg.rankings.examples;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ArgumentUtils;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.Attack;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.rankings.reasoner.StrategyBasedRankingReasoner;

public class StrategyBasedReasonerExampleDDP {

    public static void main(String[] args){
        //Ex DDP: Gegenbeispiel für Cat
        Argument a = new Argument("a");
        Argument a1 = new Argument("a1");
        Argument a2 = new Argument("a2");
        Argument a3 = new Argument("a3");
        Argument a4 = new Argument("a4");
        Argument a5 = new Argument("a5");
        Argument a6 = new Argument("a6");
        Argument b = new Argument("b");
        Argument b1 = new Argument("b1");
        Argument b2 = new Argument("b2");
        Argument b3 = new Argument("b3");
        Argument b4 = new Argument("b4");
        Argument c1 = new Argument("c1");
        Argument c2 = new Argument("c2");
        Argument c3 = new Argument("c3");
        Argument d = new Argument("d");
        Argument d1 = new Argument("d1");
        Argument d2 = new Argument("d2");
        Argument d3 = new Argument("d3");
        Argument d4 = new Argument("d4");
        Argument e1 = new Argument("e1");
        Argument e2 = new Argument("e2");
        Argument e3 = new Argument("e3");
        Argument f2 = new Argument("f2");
        Argument f1 = new Argument("f1");
        Argument f3 = new Argument("f3");
        Argument f4 = new Argument("f4");
        DungTheory exDDP = new DungTheory();
        //exDDP.add(a, b1, b2, b3, c1, c2, c3);//, d1, d2, d3);//, d2);
        //exDDP.add(e1);//, e2, e3);
        exDDP.add(d, e1, e2, e3, f1, f2, f3);
        //exDDP.add(new Attack(e1,d1));//,new Attack(e2,d2),new Attack(e3,d3));
        //exDDP.add(new Attack(d1, c1), new Attack(d2, c2), new Attack(d3, c3));
        //exDDP.add(new Attack(c1, b1), new Attack(c2, b2), new Attack(c3, b3), new Attack(b1, a), new Attack(b2, a), new Attack(b3, a));
        exDDP.add(new Attack(f4,f2), new Attack(f3,f1), new Attack(f1, e1), new Attack(f2, e1), new Attack(f2, e1), new Attack(e3, d), new Attack(e1, d), new Attack(e2, d));

        DungTheory exDDP_Test = new DungTheory();
        exDDP_Test.add(a,b1,c1,d1,e1,f1);
        exDDP_Test.add(a,b2,c2,d2,e2,f2);
        exDDP_Test.add(new Attack(f1,e1));
        exDDP_Test.add(new Attack(e1,d1));
        exDDP_Test.add(new Attack(d1,c1));
        exDDP_Test.add(new Attack(c1,b1));
        exDDP_Test.add(new Attack(b1,a));
        exDDP_Test.add(new Attack(d2,d1));
        exDDP_Test.add(new Attack(e2,d2));
        //exDDP_Test.add(new Attack(f2,e2));
        //exDDP_Test.add(new Attack(e2,d2));
        //exDDP_Test.add(new Attack(d2,c2));
        //exDDP_Test.add(new Attack(c2,b2));
        //exDDP_Test.add(new Attack(b2,a));

        DungTheory exDDP_Test2 = new DungTheory();
        exDDP_Test2.add(a,b1,c1,d1,e1,f1);
        exDDP_Test2.add(d,b2,c2,d2,e2,f2);
        exDDP_Test2.add(new Attack(a,b1),new Attack(b1,a),new Attack(c1,b1),new Attack(d1,c1));
        exDDP_Test2.add(new Attack(b2,d),new Attack(d2,b2),new Attack(e2,d));
        //Ex DDP Gegenbeispiel M&T (Delobelle) - klappt nicht für Cat - DDP erfüllt, dann: a>b

        // Angebliches Gegenbeispiel von Delobelle für DDP für M&T:
        DungTheory exDDP2_1 = new DungTheory();
        //das hier ergäbe a=0.166666

        exDDP2_1.add(a, a1, a4, a2, a5 ,a3, a6);
        exDDP2_1.add(new Attack(a1, a) , new Attack(a2, a1), new Attack(a3, a2));
        exDDP2_1.add(new Attack(a4, a), new Attack(a5, a4), new Attack(a6, a5));

        exDDP2_1.add(b, b1, b2, b3, b4);
        exDDP2_1.add(new Attack(b1, b), new Attack(b2, b1));
        exDDP2_1.add(new Attack(b4, b1), new Attack(b3, b));

        DungTheory exDDP2_2 = new DungTheory();
        exDDP2_2.add(b, b1, b2, b3, b4);
        exDDP2_2.add(new Attack(b1, b), new Attack(b2, b1));
        exDDP2_2.add(new Attack(b4, b1), new Attack(b3, b));

        // GEGENBEISPIEL Matt und Toni erfüllen nicht DDP! ????
        DungTheory exDDP_loop = new DungTheory();
        exDDP_loop.add(a,b1,c1,d1);
        exDDP_loop.add(d,b2,c2,d2);
        exDDP_loop.add(new Attack(d1,b1),new Attack(b1,d),new Attack(c1,d),new Attack(c1,c1));
        exDDP_loop.add(new Attack(a,b2),new Attack(b2,a), new Attack(c2,a),new Attack(d2,b2));

        StrategyBasedRankingReasoner strat = new StrategyBasedRankingReasoner();

        System.out.println(strat.getModel(exDDP_loop).toString());
        System.out.println(strat.getModel(exDDP_Test2).toString());
        System.out.println(strat.getModel(exDDP_Test).toString());
        System.out.println(strat.getModel(exDDP2_1).toString());
        strat.getModel(exDDP2_1).printOrder();
        System.out.println(strat.getModel(exDDP2_2).toString());

        System.out.println(strat.getModel(exDDP).toString());

    }
}

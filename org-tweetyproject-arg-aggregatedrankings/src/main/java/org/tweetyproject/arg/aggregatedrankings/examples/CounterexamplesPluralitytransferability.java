package org.tweetyproject.arg.aggregatedrankings.examples;

import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedBordaVotingRanking;
import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedCopelandVotingRanking;
import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedPluralityVotingRanking;
import org.tweetyproject.arg.aggregatedrankings.reasoner.RankingCache;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.Attack;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.rankings.reasoner.*;

import java.util.List;

public class CounterexamplesPluralitytransferability {

    public static void main(String[] args) {
            Argument a = new Argument("a");
            Argument a0 = new Argument("a0");
            Argument a1 = new Argument("a1");
            Argument a2 = new Argument("a2");
        Argument a3 = new Argument("a3");
        Argument a4 = new Argument("a4");
        Argument a5 = new Argument("a5");
        Argument a6 = new Argument("a6");
            Argument b = new Argument("b");
        Argument b1 = new Argument("b1");
            Argument c = new Argument("c");
            Argument d = new Argument("d");
            Argument e = new Argument("e");
        Argument e0 = new Argument("e0");
        Argument e1 = new Argument("e1");
        Argument e2 = new Argument("e2");
        Argument e3 = new Argument("e3");
        Argument e4 = new Argument("e4");
        Argument e5 = new Argument("e5");
            Argument f = new Argument("f");
            Argument g = new Argument("g");
            Argument h = new Argument("h");
            Argument i = new Argument("i");
            Argument j = new Argument("j");
            Argument k = new Argument("k");
            Argument l = new Argument("l");
            Argument m = new Argument("m");
        Argument x = new Argument("x");
        Argument y = new Argument("y");
        Argument z1 = new Argument("z1");
        Argument z2 = new Argument("z2");
        Argument z3 = new Argument("z3");
        Argument z4 = new Argument("z4");
        Argument z5 = new Argument("z5");

            //Pluralitytransferability: Search for Counterex for Copeland and abstraction:
            DungTheory plur_CopeAbs = new DungTheory();
            plur_CopeAbs.add(a, b, c);
            plur_CopeAbs.add(new Attack(a, b), new Attack(a, c));
            plur_CopeAbs.add(new Attack(a, a), new Attack(b, c));

            //Counterex independence for counting semantics:
        DungTheory in_CS = new DungTheory();
        in_CS.add(a,b,c,d);
        in_CS.add(new Attack(a,a),new Attack(a,b),new Attack(b,a),new Attack(c,b),new Attack(c,d));

        DungTheory in_CS1 = new DungTheory();
        in_CS1.add(a,b,c,d,e0,e1,e2);
        in_CS1.add(new Attack(a,a),new Attack(a,b),new Attack(b,a),new Attack(c,b),new Attack(c,d));
        in_CS1.add(new Attack(e0,e1),new Attack(e1,e0),new Attack(e0,e2),new Attack(e2,e0),new Attack(e1,e2),new Attack(e2,e1));
        in_CS1.add(new Attack(e0,e0),new Attack(e1,e1),new Attack(e2,e2));

        //Copeland Borda Selfcontradiction?
        DungTheory SC_1 = new DungTheory();
        SC_1.add(a,b,c,d);
        SC_1.add(new Attack(a,a),new Attack(a,b),new Attack(a,d),new Attack(c,b),new Attack(c,d),new Attack(b,d));

        DungTheory SC_2 = new DungTheory();
        SC_2.add(b,c,d);
        SC_2.add(new Attack(c,b),new Attack(c,d),new Attack(b,d));



        //pluralitytransferability indep: -- überflüssig
        DungTheory in_1 = new DungTheory();
        in_1.add(a,b,c,d,e);
        in_1.add(new Attack(c,a),new Attack(a,b),new Attack(b,a),new Attack(b,b),new Attack(b,c),new Attack(c,d),new Attack(d,c),new Attack(d,e),new Attack(e,d),new Attack(c,e),new Attack(e,c));

        //Borda VP
        DungTheory VP_B = new DungTheory();
        VP_B.add(a,b,c);
        VP_B.add(new Attack(b,b),new Attack(c,a),new Attack(c,b));

        //Borda wVP
        DungTheory wVP_B = new DungTheory();
        wVP_B.add(a,b,c,d);
        wVP_B.add(new Attack(b,b ),new Attack(b,c),new Attack(c,a),new Attack(c,c));

        DungTheory in_2 = new DungTheory();
        in_2.add(a,b,c,d,e,e0,e1,e2,e3,e4);
        in_2.add(new Attack(c,a),new Attack(a,b),new Attack(b,a),new Attack(b,b),new Attack(b,c),new Attack(c,d),new Attack(d,c),new Attack(d,e),new Attack(e,d),new Attack(c,e),new Attack(e,c));
        in_2.add(new Attack(e0,e1),new Attack(e1,e0),new Attack(e0,e2),new Attack(e2,e0),new Attack(e1,e2),new Attack(e2,e1));
        in_2.add(new Attack(e0,e0),new Attack(e1,e1),new Attack(e2,e2));
        in_2.add(new Attack(e0,e3),new Attack(e3,e0),new Attack(e1,e3),new Attack(e3,e1),new Attack(e2,e3),new Attack(e3,e2),new Attack(e3,e3));
        in_2.add(new Attack(e0,e4),new Attack(e4,e0),new Attack(e1,e4),new Attack(e4,e1),new Attack(e2,e4),new Attack(e4,e2),new Attack(e3,e4),new Attack(e4,e3),new Attack(e4,e4));
        in_2.add(new Attack(e0,e5),new Attack(e5,e0),new Attack(e1,e5),new Attack(e5,e1),new Attack(e2,e5),new Attack(e5,e2),new Attack(e3,e5),new Attack(e5,e3),new Attack(e4,e5),new Attack(e5,e4),new Attack(e5,e5));

        //Borda AvsFD:
        DungTheory AvsFD_1 = new DungTheory();
        AvsFD_1.add(a1,a2,a3,a4,a5,a6,a,b1,b);
        AvsFD_1.add(new Attack(a1,a),new Attack(a2,a1),new Attack(a3,a),new Attack(a4,a3));//,new Attack(a5,a),new Attack(a6,a5));
        AvsFD_1.add(new Attack(b1,b));

        DungTheory AvsFD_B = new DungTheory();
        AvsFD_B.add(a,b,c,d,e,f,g);
        AvsFD_B.add(new Attack(b,a));
        AvsFD_B.add(new Attack(c,e),new Attack(d,f),new Attack(e,g),new Attack(f,g));

        DungTheory AvsFD_C = new DungTheory();
        AvsFD_C.add(a,b,c,d,e,f,g);
        AvsFD_C.add(x,y,a1,z1,z2,z3,z4,z5);
        AvsFD_C.add(new Attack(b,a));
        AvsFD_C.add(new Attack(c,e),new Attack(d,f),new Attack(e,g),new Attack(f,g));
        AvsFD_C.add(new Attack(x,a1),new Attack(y,a2),new Attack(a1,z1),new Attack(a1,z2),new Attack(a1,z3),new Attack(a1,z4),new Attack(a1,z5));

        DungTheory AvsFD_B_t = new DungTheory();
        AvsFD_B_t.add(a,b,c,d,e,f,g,h,i);
        AvsFD_B_t.add(new Attack(b,a));
        AvsFD_B_t.add(new Attack(c,e),new Attack(d,f),new Attack(e,g),new Attack(f,g));
        AvsFD_B_t.add(new Attack(h,c),new Attack(i,h));

        DungTheory DDP_ex_Amgoud = new DungTheory();
        DDP_ex_Amgoud.add(a,b,c,d,e,f,g,h,i,j,k,l);
        DDP_ex_Amgoud.add(x,y,z1,z2,z3,z4,z5);
        DDP_ex_Amgoud.add(new Attack(c,a),new Attack(d,a),new Attack(g,d),new Attack(h,c),new Attack(l,h));
        DDP_ex_Amgoud.add(new Attack(i,b),new Attack(j,b),new Attack(e,j),new Attack(k,j));
        DDP_ex_Amgoud.add(new Attack(y,x),new Attack(z1,z2),new Attack(z2,z5),new Attack(z3,z4),new Attack(z4,z5));



        //NaE Test:
        DungTheory NaE_1 = new DungTheory();
        NaE_1.add(a,b,c);
        NaE_1.add(new Attack(c,b),new Attack(b,a));


        // Creating List of ranking semantics to aggregate
            CategorizerRankingReasoner cat = new CategorizerRankingReasoner(0.00001);
            DiscussionBasedRankingReasoner dbs = new DiscussionBasedRankingReasoner();
            BurdenBasedRankingReasoner bbs = new BurdenBasedRankingReasoner();
            StrategyBasedRankingReasoner strat = new StrategyBasedRankingReasoner();
            CountingRankingReasoner count = new CountingRankingReasoner();
            SyntaxRankingReasoner syn = new SyntaxRankingReasoner();

        System.out.println("strat AvsFD_B:");
        System.out.println(strat.getModel(AvsFD_B_t).toString());
        strat.getModel(AvsFD_B_t).printOrder();
        System.out.println(dbs.getModel(AvsFD_B_t).toString());
        System.out.println("strat AvsFD_C:");
        System.out.println(strat.getModel(AvsFD_C).toString());
        strat.getModel(AvsFD_C).printOrder();
        System.out.println(dbs.getModel(AvsFD_C).toString());
        System.out.println(bbs.getModel(AvsFD_C).toString());
        System.out.println(cat.getModel(AvsFD_C).toString());

        System.out.println("strat DDP_ex_Amgoud:");
        System.out.println(strat.getModel(DDP_ex_Amgoud).toString());
        strat.getModel(DDP_ex_Amgoud).printOrder();
        System.out.println(dbs.getModel(DDP_ex_Amgoud).toString());
        System.out.println(bbs.getModel(DDP_ex_Amgoud).toString());
        System.out.println(cat.getModel(DDP_ex_Amgoud).toString());
        cat.getModel(DDP_ex_Amgoud).printOrder();
        System.out.println("NaE_1");
        System.out.println(strat.getModel(NaE_1).toString());
        System.out.println(cat.getModel(NaE_1).toString());
        System.out.println(dbs.getModel(NaE_1).toString());
        System.out.println("test");

        System.out.println("SC_1");
        System.out.println(strat.getModel(SC_1).toString());
        System.out.println(cat.getModel(SC_1).toString());
        System.out.println(dbs.getModel(SC_1).toString());
        System.out.println("test");

        System.out.println("SC_2");
        System.out.println(strat.getModel(SC_2).toString());
        System.out.println(cat.getModel(SC_2).toString());
        System.out.println(dbs.getModel(SC_2).toString());
        System.out.println("test");

        System.out.println(strat.getModel(VP_B).toString());
        System.out.println(cat.getModel(VP_B).toString());
        System.out.println(dbs.getModel(VP_B).toString());
        System.out.println("test2");

        System.out.println(strat.getModel(wVP_B).toString());
        System.out.println(cat.getModel(wVP_B).toString());
        System.out.println(dbs.getModel(wVP_B).toString());

        //count.getModel(in_CS).printOrder();
        //System.out.println(count.getModel(in_CS).toString());
        //    count.getModel(in_CS1).printOrder();
        //System.out.println(count.getModel(in_CS1).toString());

            List<AbstractRankingReasoner<?>> reasoners = List.of(cat, dbs, bbs, strat);
            List<AbstractRankingReasoner<?>> reasoners2 = List.of(cat, strat);

        List<AbstractRankingReasoner<?>> reasoners3 = List.of(cat, strat, count);
        List<AbstractRankingReasoner<?>> reasoners4 = List.of(cat,dbs,syn);

        RankingCache rankingCache = new RankingCache();
            AggregatedPluralityVotingRanking aggPlur = new AggregatedPluralityVotingRanking(reasoners,rankingCache);
            AggregatedBordaVotingRanking aggBorda = new AggregatedBordaVotingRanking(reasoners,rankingCache);
            AggregatedCopelandVotingRanking aggCopeland1 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  1);
            AggregatedCopelandVotingRanking aggCopeland05 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  0.5);
            AggregatedCopelandVotingRanking aggCopeland0 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  0);


            RankingCache rankingCache1 = new RankingCache();
            AggregatedPluralityVotingRanking aggPlur2 = new AggregatedPluralityVotingRanking(reasoners2,rankingCache1);
            AggregatedCopelandVotingRanking aggCope2 = new AggregatedCopelandVotingRanking(reasoners2, rankingCache1);
            AggregatedBordaVotingRanking aggBorda2 = new AggregatedBordaVotingRanking(reasoners2, rankingCache1);
            //System.out.println(aggCope2.getModel(plur_CopeAbs).toString());
            //System.out.println(aggBorda2.getModel(plur_CopeAbs).toString());

        RankingCache rankingCache2 = new RankingCache();
        AggregatedBordaVotingRanking aggBorda30 = new AggregatedBordaVotingRanking(reasoners3,rankingCache2,0);
        AggregatedBordaVotingRanking aggBorda31 = new AggregatedBordaVotingRanking(reasoners3,rankingCache2, 1);
        AggregatedBordaVotingRanking aggBorda312 = new AggregatedBordaVotingRanking(reasoners3,rankingCache2, 0.5);

        RankingCache rankingCache4 = new RankingCache();
        AggregatedBordaVotingRanking aggBorda40 = new AggregatedBordaVotingRanking(reasoners4,rankingCache4,0);
        AggregatedBordaVotingRanking aggBorda41 = new AggregatedBordaVotingRanking(reasoners4,rankingCache4, 1);
        AggregatedBordaVotingRanking aggBorda405 = new AggregatedBordaVotingRanking(reasoners4,rankingCache4, 0.5);

        System.out.println("Weak VP Borda:");
        aggBorda41.getModel(wVP_B).printOrder();
        aggBorda40.getModel(wVP_B).printOrder();
        aggBorda405.getModel(wVP_B).printOrder();
        rankingCache4.prettyPrint();

        System.out.println("IN CS Borda:");
        aggBorda30.getModel(in_CS1).printOrder();
        aggBorda30.getModel(in_CS).printOrder();

        aggBorda31.getModel(in_CS1).printOrder();
        aggBorda31.getModel(in_CS).printOrder();

        aggBorda312.getModel(in_CS1).printOrder();
        aggBorda312.getModel(in_CS).printOrder();
        rankingCache2.prettyPrint();

        System.out.println("In CS Copeland:");
        AggregatedCopelandVotingRanking aggCope3 = new AggregatedCopelandVotingRanking(reasoners3,rankingCache2);
    aggCope3.getModel(in_CS1).printOrder();
    aggCope3.getModel(in_CS).printOrder();

        RankingCache rankingCache3 = new RankingCache();
        System.out.println("plur");
    AggregatedPluralityVotingRanking aggPlur3 = new AggregatedPluralityVotingRanking(reasoners3, rankingCache3);
        aggPlur3.getModel(in_1).printOrder();
        aggPlur3.getModel(in_2).printOrder();
        rankingCache3.prettyPrint();

    }

}

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

public class CounterexamplesCatDbsBbsStrat {
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
        Argument f = new Argument("f");
        Argument g = new Argument("g");
        Argument h = new Argument("h");
        Argument i = new Argument("i");
        Argument j = new Argument("j");
        Argument k = new Argument("k");
        Argument l = new Argument("l");
        Argument m = new Argument("m");


        //Independence:
        // Counterexample for Independence for plurality-aggregated semantics:
        // 1. Part: Relevant connected Component
        DungTheory ex1InPV = new DungTheory();
        ex1InPV.add(a, b, c);
        ex1InPV.add(new Attack(a, b), new Attack(a, c));
        ex1InPV.add(new Attack(b, a));
        ex1InPV.add(new Attack(c, b));

        // 2. Part: Whole Argumentation graph
        DungTheory ex2InPV = new DungTheory();
        ex2InPV.add(a, b, c, d);
        ex2InPV.add(new Attack(a, b), new Attack(a, c));
        ex2InPV.add(new Attack(b, a));
        ex2InPV.add(new Attack(c, b));

        // Counterexample for Copeland-alpha-aggregated semantics:
        // 1. Part: Two connected components as seperate graphs
        DungTheory exInCope1 = new DungTheory();
        exInCope1.add(a, b, c, d, e, f);
        exInCope1.add(new Attack(a, a));
        exInCope1.add(new Attack(c, a));
        exInCope1.add(new Attack(c, b), new Attack(d, b));
        exInCope1.add(new Attack(e, c), new Attack(e, d));
        exInCope1.add(new Attack(f, c));

        DungTheory exInCope2 = new DungTheory();
        exInCope2.add(g, h, i, j, k, l, m);
        exInCope2.add(new Attack(h, g), new Attack(k, g), new Attack(i, h), new Attack(j, h), new Attack(j, i), new Attack(l, k), new Attack(m, k), new Attack(l, m), new Attack(i, m), new Attack(j, m));

        // 2. Part: Whole Argumentation graph
        DungTheory exInCope = new DungTheory();
        exInCope.add(a, b, c, d, e, f, g, h, i, j, k, l, m);

        exInCope.add(new Attack(a, a));
        exInCope.add(new Attack(c, a));
        exInCope.add(new Attack(c, b), new Attack(d, b));
        exInCope.add(new Attack(e, c), new Attack(e, d));
        exInCope.add(new Attack(f, c));

        exInCope.add(new Attack(h, g), new Attack(k, g), new Attack(i, h), new Attack(j, h), new Attack(j, i), new Attack(l, k), new Attack(m, k), new Attack(l, m), new Attack(i, m), new Attack(j, m));

        // Counterexample for Borda:
        DungTheory exInBorda = new DungTheory();
        exInBorda.add(a, b, c, d, e, f);
        exInBorda.add(new Attack(a, c), new Attack(a, d), new Attack(b, b), new Attack(b, c), new Attack(b, d), new Attack(c, a), new Attack(d, a));
        exInBorda.add(new Attack(e, f));

        DungTheory exInBorda1 = new DungTheory();
        exInBorda1.add(a, b, c, d);
        exInBorda1.add(new Attack(a, c), new Attack(a, d), new Attack(b, b), new Attack(b, c), new Attack(b, d), new Attack(c, a), new Attack(d, a));


        // Cardinality-precedence:
        // Counterexample for plurality-aggregated semantics
        DungTheory exCPP = new DungTheory();
        exCPP.add(a, b, c, d, e);
        exCPP.add(new Attack(b, a));
        exCPP.add(new Attack(d, c), new Attack(e, c));
        // Counterexample for Copeland-aggregated, Borda-aggregated semantics (and plurality-aggregated semantics)
        DungTheory ex2CP = new DungTheory();
        ex2CP.add(a, b, c, d, e, f, g);
        ex2CP.add(new Attack(a, a));
        ex2CP.add(new Attack(c, b), new Attack(d, b));
        ex2CP.add(new Attack(e, c), new Attack(e, d));
        ex2CP.add(new Attack(f, c), new Attack(f, d));
        ex2CP.add(new Attack(g, c), new Attack(g, d));


        // Counter-transitivity:
        // Counterexample for Plurality, Copeland and Borda: *
        DungTheory exCT = new DungTheory();
        exCT.add(a, b);
        exCT.add(new Attack(a, a), new Attack(a, b));


        // Strict Counter-transitivity:
        // Counterexample for Plurality(?), Copeland and Borda: *
        DungTheory exSCT = new DungTheory();
        exSCT.add(a, b, c);
        exSCT.add(new Attack(a, a), new Attack(a, b), new Attack(b, c));


        // Self-Contradiction:
        // Counterexample for Plurality(?), Copeland and Borda: *
        DungTheory exSC = new DungTheory();
        exSC.add(a, b, c);
        exSC.add(new Attack(a, a), new Attack(b, c));


        // sigma-Compatibility:
        // Counterexample ? for:
        DungTheory exsComp = new DungTheory();
        exsComp.add(a, b, c, d);
        exsComp.add(new Attack(a, b), new Attack(a, c), new Attack(b, d), new Attack(c, d));


        // Quality-Precedence:
        // Counterexample for Plurality, Copeland and Borda: *
        DungTheory exQP = new DungTheory();
        exQP.add(a, b, c);
        exQP.add(new Attack(a, a), new Attack(a, b), new Attack(b, a), new Attack(c, b), new Attack(c, c));


        //Attack vs Full Defense
        DungTheory exAvsFD = new DungTheory();
        exAvsFD.add(a, b, c, d);
        exAvsFD.add(new Attack(a, b), new Attack(a, c), new Attack(b, d), new Attack(c, d));


        DungTheory exAvsDF1 = new DungTheory();
        exAvsDF1.add(a, a1, a2, a3, a4, a5, a6, b, b1);
        exAvsDF1.add(new Attack(a2, a1), new Attack(a4, a3), new Attack(a6, a5));
        exAvsDF1.add(new Attack(a1, a), new Attack(a3, a), new Attack(a5, a));
        exAvsDF1.add(new Attack(b1, b));

        DungTheory exAvsDF2 = new DungTheory();
        exAvsDF2.add(a, a1, a2, a3, a4, b, b1);
        exAvsDF2.add(new Attack(a2, a1), new Attack(a4, a3));
        exAvsDF2.add(new Attack(a1, a), new Attack(a3, a));
        exAvsDF2.add(new Attack(b1, b));

        DungTheory exAvsDF3 = new DungTheory();
        exAvsDF3.add(a, a1, a2, b, b1);
        exAvsDF3.add(new Attack(a2, a1));
        exAvsDF3.add(new Attack(a1, a));
        exAvsDF3.add(new Attack(b1, b));

        DungTheory exAvsDF4 = new DungTheory();
        exAvsDF4.add(a, b, b1, c);
        exAvsDF4.add(new Attack(b, a), new Attack(b1, a), new Attack(c, b), new Attack(c, b1));


        // ---------------


        // Void-Precedence:
        // Counterex ? with serializable --- nope!
        DungTheory exVP = new DungTheory();
        exVP.add(a, b, c);
        exVP.add(new Attack(b, c), new Attack(c, b));

        // Beispiel für starke Gegentransitivität für Priority Voting
        DungTheory exCT1 = new DungTheory();
        exCT1.add(a, b, c);
        exCT1.add(new Attack(b, a));
        exCT1.add(new Attack(c, b));
        // Test CT/SCT?
        DungTheory exCT2 = new DungTheory();
        exCT2.add(a, a1, a2, a3, a4, a5);
        exCT2.add(new Attack(a, a1), new Attack(a1, a2), new Attack(a2, a3), new Attack(a3, a4), new Attack(a4, a5));


        //Gegenbsp Void precedence PriorityVoting ?
        DungTheory privit_void = new DungTheory();
        privit_void.add(a0, a1, a2);
        privit_void.add(new Attack(a1, a2));


        //Counterexample adm-Compatibility
        DungTheory admComp_ex = new DungTheory();
        admComp_ex.add(a, b, c, d, e);
        admComp_ex.add(new Attack(e, c), new Attack(e, d));
        admComp_ex.add(new Attack(d, b), new Attack(c, b));
        admComp_ex.add(new Attack(b, a), new Attack(a, a));


        //Pluralitytransferability: Search for Counterex for Copeland and abstraction:
        DungTheory plur_CopeAbs = new DungTheory();
        plur_CopeAbs.add(a, b, c);
        plur_CopeAbs.add(new Attack(a, b), new Attack(a, c));
        plur_CopeAbs.add(new Attack(a, a), new Attack(b, c));


        Argument b0 = new Argument("b0");
        Argument c0 = new Argument("c0");
        Argument d0 = new Argument("d0");
        //Argument b1 = new Argument("b1");
        Argument c1 = new Argument("c1");
        Argument a10 = new Argument("a10");
        Argument b10 = new Argument("b10");
        Argument c10 = new Argument("c10");

        // same graphs, different output --> in the old version | als Test?
        DungTheory exMandT_1 = new DungTheory();
        exMandT_1.add(a, b, c, a1, b1, c1, d);
        exMandT_1.add(new Attack(a, b), new Attack(b, a), new Attack(b, c), new Attack(c, b), new Attack(a, c), new Attack(c, a));
        exMandT_1.add(new Attack(a, b1), new Attack(a, c1), new Attack(b, a1), new Attack(b, c1), new Attack(c, a1), new Attack(c, b1));
        exMandT_1.add(new Attack(c1, a1), new Attack(c1, b1));
        exMandT_1.add(new Attack(d, c));

        DungTheory exMandT_2 = new DungTheory();
        exMandT_2.add(a0, b0, c0, a10, b10, c10, d0);
        exMandT_2.add(new Attack(a0, b0), new Attack(b0, a0), new Attack(b0, c0), new Attack(c0, b0), new Attack(a0, c0), new Attack(c0, a0));
        exMandT_2.add(new Attack(a0, b10), new Attack(a0, c10), new Attack(b0, a10), new Attack(b0, c10), new Attack(c0, a10), new Attack(c0, b10));
        exMandT_2.add(new Attack(c10, a10), new Attack(c10, b10));
        exMandT_2.add(new Attack(d0, c0));


        // Creating list of ranking semantics Cat, Dbs, Bbs and strat to aggregate
        CategorizerRankingReasoner cat = new CategorizerRankingReasoner(0.00001);
        DiscussionBasedRankingReasoner dbs = new DiscussionBasedRankingReasoner();
        BurdenBasedRankingReasoner bbs = new BurdenBasedRankingReasoner();
        StrategyBasedRankingReasoner strat = new StrategyBasedRankingReasoner();
        List<AbstractRankingReasoner<?>> reasoners = List.of(cat, dbs, bbs, strat);

        //create a rankingCache to calculate the rankings of the used ranking semantics just once for each argumentation graph
        RankingCache rankingCache = new RankingCache();
        //aggregation semantics for plurality, Borda and Copeland over the four ranking semantics
        AggregatedPluralityVotingRanking aggPlur = new AggregatedPluralityVotingRanking(reasoners, rankingCache);
        AggregatedBordaVotingRanking aggBorda1 = new AggregatedBordaVotingRanking(reasoners, rankingCache, 1);
        AggregatedBordaVotingRanking aggBorda05 = new AggregatedBordaVotingRanking(reasoners, rankingCache, 0.5);
        AggregatedBordaVotingRanking aggBorda0 = new AggregatedBordaVotingRanking(reasoners, rankingCache, 0);
        AggregatedCopelandVotingRanking aggCopeland1 = new AggregatedCopelandVotingRanking(reasoners, rankingCache, 1);
        AggregatedCopelandVotingRanking aggCopeland05 = new AggregatedCopelandVotingRanking(reasoners, rankingCache, 0.5);
        AggregatedCopelandVotingRanking aggCopeland0 = new AggregatedCopelandVotingRanking(reasoners, rankingCache, 0);


        //Print Counterexamples:
        System.out.println("Independence:");
        System.out.println("Counterexample for Independence for plurality-aggregated semantics");
        System.out.println("Argumentation Framework: " + ex1InPV.toString());
        System.out.println(aggPlur.getModel(ex1InPV).toString());
        aggPlur.getModel(ex1InPV).printOrder();
        System.out.println(aggPlur.getModel(ex2InPV).toString());
        aggPlur.getModel(ex2InPV).printOrder();

        System.out.println("\n");

        System.out.println("Counterexample for Independence for Copeland-alpha-aggregated semantics");

        System.out.println("Argumentation Framework (whole graph): " + exInCope.toString());
        System.out.println("Cat: " + cat.getModel(exInCope).toString());
        cat.getModel(exInCope).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exInCope).toString());
        System.out.println("Bbs: " + bbs.getModel(exInCope).toString());
        System.out.println("Strat: " + strat.getModel(exInCope).toString());
        strat.getModel(exInCope).printOrder();

        System.out.println("Copeland-1: " + aggCopeland1.getModel(exInCope).toString());
        aggCopeland1.getModel(exInCope).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exInCope).toString());
        aggCopeland05.getModel(exInCope).printOrder();
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exInCope).toString());
        aggCopeland0.getModel(exInCope).printOrder();

        System.out.println("First connected component: " + exInCope1.toString());
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exInCope1).toString());
        aggCopeland1.getModel(exInCope1).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exInCope1).toString());
        aggCopeland05.getModel(exInCope1).printOrder();
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exInCope1).toString());
        aggCopeland0.getModel(exInCope1).printOrder();

        System.out.println("Second connected component: " + exInCope2.toString());
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exInCope2).toString());
        aggCopeland1.getModel(exInCope2).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exInCope2).toString());
        aggCopeland05.getModel(exInCope2).printOrder();
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exInCope2).toString());
        aggCopeland0.getModel(exInCope2).printOrder();

        System.out.println("\n");

        // Test for Borda (counterexample for Borda-1 and Borda-0):
        System.out.println("Test for Borda:");
        System.out.println("Whole graph:");
        System.out.println("Cat: " + cat.getModel(exInCope).toString());
        cat.getModel(exInCope).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exInCope).toString());
        System.out.println("Bbs: " + bbs.getModel(exInCope).toString());
        System.out.println("Strat: " + strat.getModel(exInCope).toString());
        strat.getModel(exInCope).printOrder();

        System.out.println("Borda-1: " + aggBorda1.getModel(exInCope).toString());
        aggBorda1.getModel(exInCope).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exInCope).toString());
        aggBorda05.getModel(exInCope).printOrder();
        System.out.println("Borda-0: " + aggBorda0.getModel(exInCope).toString());
        aggBorda0.getModel(exInCope).printOrder();

        System.out.println("First connected component:");
        System.out.println("Borda-1: " + aggBorda1.getModel(exInCope1).toString());
        aggBorda1.getModel(exInCope1).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exInCope1).toString());
        aggBorda05.getModel(exInCope1).printOrder();
        System.out.println("Borda-0: " + aggBorda0.getModel(exInCope1).toString());
        aggBorda0.getModel(exInCope1).printOrder();

        System.out.println("Second connected component:");
        System.out.println("Borda-1: " + aggBorda1.getModel(exInCope2).toString());
        aggBorda1.getModel(exInCope2).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exInCope2).toString());
        aggBorda05.getModel(exInCope2).printOrder();
        System.out.println("Borda-0: " + aggBorda0.getModel(exInCope2).toString());
        aggBorda0.getModel(exInCope2).printOrder();

        System.out.println("\n");

        System.out.println("Counterexample for Borda:");
        System.out.println("Argumentation Framework (whole graph): " + exInBorda.toString());
        System.out.println("Cat: " + cat.getModel(exInBorda).toString());
        cat.getModel(exInBorda).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exInBorda).toString());
        System.out.println("Bbs: " + bbs.getModel(exInBorda).toString());
        System.out.println("Strat: " + strat.getModel(exInBorda).toString());
        strat.getModel(exInBorda).printOrder();

        System.out.println("Borda-1: " + aggBorda1.getModel(exInBorda).toString());
        aggBorda1.getModel(exInBorda).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exInBorda).toString());
        aggBorda05.getModel(exInBorda).printOrder();
        System.out.println("Borda-0: " + aggBorda0.getModel(exInBorda).toString());
        aggBorda0.getModel(exInBorda).printOrder();

        System.out.println("First connected component: " + exInBorda1.toString());
        System.out.println("Borda-1: " + aggBorda1.getModel(exInBorda1).toString());
        aggBorda1.getModel(exInBorda1).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exInBorda1).toString());
        aggBorda05.getModel(exInBorda1).printOrder();
        System.out.println("Borda-0: " + aggBorda0.getModel(exInBorda1).toString());
        aggBorda0.getModel(exInBorda1).printOrder();

        System.out.println("\n");


        System.out.println("Cardinality Precedence:");
        System.out.println("Counterexample for Cardinality Precedence for plurality-aggregated semantics");
        System.out.println("Argumentation Framework: " + exCPP.toString());
        System.out.println("Cat: " + cat.getModel(exCPP).toString());
        cat.getModel(exCPP).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exCPP).toString());
        System.out.println("Bbs: " + bbs.getModel(exCPP).toString());
        System.out.println("Strat: " + strat.getModel(exCPP).toString());
        strat.getModel(exCPP).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exCPP).toString());

        System.out.println("\n");

        System.out.println("Counterexamples for Cardinality Precedence for Copeland-aggregated semantics, Borda-aggregated semantics and Plurality-aggregated semantics");
        System.out.println("Argumentation Framework: " + ex2CP.toString());
        System.out.println("Cat: " + cat.getModel(ex2CP).toString());
        cat.getModel(ex2CP).printOrder();
        System.out.println("Dbs: " + dbs.getModel(ex2CP).toString());
        System.out.println("Bbs: " + bbs.getModel(ex2CP).toString());
        System.out.println("Strat: " + strat.getModel(ex2CP).toString());
        strat.getModel(ex2CP).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(ex2CP).toString());
        aggPlur.getModel(ex2CP).printOrder();
        System.out.println("Copeland");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(ex2CP).toString());
        aggCopeland0.getModel(ex2CP).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(ex2CP).toString());
        aggCopeland05.getModel(ex2CP).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(ex2CP).toString());
        aggCopeland1.getModel(ex2CP).printOrder();
        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(ex2CP).toString());
        aggBorda0.getModel(ex2CP).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(ex2CP).toString());
        aggBorda05.getModel(ex2CP).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(ex2CP).toString());
        aggBorda1.getModel(ex2CP).printOrder();

        System.out.println("\n");

        System.out.println("Counterexample for Counter-Transitivity for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Argumentation Framework: " + exCT.toString());
        System.out.println("Cat: " + cat.getModel(exCT).toString());
        cat.getModel(exCT).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exCT).toString());
        System.out.println("Bbs: " + bbs.getModel(exCT).toString());
        System.out.println("Strat: " + strat.getModel(exCT).toString());
        strat.getModel(exCT).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exCT).toString());
        aggPlur.getModel(exCT).printOrder();
        System.out.println("Copeland:");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exCT).toString());
        aggCopeland0.getModel(exCT).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exCT).toString());
        aggCopeland05.getModel(exCT).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exCT).toString());
        aggCopeland1.getModel(exCT).printOrder();
        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(exCT).toString());
        aggBorda0.getModel(exCT).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exCT).toString());
        aggBorda05.getModel(exCT).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(exCT).toString());
        aggBorda1.getModel(exCT).printOrder();

        System.out.println("\n");

        System.out.println("Counterexample for Strict-Counter-Transitivity for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Argumentation Framework: " + exSCT.toString());
        System.out.println("Cat: " + cat.getModel(exSCT).toString());
        cat.getModel(exSCT).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exSCT).toString());
        System.out.println("Bbs: " + bbs.getModel(exSCT).toString());
        System.out.println("Strat: " + strat.getModel(exSCT).toString());
        strat.getModel(exSCT).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exSCT).toString());
        aggPlur.getModel(exSCT).printOrder();
        System.out.println("Copeland:");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exSCT).toString());
        aggCopeland0.getModel(exSCT).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exSCT).toString());
        aggCopeland05.getModel(exSCT).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exSCT).toString());
        aggCopeland1.getModel(exSCT).printOrder();
        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(exSCT).toString());
        aggBorda0.getModel(exSCT).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exSCT).toString());
        aggBorda05.getModel(exSCT).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(exSCT).toString());
        aggBorda1.getModel(exSCT).printOrder();

        System.out.println("\n");

        System.out.println("Counterexample for sigma-Compatibility for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Argumentation Framework: " + exsComp.toString());
        System.out.println("Cat: " + cat.getModel(exsComp).toString());
        cat.getModel(exsComp).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exsComp).toString());
        System.out.println("Bbs: " + bbs.getModel(exsComp).toString());
        System.out.println("Strat: " + strat.getModel(exsComp).toString());
        strat.getModel(exsComp).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exsComp).toString());
        System.out.println(aggPlur.getModel(exsComp).toString());
        aggPlur.getModel(exsComp).printOrder();
        System.out.println("Copeland:");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exsComp).toString());
        aggCopeland0.getModel(exsComp).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exsComp).toString());
        aggCopeland05.getModel(exsComp).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exsComp).toString());
        aggCopeland1.getModel(exsComp).printOrder();

        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(exsComp).toString());
        aggBorda0.getModel(exsComp).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exsComp).toString());
        aggBorda05.getModel(exsComp).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(exsComp).toString());
        aggBorda1.getModel(exsComp).printOrder();

        System.out.println("\n");

        System.out.println("Counterexample for Self-Contradiction for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Argumentation Framework: " + exSC.toString());
        System.out.println("Cat: " + cat.getModel(exSC).toString());
        cat.getModel(exSC).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exSC).toString());
        System.out.println("Bbs: " + bbs.getModel(exSC).toString());
        System.out.println("Strat: " + strat.getModel(exSC).toString());
        strat.getModel(exSC).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exSC).toString());
        aggPlur.getModel(exSC).printOrder();
        System.out.println("Copeland:");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exSC).toString());
        aggCopeland0.getModel(exSC).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exSC).toString());
        aggCopeland05.getModel(exSC).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exSC).toString());
        aggCopeland1.getModel(exSC).printOrder();
        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(exSC).toString());
        aggBorda0.getModel(exSC).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exSC).toString());
        aggBorda05.getModel(exSC).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(exSC).toString());
        aggBorda1.getModel(exSC).printOrder();

        System.out.println("\n");

        System.out.println("Counterexample for Quality-Precedence for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Argumentation Framework: " + exQP.toString());
        System.out.println("Cat: " + cat.getModel(exQP).toString());
        cat.getModel(exQP).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exQP).toString());
        System.out.println("Bbs: " + bbs.getModel(exQP).toString());
        System.out.println("Strat: " + strat.getModel(exQP).toString());
        strat.getModel(exQP).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exQP).toString());
        aggPlur.getModel(exQP).printOrder();
        System.out.println("Copeland:");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exQP).toString());
        aggCopeland0.getModel(exQP).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exQP).toString());
        aggCopeland05.getModel(exQP).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exQP).toString());
        aggCopeland1.getModel(exQP).printOrder();
        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(exQP).toString());
        aggBorda0.getModel(exQP).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exQP).toString());
        aggBorda05.getModel(exQP).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(exQP).toString());
        aggBorda1.getModel(exQP).printOrder();

        System.out.println("\n");

        System.out.println("Counterexample for Attack vs Full Defense for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Argumentation Framework: " + exAvsFD.toString());
        System.out.println("Cat: " + cat.getModel(exAvsFD).toString());
        cat.getModel(exAvsFD).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exAvsFD).toString());
        System.out.println("Bbs: " + bbs.getModel(exAvsFD).toString());
        System.out.println("Strat: " + strat.getModel(exAvsFD).toString());
        strat.getModel(exAvsFD).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exAvsFD).toString());
        aggPlur.getModel(exAvsFD).printOrder();
        System.out.println("Copeland:");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exAvsFD).toString());
        aggCopeland0.getModel(exAvsFD).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exAvsFD).toString());
        aggCopeland05.getModel(exAvsFD).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exAvsFD).toString());
        aggCopeland1.getModel(exAvsFD).printOrder();
        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(exAvsFD).toString());
        aggBorda0.getModel(exAvsFD).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exAvsFD).toString());
        aggBorda05.getModel(exAvsFD).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(exAvsFD).toString());
        aggBorda1.getModel(exAvsFD).printOrder();

        System.out.println("\n");
        //------------------------
        System.out.println("Counterexample for Attack vs Full Defense for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Plurality:");
        aggPlur.getModel(exAvsDF1).printOrder();
        System.out.println("Copeland:");
        aggCopeland0.getModel(exAvsDF1).printOrder();
        aggCopeland05.getModel(exAvsDF1).printOrder();
        aggCopeland1.getModel(exAvsDF1).printOrder();
        System.out.println("Borda:");
        aggBorda0.getModel(exAvsDF1).printOrder();
        aggBorda05.getModel(exAvsDF1).printOrder();
        aggBorda1.getModel(exAvsDF1).printOrder();

        System.out.println("Cat: " + cat.getModel(exAvsDF1).toString());
        cat.getModel(exAvsDF1).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exAvsDF1).toString());
        System.out.println("Bbs: " + bbs.getModel(exAvsDF1).toString());
        System.out.println("Strat: " + strat.getModel(exAvsDF1).toString());
        strat.getModel(exAvsDF1).printOrder();

        System.out.println("Counterexample for Attack vs Full Defense 2? for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Plurality:");
        aggPlur.getModel(exAvsDF2).printOrder();
        System.out.println("Copeland:");
        aggCopeland0.getModel(exAvsDF2).printOrder();
        aggCopeland05.getModel(exAvsDF2).printOrder();
        aggCopeland1.getModel(exAvsDF2).printOrder();
        System.out.println("Borda:");
        aggBorda0.getModel(exAvsDF2).printOrder();
        aggBorda05.getModel(exAvsDF2).printOrder();
        aggBorda1.getModel(exAvsDF2).printOrder();

        System.out.println("Cat: " + cat.getModel(exAvsDF2).toString());
        cat.getModel(exAvsDF2).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exAvsDF2).toString());
        System.out.println("Bbs: " + bbs.getModel(exAvsDF2).toString());
        System.out.println("Strat: " + strat.getModel(exAvsDF2).toString());
        strat.getModel(exAvsDF2).printOrder();

        System.out.println("Counterexample for Attack vs Full Defense 3 for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Plurality:");
        aggPlur.getModel(exAvsDF3).printOrder();
        System.out.println("Copeland:");
        aggCopeland0.getModel(exAvsDF3).printOrder();
        aggCopeland05.getModel(exAvsDF3).printOrder();
        aggCopeland1.getModel(exAvsDF3).printOrder();
        System.out.println("Borda:");
        aggBorda0.getModel(exAvsDF3).printOrder();
        aggBorda05.getModel(exAvsDF3).printOrder();
        aggBorda1.getModel(exAvsDF3).printOrder();

        System.out.println("Cat: " + cat.getModel(exAvsDF3).toString());
        cat.getModel(exAvsDF3).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exAvsDF3).toString());
        System.out.println("Bbs: " + bbs.getModel(exAvsDF3).toString());
        System.out.println("Strat: " + strat.getModel(exAvsDF3).toString());
        strat.getModel(exAvsDF3).printOrder();


        System.out.println("Counterexample for Attack vs Full Defense 4 for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Plurality:");
        aggPlur.getModel(exAvsDF4).printOrder();
        System.out.println("Copeland:");
        aggCopeland0.getModel(exAvsDF4).printOrder();
        aggCopeland05.getModel(exAvsDF4).printOrder();
        aggCopeland1.getModel(exAvsDF4).printOrder();
        System.out.println("Borda:");
        aggBorda0.getModel(exAvsDF4).printOrder();
        aggBorda05.getModel(exAvsDF4).printOrder();
        aggBorda1.getModel(exAvsDF4).printOrder();

        System.out.println("Cat: " + cat.getModel(exAvsDF4).toString());
        cat.getModel(exAvsDF4).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exAvsDF4).toString());
        System.out.println("Bbs: " + bbs.getModel(exAvsDF4).toString());
        System.out.println("Strat: " + strat.getModel(exAvsDF4).toString());
        strat.getModel(exAvsDF4).printOrder();

        //---------------------
        System.out.println("\n");

        System.out.println("Counterexample for Argument Equivalence and (Ordinal) Equivalence for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Argumentation Framework: " + exCT2.toString());
        System.out.println("Cat: " + cat.getModel(exCT2).toString());
        cat.getModel(exCT2).printOrder();
        System.out.println("Dbs: " + dbs.getModel(exCT2).toString());
        System.out.println("Bbs: " + bbs.getModel(exCT2).toString());
        System.out.println("Strat: " + strat.getModel(exCT2).toString());
        strat.getModel(exCT2).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exCT2).toString());
        aggPlur.getModel(exCT2).printOrder();
        System.out.println("Copeland:");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exCT2).toString());
        aggCopeland0.getModel(exCT2).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exCT2).toString());
        aggCopeland05.getModel(exCT2).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exCT2).toString());
        aggCopeland1.getModel(exCT2).printOrder();
        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(exCT2).toString());
        aggBorda0.getModel(exCT2).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exCT2).toString());
        aggBorda05.getModel(exCT2).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(exCT2).toString());
        aggBorda1.getModel(exCT2).printOrder();


        /*
        System.out.println("DAS HIER WIEDER LÖSCHEN...");
        List<AbstractRankingReasoner<?>> reasoners2 = List.of(cat, dbs, bbs);
        RankingCache rankingCache1 = new RankingCache();
        AggregatedPluralityVotingRanking aggPlur2 = new AggregatedPluralityVotingRanking(reasoners2,rankingCache1);
        AggregatedCopelandVotingRanking aggCope02 = new AggregatedCopelandVotingRanking(reasoners2, rankingCache1,0);
        AggregatedBordaVotingRanking aggBorda02 = new AggregatedBordaVotingRanking(reasoners2, rankingCache1,0 );
        AggregatedCopelandVotingRanking aggCope052 = new AggregatedCopelandVotingRanking(reasoners2, rankingCache1,0.5);
        AggregatedBordaVotingRanking aggBorda052 = new AggregatedBordaVotingRanking(reasoners2, rankingCache1,0.5 );
        AggregatedCopelandVotingRanking aggCope12 = new AggregatedCopelandVotingRanking(reasoners2, rankingCache1,1);
        AggregatedBordaVotingRanking aggBorda12 = new AggregatedBordaVotingRanking(reasoners2, rankingCache1,1);
        System.out.println("exCT");
        System.out.println(aggPlur2.getModel(exCT).toString());
        System.out.println(aggCope02.getModel(exCT).toString());
        System.out.println(aggBorda02.getModel(exCT).toString());
        System.out.println(aggCope052.getModel(exCT).toString());
        System.out.println(aggBorda052.getModel(exCT).toString());
        System.out.println(aggCope12.getModel(exCT).toString());
        System.out.println(aggBorda12.getModel(exCT).toString());
        System.out.println("exSCT");
        System.out.println(aggPlur2.getModel(exSCT).toString());
        System.out.println(aggCope02.getModel(exSCT).toString());
        System.out.println(aggBorda02.getModel(exSCT).toString());
        System.out.println(aggCope052.getModel(exSCT).toString());
        System.out.println(aggBorda052.getModel(exSCT).toString());
        System.out.println(aggCope12.getModel(exSCT).toString());
        System.out.println(aggBorda12.getModel(exSCT).toString());
        System.out.println("exCT1");
        System.out.println(aggPlur2.getModel(exCT1).toString());
        System.out.println(aggCope02.getModel(exCT1).toString());
        System.out.println(aggBorda02.getModel(exCT1).toString());
        System.out.println(aggCope052.getModel(exCT1).toString());
        System.out.println(aggBorda052.getModel(exCT1).toString());
        System.out.println(aggCope12.getModel(exCT1).toString());
        System.out.println(aggBorda12.getModel(exCT1).toString());
        System.out.println("exCT2");
        System.out.println(aggPlur2.getModel(exCT2).toString());
        System.out.println(aggCope02.getModel(exCT2).toString());
        System.out.println(aggBorda02.getModel(exCT2).toString());
        System.out.println(aggCope052.getModel(exCT2).toString());
        System.out.println(aggBorda052.getModel(exCT2).toString());
        System.out.println(aggCope12.getModel(exCT2).toString());
        System.out.println(aggBorda12.getModel(exCT2).toString());
        */
        /*

        List<AbstractRankingReasoner<?>> reasoners2 = List.of(dbs, bbs, strat);
        AggregatedPluralityVotingRanking aggPlur2 = new AggregatedPluralityVotingRanking(reasoners,rankingCache);
        AggregatedBordaVotingRanking aggBorda12 = new AggregatedBordaVotingRanking(reasoners,rankingCache,1);
        AggregatedBordaVotingRanking aggBorda052 = new AggregatedBordaVotingRanking(reasoners,rankingCache,0.5);
        AggregatedBordaVotingRanking aggBorda02 = new AggregatedBordaVotingRanking(reasoners,rankingCache,0);
        AggregatedCopelandVotingRanking aggCopeland12 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  1);
        AggregatedCopelandVotingRanking aggCopeland052 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  0.5);
        AggregatedCopelandVotingRanking aggCopeland02 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  0);

        aggPlur2.getModel(ex2CP);
        aggCopeland02.getModel(ex2CP);
        aggCopeland052.getModel(ex2CP);
        aggCopeland12.getModel(ex2CP);
        aggBorda02.getModel(ex2CP);
        aggBorda052.getModel(ex2CP);
        aggBorda12.getModel(ex2CP);

         */

/*
        List<AbstractRankingReasoner<?>> reasoners2 = List.of(cat, strat);

        System.out.println("exTest5:");
        System.out.println(aggCopeland1.getModel(exInCope1).toString());
        System.out.println(aggCopeland05.getModel(exInCope1).toString());
        System.out.println(aggCopeland0.getModel(exInCope1).toString());
        System.out.println("exTest6:");
        System.out.println(aggCopeland1.getModel(exInCope).toString());
        System.out.println(aggCopeland05.getModel(exInCope).toString());
        System.out.println(aggCopeland0.getModel(exInCope).toString());
        System.out.println("exTest7:");
        System.out.println(aggCopeland1.getModel(exInCope2).toString());
        System.out.println(aggCopeland05.getModel(exInCope2).toString());
        System.out.println(aggCopeland0.getModel(exInCope2).toString());

        System.out.println("exTest5:");
        aggCopeland1.getModel(exInCope1).printOrder();
        aggCopeland05.getModel(exInCope1).printOrder();
        aggCopeland0.getModel(exInCope1).printOrder();
        System.out.println("exTest6:");
        aggCopeland1.getModel(exInCope).printOrder();
        aggCopeland05.getModel(exInCope).printOrder();
        aggCopeland0.getModel(exInCope).printOrder();
        System.out.println("exTest7:");
        aggCopeland1.getModel(exInCope2).printOrder();
        aggCopeland05.getModel(exInCope2).printOrder();
        aggCopeland0.getModel(exInCope2).printOrder();

        System.out.println("strat:");

        System.out.println(cat.getModel(exInCope).toString());
        cat.getModel(exInCope).printOrder();
        System.out.println(dbs.getModel(exInCope).toString());
        System.out.println(bbs.getModel(exInCope).toString());
        System.out.println(strat.getModel(exInCope).toString());
        strat.getModel(exInCope).printOrder();

        RankingCache rankingCache1 = new RankingCache();
        AggregatedPluralityVotingRanking aggPlur2 = new AggregatedPluralityVotingRanking(reasoners2,rankingCache1);
        AggregatedCopelandVotingRanking aggCope2 = new AggregatedCopelandVotingRanking(reasoners2, rankingCache1);
        AggregatedBordaVotingRanking aggBorda2 = new AggregatedBordaVotingRanking(reasoners2, rankingCache1);
        System.out.println(aggCope2.getModel(plur_CopeAbs).toString());
        System.out.println(aggBorda2.getModel(plur_CopeAbs).toString());

        rankingCache1.prettyPrint();
        */
/*
        // Creating list of ranking semantics Cat, Dbs, Bbs and strat to aggregate
        SerialisableRankingReasoner ser = new SerialisableRankingReasoner();
        List<AbstractRankingReasoner<?>> reasonersSer = List.of(cat, strat, ser);

        //create a rankingCache to calculate the rankings of the used ranking semantics just once for each argumentation graph
        RankingCache rankingCache3 = new RankingCache();
        //aggregation semantics for plurality, Borda and Copeland over the four ranking semantics
        AggregatedPluralityVotingRanking aggPlurS = new AggregatedPluralityVotingRanking(reasoners,rankingCache);
        AggregatedBordaVotingRanking aggBorda1S = new AggregatedBordaVotingRanking(reasoners,rankingCache,1);
        AggregatedBordaVotingRanking aggBorda05S = new AggregatedBordaVotingRanking(reasoners,rankingCache,0.5);
        AggregatedBordaVotingRanking aggBorda0S = new AggregatedBordaVotingRanking(reasoners,rankingCache,0);
        AggregatedCopelandVotingRanking aggCopeland1S = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  1);
        AggregatedCopelandVotingRanking aggCopeland05S = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  0.5);
        AggregatedCopelandVotingRanking aggCopeland0S = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  0);

        System.out.println(aggPlurS.getModel(exVP).toString());
        aggBorda0S.getModel(exVP).printOrder();
        aggBorda1S.getModel(exVP).printOrder();
        System.out.println(ser.getModel(exVP).toString());
        */

    }
}

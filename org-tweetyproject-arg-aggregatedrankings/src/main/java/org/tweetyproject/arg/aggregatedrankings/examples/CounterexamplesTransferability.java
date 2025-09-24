package org.tweetyproject.arg.aggregatedrankings.examples;

import org.tweetyproject.arg.aggregatedrankings.reasoner.*;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.Attack;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.rankings.postulates.RankingPostulate;
import org.tweetyproject.arg.rankings.reasoner.*;
import org.tweetyproject.commons.postulates.Postulate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CounterexamplesTransferability {

    public static void main(String[] args) {
        Argument a = new Argument("a");
        Argument a0 = new Argument("a0");
        Argument a1 = new Argument("a1");
        Argument a2 = new Argument("a2");
        Argument a3 = new Argument("a3");
        Argument a4 = new Argument("a4");
        Argument a5 = new Argument("a5");
        Argument b = new Argument("b");
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

        // Counterexample for CT for plurality:
        //DungTheory exCTplur
        //[(a1,a1), (a1,a3), (a2,a1), (a2,a2), (a1,a0)]>

        // Counterexample for strat for CT, SCT and OE (Bonzon et al 2023): - löschen
        DungTheory exCT_SCT_OE_strat = new DungTheory();
        exCT_SCT_OE_strat.add(a, a1, a2, a3, a4, a5);
        exCT_SCT_OE_strat.add(new Attack(a, a1), new Attack(a1, a2), new Attack(a2, a3), new Attack(a3, a4), new Attack(a4, a5));

        //Transferability: Search for Counterexample for CT & SCT:
        // Counterexample for CT for Borda^1 with Cat, Dbs and Bbs:
        DungTheory exCT_T = new DungTheory();
        exCT_T.add(a0, a1, a2, a3, a4);
        exCT_T.add(new Attack(a1, a0), new Attack(a1, a1), new Attack(a1, a3), new Attack(a2, a0), new Attack(a2, a3), new Attack(a3, a0), new Attack(a3, a2), new Attack(a3, a3), new Attack(a4, a1), new Attack(a4, a2));

        DungTheory exCT_T1 = new DungTheory();
        exCT_T1.add(a0, b, a2, a3, a4);
        exCT_T1.add(new Attack(b, a0), new Attack(b, b), new Attack(b, a3), new Attack(a2, a0), new Attack(a2, a3), new Attack(a3, a0), new Attack(a3, a2), new Attack(a3, a3), new Attack(a4, b), new Attack(a4, a2));

        // Counterexample for SCT for Borda^1 with Cat, Dbs and Bbs:
        DungTheory exSCT_T = new DungTheory();
        exSCT_T.add(a0, a1, a2, a3, a4);
        exSCT_T.add(new Attack(a1, a0), new Attack(a1, a2), new Attack(a1, a4), new Attack(a2, a4), new Attack(a3, a0), new Attack(a3, a1), new Attack(a3, a2), new Attack(a4, a1), new Attack(a4, a4));
        // Counterexample for SCT for Borda^1 with Cat und Dbs:
        DungTheory exSCT_T0 = new DungTheory();
        exSCT_T0.add(a0, a1, a2, a3, a4);
        exSCT_T0.add(new Attack(a1, a0), new Attack(a1, a2), new Attack(a1, a3), new Attack(a2, a0), new Attack(a2, a3), new Attack(a3, a0), new Attack(a3, a1), new Attack(a3, a3), new Attack(a4, a1), new Attack(a4, a2));
        // Counterexample for SCT for Borda^1 with Cat und Dbs: -> TEST Umbenennung...
        DungTheory exSCT_T00 = new DungTheory();
        exSCT_T00.add(a0, b, a2, a3, a4);
        exSCT_T00.add(new Attack(b, a0), new Attack(b, a2), new Attack(b, a3), new Attack(a2, a0), new Attack(a2, a3), new Attack(a3, a0), new Attack(a3, b), new Attack(a3, a3), new Attack(a4, b), new Attack(a4, a2));

        // Counterexample for CT und OE for Plurality (with cat und dbs);
        DungTheory exCT_OE_pl = new DungTheory();
        exCT_OE_pl.add(a, b, c);
        exCT_OE_pl.add(new Attack(a, a), new Attack(a, b), new Attack(b, b), new Attack(b, c));

        // Counterexample for Borda^1 CT and OE:
        //a3 is not strictly more or equally acceptable than a4 (CT)
        DungTheory ex_CT_OE_B1 = new DungTheory();
        ex_CT_OE_B1.add(a, b, c, d, e);
        ex_CT_OE_B1.add(new Attack(a, a), new Attack(a, c), new Attack(a, d), new Attack(b, b), new Attack(b, a), new Attack(d, a), new Attack(e, b), new Attack(e, c), new Attack(e, d));
        //Counterexample found with generator for AggregatedBordaVotingRanking and Counter-Transitivity:?
        //<{ a1, a2, a3, a4, a0 },[(a1,a1), (a3,a3), (a1,a4), (a4,a1), (a2,a0), (a2,a3), (a2,a4), (a3,a1), (a1,a0)]>

        DungTheory exSCT_Borda = new DungTheory();
        exSCT_Borda.add(a1, a, a2, b, a3, c, a4, d, a5, e, a0);
        exSCT_Borda.add(new Attack(b, a3), new Attack(b, a4), new Attack(a1, a5), new Attack(a4, a), new Attack(c, a4), new Attack(a0, a), new Attack(a0, b), new Attack(a5, a), new Attack(a5, b), new Attack(d, a5), new Attack(a1, c), new Attack(a1, d), new Attack(b, a0), new Attack(a1, a0));

        // Search for Counterexample for CP for Copeland:
        DungTheory exCP_C = new DungTheory();
        exCP_C.add(a, b, c);
        exCP_C.add(new Attack(a, b), new Attack(b, a), new Attack(b, b), new Attack(b, c), new Attack(c, a));

        // CounterEx for QP - testing:
        DungTheory exQP_1 = new DungTheory();
        exQP_1.add(a, b, c, d, e, f, g);
        exQP_1.add(new Attack(b, f), new Attack(b, d), new Attack(a, d), new Attack(c, e));
        exQP_1.add(new Attack(g, b), new Attack(g, a));

        // Creating List of ranking semantics to aggregate
        CategorizerRankingReasoner cat = new CategorizerRankingReasoner(0.00001);
        DiscussionBasedRankingReasoner dbs = new DiscussionBasedRankingReasoner();
        BurdenBasedRankingReasoner bbs = new BurdenBasedRankingReasoner();
        StrategyBasedRankingReasoner strat = new StrategyBasedRankingReasoner();
        CountingRankingReasoner count = new CountingRankingReasoner();

        System.out.println("exCT_T");
        System.out.println(cat.getModel(exCT_T).toString());
        cat.getModel(exCT_T).printOrder();
        System.out.println(dbs.getModel(exCT_T).toString());
        System.out.println(bbs.getModel(exCT_T).toString());
        System.out.println(count.getModel(exCT_T).toString());
        count.getModel(exCT_T).printOrder();
        System.out.println("test");

        List<AbstractRankingReasoner<?>> reasoners = List.of(cat, dbs);//, bbs, count);
        List<AbstractRankingReasoner<?>> reasoners2 = List.of(cat, strat);

        List<AbstractRankingReasoner<?>> reasoners3 = List.of(cat, strat, count);

        List<AbstractRankingReasoner<?>> reasoners4 = List.of(cat, dbs);

        RankingCache rankingCache = new RankingCache();
        AggregatedPluralityVotingRanking aggPlur = new AggregatedPluralityVotingRanking(reasoners, rankingCache);
        AggregatedBordaVotingRanking aggBorda1 = new AggregatedBordaVotingRanking(reasoners, rankingCache, 1);
        AggregatedBordaVotingRanking aggBorda05 = new AggregatedBordaVotingRanking(reasoners, rankingCache, 0.5);
        AggregatedBordaVotingRanking aggBorda0 = new AggregatedBordaVotingRanking(reasoners, rankingCache, 0);
        AggregatedCopelandVotingRanking aggCopeland1 = new AggregatedCopelandVotingRanking(reasoners, rankingCache, 1);
        AggregatedCopelandVotingRanking aggCopeland05 = new AggregatedCopelandVotingRanking(reasoners, rankingCache, 0.5);
        AggregatedCopelandVotingRanking aggCopeland0 = new AggregatedCopelandVotingRanking(reasoners, rankingCache, 0);


        AggregatedPluralityVotingRanking aggPlur3 = new AggregatedPluralityVotingRanking(reasoners4, rankingCache);
        System.out.println("CT, OE plurality:");
        aggPlur3.getModel(exCT_OE_pl).printOrder();
        cat.getModel(exCT_OE_pl).printOrder();
        System.out.println(dbs.getModel(exCT_OE_pl).toString());
        System.out.println(dbs.getModel(exCT_OE_pl).toString());
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exCT_OE_pl, aggPlur3));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(exCT_OE_pl, aggPlur3));


        System.out.println("CP Copeland:");
        aggCopeland1.getModel(exCP_C).printOrder();
        aggCopeland05.getModel(exCP_C).printOrder();
        aggCopeland0.getModel(exCP_C).printOrder();
        rankingCache.prettyPrint();

        System.out.println("CT plurality");
        aggPlur.getModel(exCT_T).printOrder();
        Collection<RankingPostulate> all_postulates = new HashSet<RankingPostulate>();
        all_postulates.add(RankingPostulate.COUNTERTRANSITIVITY);
        all_postulates.add(RankingPostulate.STRICTCOUNTERTRANSITIVITY);
        all_postulates.add(RankingPostulate.ORDINALEQUIVALENCE);
        //PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(exCT_T,
        //        new CategorizerRankingReasoner());
        //evaluator.addAllPostulates(all_postulates);
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exCT_T, aggBorda1));
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exCT_T1, aggBorda1));

        aggBorda05.getModel(exSCT_T0).printOrder();
        System.out.println("TEEEEEEEEEEEEEST");

        System.out.println("CT Borda:");
        System.out.println(exSCT_Borda.toString());
        aggBorda1.getModel(exSCT_Borda).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, aggBorda1));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, aggBorda1));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(exSCT_Borda, aggBorda1));
        aggBorda05.getModel(exSCT_Borda).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, aggBorda05));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, aggBorda05));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(exSCT_Borda, aggBorda05));
        aggBorda0.getModel(exSCT_Borda).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, aggBorda0));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, aggBorda0));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(exSCT_Borda, aggBorda0));


        System.out.println("exSCT_Borda " + exSCT_Borda.toString());
        System.out.println("Counterexample for Argument Equivalence and (Ordinal) Equivalence for Plurality-aggregated, Copeland-aggregated and Borda-aggregated semantics");
        System.out.println("Argumentation Framework: " + exSCT_Borda.toString());
        System.out.println("Cat: " + cat.getModel(exSCT_Borda).toString());
        cat.getModel(exSCT_Borda).printOrder();
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, cat));
        System.out.println("Dbs: " + dbs.getModel(exSCT_Borda).toString());
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, dbs));
        System.out.println("Bbs: " + bbs.getModel(exSCT_Borda).toString());
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, bbs));
        System.out.println("Count: " + count.getModel(exSCT_Borda).toString());
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(exSCT_Borda, count));
        strat.getModel(exSCT_Borda).printOrder();
        System.out.println("Plurality: " + aggPlur.getModel(exSCT_Borda).toString());
        aggPlur.getModel(exSCT_Borda).printOrder();
        System.out.println("Copeland:");
        System.out.println("Copeland-0: " + aggCopeland0.getModel(exSCT_Borda).toString());
        aggCopeland0.getModel(exSCT_Borda).printOrder();
        System.out.println("Copeland-0.5: " + aggCopeland05.getModel(exSCT_Borda).toString());
        aggCopeland05.getModel(exSCT_Borda).printOrder();
        System.out.println("Copeland-1: " + aggCopeland1.getModel(exSCT_Borda).toString());
        aggCopeland1.getModel(exSCT_Borda).printOrder();
        System.out.println("Borda:");
        System.out.println("Borda-0: " + aggBorda0.getModel(exSCT_Borda).toString());
        aggBorda0.getModel(exSCT_Borda).printOrder();
        System.out.println("Borda-0.5: " + aggBorda05.getModel(exSCT_Borda).toString());
        aggBorda05.getModel(exSCT_Borda).printOrder();
        System.out.println("Borda-1: " + aggBorda1.getModel(exSCT_Borda).toString());
        aggBorda1.getModel(exSCT_Borda).printOrder();
        System.out.println("ex_CT_OE_B1:");
        aggBorda1.getModel(ex_CT_OE_B1).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggBorda1));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggBorda1));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(ex_CT_OE_B1, aggBorda1));
        aggBorda05.getModel(ex_CT_OE_B1).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggBorda05));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggBorda05));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(ex_CT_OE_B1, aggBorda05));
        aggBorda0.getModel(ex_CT_OE_B1).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggBorda0));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggBorda0));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(ex_CT_OE_B1, aggBorda0));

        System.out.println("Copeland:");

        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggCopeland1));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggCopeland1));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(ex_CT_OE_B1, aggCopeland1));
        aggCopeland1.getModel(ex_CT_OE_B1).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggCopeland05));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggCopeland05));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(ex_CT_OE_B1, aggCopeland05));
        aggCopeland05.getModel(ex_CT_OE_B1).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggCopeland0));
        System.out.println(RankingPostulate.STRICTCOUNTERTRANSITIVITY.isSatisfied(ex_CT_OE_B1, aggCopeland0));
        System.out.println(RankingPostulate.ORDINALEQUIVALENCE.isSatisfied(ex_CT_OE_B1, aggCopeland0));
        aggCopeland0.getModel(ex_CT_OE_B1).printOrder();
        rankingCache.prettyPrint();


        System.out.println("CT Copeland:");
        aggCopeland1.getModel(exCT_T).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exCT_T, aggCopeland1));
        aggCopeland05.getModel(exCT_T).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exCT_T, aggCopeland05));
        aggCopeland0.getModel(exCT_T).printOrder();
        System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exCT_T, aggCopeland0));

        rankingCache.prettyPrint();


        RankingCache rankingCache1 = new RankingCache();
        AggregatedPluralityVotingRanking aggPlur2 = new AggregatedPluralityVotingRanking(reasoners2, rankingCache1);
        AggregatedCopelandVotingRanking aggCope2 = new AggregatedCopelandVotingRanking(reasoners2, rankingCache1);
        AggregatedBordaVotingRanking aggBorda2 = new AggregatedBordaVotingRanking(reasoners2, rankingCache1);
        //System.out.println(aggCope2.getModel(plur_CopeAbs).toString());
        //System.out.println(aggBorda2.getModel(plur_CopeAbs).toString());

        RankingCache rankingCache2 = new RankingCache();
        AggregatedBordaVotingRanking aggBorda30 = new AggregatedBordaVotingRanking(reasoners3, rankingCache2, 0);
        AggregatedBordaVotingRanking aggBorda31 = new AggregatedBordaVotingRanking(reasoners3, rankingCache2, 1);
        AggregatedBordaVotingRanking aggBorda312 = new AggregatedBordaVotingRanking(reasoners3, rankingCache2, 0.5);

        printExample(aggCopeland1, exCT_SCT_OE_strat, reasoners, all_postulates);
        printExample(aggCopeland05, exCT_SCT_OE_strat, reasoners, all_postulates);
        printExample(aggCopeland0, exCT_SCT_OE_strat, reasoners, all_postulates);
        printExample(aggBorda1, exCT_SCT_OE_strat, reasoners, all_postulates);
        printExample(aggBorda05, exCT_SCT_OE_strat, reasoners, all_postulates);
        printExample(aggBorda0, exCT_SCT_OE_strat, reasoners, all_postulates);
        for (Postulate postulate : all_postulates) {
            System.out.println(postulate + ": " + postulate.isSatisfied(exCT_SCT_OE_strat, strat));
        }

    }


    public static void printExample(Voting agg, DungTheory dt, List<AbstractRankingReasoner<?>> reasoners, Collection<RankingPostulate> postulates) {

        System.out.println(agg + " for " + dt);
        agg.getModel(dt).printOrder();
        for (Postulate postulate : postulates) {
            System.out.println(postulate + ": " + postulate.isSatisfied(dt, agg));
        }
        for (AbstractRankingReasoner<?> reasoner : reasoners) {
            System.out.println(reasoner.getClass().getName() + ": " + reasoner.getModel(dt));
        }
    }

}

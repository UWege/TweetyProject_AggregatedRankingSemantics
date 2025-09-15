package org.tweetyproject.arg.aggregatedrankings.examples;

import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedBordaVotingRanking;
import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedCopelandVotingRanking;
import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedPluralityVotingRanking;
import org.tweetyproject.arg.aggregatedrankings.reasoner.RankingCache;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.Attack;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.rankings.postulates.RankingPostulate;
import org.tweetyproject.arg.rankings.reasoner.*;
import org.tweetyproject.commons.postulates.PostulateEvaluator;

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
            Argument a4= new Argument("a4");
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

            //Transferability: Search for Counterexample for CT & SCT:
            // Counterexample for CT for Borda^1 with Cat, Dbs and Bbs:
            DungTheory exCT_T = new DungTheory();
            exCT_T.add(a0,a1,a2,a3,a4);
            exCT_T.add(new Attack(a1,a0), new Attack(a1,a1),new Attack(a1,a3),new Attack(a2,a0),new Attack(a2,a3),new Attack(a3,a0),new Attack(a3,a2),new Attack(a3,a3),new Attack(a4,a1),new Attack(a4,a2));

            DungTheory exCT_T1 = new DungTheory();
            exCT_T1.add(a0,b,a2,a3,a4);
            exCT_T1.add(new Attack(b,a0), new Attack(b,b),new Attack(b,a3),new Attack(a2,a0),new Attack(a2,a3),new Attack(a3,a0),new Attack(a3,a2),new Attack(a3,a3),new Attack(a4,b),new Attack(a4,a2));

            // Counterexample for SCT for Borda^1 with Cat, Dbs and Bbs:
            DungTheory exSCT_T = new DungTheory();
            exSCT_T.add(a0,a1,a2,a3,a4);
            exSCT_T.add(new Attack(a1,a0),new Attack(a1,a2), new Attack(a1,a4), new Attack(a2,a4),new Attack(a3,a0),new Attack(a3,a1),new Attack(a3,a2),new Attack(a4,a1),new Attack(a4,a4));
            // Counterexample for SCT for Borda^1 with Cat und Dbs:
            DungTheory exSCT_T0 = new DungTheory();
            exSCT_T0.add(a0,a1,a2,a3,a4);
            exSCT_T0.add(new Attack(a1,a0), new Attack(a1,a2), new Attack(a1,a3), new Attack(a2,a0), new Attack(a2,a3), new Attack(a3,a0), new Attack(a3,a1), new Attack(a3,a3), new Attack(a4,a1), new Attack(a4,a2));
            // Counterexample for SCT for Borda^1 with Cat und Dbs: -> TEST Umbenennung...
            DungTheory exSCT_T00 = new DungTheory();
            exSCT_T00.add(a0,b,a2,a3,a4);
            exSCT_T00.add(new Attack(b,a0), new Attack(b,a2), new Attack(b,a3), new Attack(a2,a0), new Attack(a2,a3), new Attack(a3,a0), new Attack(a3,b), new Attack(a3,a3), new Attack(a4,b), new Attack(a4,a2));

            // Search for Counterexample for CP for Copeland:
            DungTheory exCP_C = new DungTheory();
            exCP_C.add(a,b,c);
            exCP_C.add(new Attack(a,b),new Attack(b,a),new Attack(b,b),new Attack(b,c),new Attack(c,a));

            // CounterEx for QP - testing:
            DungTheory exQP_1 = new DungTheory();
            exQP_1.add(a,b,c,d,e,f,g);
            exQP_1.add(new Attack(b,f),new Attack(b,d),new Attack(a,d),new Attack(c,e));
            exQP_1.add(new Attack(g,b),new Attack(g,a));

            // Creating List of ranking semantics to aggregate
            CategorizerRankingReasoner cat = new CategorizerRankingReasoner(0.00001);
            DiscussionBasedRankingReasoner dbs = new DiscussionBasedRankingReasoner();
            BurdenBasedRankingReasoner bbs = new BurdenBasedRankingReasoner();
            StrategyBasedRankingReasoner strat = new StrategyBasedRankingReasoner();
            CountingRankingReasoner count = new CountingRankingReasoner();

            mbsCB mbs = new mbsCB();

            System.out.println("exQP_1:");
            System.out.println(mbs.getModel(exQP_1).toString());

            System.out.println("exCT_T");
            System.out.println(cat.getModel(exCT_T).toString());
            cat.getModel(exCT_T).printOrder();
            System.out.println(dbs.getModel(exCT_T).toString());
            System.out.println(bbs.getModel(exCT_T).toString());
            System.out.println(count.getModel(exCT_T).toString());
            count.getModel(exCT_T).printOrder();
            System.out.println("test");

            List<AbstractRankingReasoner<?>> reasoners = List.of(strat, dbs, bbs);
            List<AbstractRankingReasoner<?>> reasoners2 = List.of(cat, strat);

            List<AbstractRankingReasoner<?>> reasoners3 = List.of(cat, strat, count);

            RankingCache rankingCache = new RankingCache();
            AggregatedPluralityVotingRanking aggPlur = new AggregatedPluralityVotingRanking(reasoners,rankingCache);
            AggregatedBordaVotingRanking aggBorda1 = new AggregatedBordaVotingRanking(reasoners,rankingCache,1);
            AggregatedBordaVotingRanking aggBorda05 = new AggregatedBordaVotingRanking(reasoners, rankingCache, 0.5);
            AggregatedBordaVotingRanking aggBorda0 = new AggregatedBordaVotingRanking(reasoners, rankingCache, 0);
            AggregatedCopelandVotingRanking aggCopeland1 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  1);
            AggregatedCopelandVotingRanking aggCopeland05 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  0.5);
            AggregatedCopelandVotingRanking aggCopeland0 = new AggregatedCopelandVotingRanking(reasoners,rankingCache,  0);

            System.out.println("CP Copeland:");
            aggCopeland1.getModel(exCP_C).printOrder();
            aggCopeland05.getModel(exCP_C).printOrder();
            aggCopeland0.getModel(exCP_C).printOrder();
            rankingCache.prettyPrint();
            System.out.println("TEEEEEEEST CP_C");

            System.out.println("CT plurality");
            aggPlur.getModel(exCT_T).printOrder();
            Collection<RankingPostulate>  all_postulates = new HashSet<RankingPostulate>();
            all_postulates.add(RankingPostulate.COUNTERTRANSITIVITY);
            all_postulates.add(RankingPostulate.STRICTCOUNTERTRANSITIVITY);
            //PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(exCT_T,
            //        new CategorizerRankingReasoner());
            //evaluator.addAllPostulates(all_postulates);
            System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exCT_T,aggBorda1));
            System.out.println(RankingPostulate.COUNTERTRANSITIVITY.isSatisfied(exCT_T1,aggBorda1));

            aggBorda05.getModel(exSCT_T0).printOrder();
            System.out.println("TEEEEEEEEEEEEEST");

            System.out.println("CT Borda:");
            aggBorda1.getModel(exCT_T).printOrder();
            aggBorda05.getModel(exCT_T).printOrder();
            aggBorda0.getModel(exCT_T).printOrder();
            rankingCache.prettyPrint();

            System.out.println("CT Copeland:");
            aggCopeland1.getModel(exCT_T).printOrder();
            aggCopeland05.getModel(exCT_T).printOrder();
            aggCopeland0.getModel(exCT_T).printOrder();
            rankingCache.prettyPrint();


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


        }

    }



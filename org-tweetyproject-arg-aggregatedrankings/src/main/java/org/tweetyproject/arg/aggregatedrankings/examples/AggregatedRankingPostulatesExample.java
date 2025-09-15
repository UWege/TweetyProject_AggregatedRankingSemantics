package org.tweetyproject.arg.aggregatedrankings.examples;

import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedBordaVotingRanking;
import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedCopelandVotingRanking;
import org.tweetyproject.arg.aggregatedrankings.reasoner.AggregatedPluralityVotingRanking;

import org.tweetyproject.arg.aggregatedrankings.reasoner.RankingCache;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.dung.util.DungTheoryGenerator;
import org.tweetyproject.arg.dung.util.EnumeratingDungTheoryGenerator;
import org.tweetyproject.arg.rankings.postulates.RankingPostulate;
import org.tweetyproject.arg.rankings.reasoner.*;
import org.tweetyproject.commons.BeliefSet;
import org.tweetyproject.commons.postulates.PostulateEvaluatable;
import org.tweetyproject.commons.postulates.PostulateEvaluator;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 * Example code for evaluating aggregated ranking semantics in regard to postulates.
 * Each postulate represents a single property that characterizes how the semantics ranks arguments.
 *
 * @author Anna Gessler and Ulla Wege
 */
public class AggregatedRankingPostulatesExample {

        private static Collection<RankingPostulate> all_postulates;

        /**
         * Main method that initializes the postulates and runs the different ranking reasoner examples.
         *
         * @param args Command line arguments (not used)
         */
        public static void main(String[] args) {
            all_postulates = new HashSet<RankingPostulate>();
            //all_postulates.add(RankingPostulate.ABSTRACTION);
            //all_postulates.add(RankingPostulate.ADDITIONOFATTACKBRANCH);
            //all_postulates.add(RankingPostulate.ADDITIONOFDEFENSEBRANCH);
            //all_postulates.add(RankingPostulate.ATTACKVSFULLDEFENSE);
            //all_postulates.add(RankingPostulate.CARDINALITYPRECEDENCE);
            //all_postulates.add(RankingPostulate.COUNTERTRANSITIVITY);
            //all_postulates.add(RankingPostulate.DEFENSEPRECEDENCE);
            //all_postulates.add(RankingPostulate.DISTDEFENSEPRECEDENCE);
            //all_postulates.add(RankingPostulate.INCREASEOFATTACKBRANCH);
            //all_postulates.add(RankingPostulate.INCREASEOFDEFENSEBRANCH);
            //all_postulates.add(RankingPostulate.INDEPENDENCE);
            //all_postulates.add(RankingPostulate.NONATTACKEDEQUIVALENCE);
            all_postulates.add(RankingPostulate.QUALITYPRECEDENCE);
            //all_postulates.add(RankingPostulate.SELFCONTRADICTION);
            //all_postulates.add(RankingPostulate.STRICTADDITIONOFDEFENSEBRANCH);
            //all_postulates.add(RankingPostulate.STRICTCOUNTERTRANSITIVITY);
            //all_postulates.add(RankingPostulate.TOTAL);
            //all_postulates.add(RankingPostulate.VOIDPRECEDENCE);
            //all_postulates.add(RankingPostulate.WEAKVOIDPRECEDENCE);
            //all_postulates.add(RankingPostulate.ORDINALEQUIVALENCE);

            //CategorizerExample();
            //BurdenExample();
            //DiscussionExample();
            //TuplesExample();
            //StrategyBasedExample();
            //SAFExample();
            //CountingExample();
            //PropagationExample();
            //SerialisableRankingReasoner();

            CategorizerRankingReasoner cat = new CategorizerRankingReasoner(0.00001);
            DiscussionBasedRankingReasoner dbs = new DiscussionBasedRankingReasoner();
            BurdenBasedRankingReasoner bbs = new BurdenBasedRankingReasoner();
            StrategyBasedRankingReasoner strat = new StrategyBasedRankingReasoner();
            PropagationRankingReasoner propa2 = new PropagationRankingReasoner(0.75, false,
                    PropagationRankingReasoner.PropagationSemantics.PROPAGATION2);
            PropagationRankingReasoner propa2m = new PropagationRankingReasoner(0.75, true, PropagationRankingReasoner.PropagationSemantics.PROPAGATION2);

            mbsCB mbs = new mbsCB();
            tbsCB tbs = new tbsCB();
            isCB is = new isCB();

            SyntaxRankingReasoner syn = new SyntaxRankingReasoner();

            CountingRankingReasoner count = new CountingRankingReasoner();
            //System.out.println("propagation example..");
            //PropagationExample();

            //List<AbstractRankingReasoner<?>> reasoners = List.of(cat, dbs, syn);
            //List<AbstractRankingReasoner<?>> reasoners = List.of(cat, dbs, count);
            List<AbstractRankingReasoner<?>> reasoners = List.of(mbs, tbs, is);

            RankingCache rankingCache = new RankingCache();
            //aggregatedPluralityExample(reasoners, rankingCache);
            //aggregatedCopelandExample(reasoners, rankingCache, 1);
            //aggregatedBordaExample(reasoners, rankingCache, 1);

            aggregatedCopelandExample(reasoners, rankingCache, 0.5);
            aggregatedBordaExample(reasoners, rankingCache, 0.5);

            aggregatedCopelandExample(reasoners, rankingCache, 0);
            aggregatedBordaExample(reasoners, rankingCache, 0);


            aggregatedCopelandExample(reasoners, null, 1);
            aggregatedBordaExample(reasoners, null, 1);

            //aggregatedCopelandExample(reasoners, null, 0.5);
            //aggregatedBordaExample(reasoners, null, 0.5);
            //aggregatedCopelandExample(reasoners, null, 0);

            //aggregatedCopelandExample(reasoners, null, 0);
            //aggregatedBordaExample(reasoners, null, 0.5);

            //StrategyBasedExample();

        }

        /**
         * Evaluates the CategorizerRankingReasoner against all postulates and prints the results.
         */
        public static void CategorizerExample() {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                    new CategorizerRankingReasoner());
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(4000, false).prettyPrint());
        }

        /**
         * Evaluates the BurdenBasedRankingReasoner against all postulates and prints the results.
         */
        public static void BurdenExample() {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                    new BurdenBasedRankingReasoner());
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(100, false).prettyPrint());
        }

        /**
         * Evaluates the DiscussionBasedRankingReasoner against all postulates and prints the results.
         */
        public static void DiscussionExample() {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                    new DiscussionBasedRankingReasoner());
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(2000, false).prettyPrint());
        }

        /**
         * Evaluates the TuplesRankingReasoner against all postulates and prints the results.
         */
        public static void TuplesExample() {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                    new TuplesRankingReasoner());
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(4000, false).prettyPrint());
        }

        /**
         * Evaluates the StrategyBasedRankingReasoner against all postulates and prints the results.
         */
        public static void StrategyBasedExample() {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                    new StrategyBasedRankingReasoner());
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(100, false).prettyPrint());
        }

        /**
         * Evaluates the SAFRankingReasoner against all postulates and prints the results.
         */
        public static void SAFExample() {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                    new SAFRankingReasoner());
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(2000, false).prettyPrint());
        }

        /**
         * Evaluates the CountingRankingReasoner against all postulates and prints the results.
         */
        public static void CountingExample() {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                    new CountingRankingReasoner());
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(1000, false).prettyPrint());
        }

        /**
         * Evaluates the PropagationRankingReasoner against all postulates and prints the results.
         */
        public static void PropagationExample() {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PropagationRankingReasoner propagation_reasoner = new PropagationRankingReasoner(0.75, false,
                    PropagationRankingReasoner.PropagationSemantics.PROPAGATION2);
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg, propagation_reasoner);
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(2000, false).prettyPrint());
        }

    /**
     * Evaluates the SerialisableRankingReasoner against all postulates and prints the results.
     */
    public static void SerialisableRankingReasoner() {
        DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
        PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                new SerialisableRankingReasoner());
        evaluator.addAllPostulates(all_postulates);
        System.out.println(evaluator.evaluate(10000, false).prettyPrint());
    }

        public static void aggregatedPluralityExample(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCache) {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
                    new AggregatedPluralityVotingRanking(reasoners, rankingCache));
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(10000, true).prettyPrint());
        }

        public static void aggregatedCopelandExample(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCache, double alpha) {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator;
            if(rankingCache==null){
                evaluator = new PostulateEvaluator<>(dg,
                        new AggregatedCopelandVotingRanking(reasoners, alpha));
            }else{
                evaluator = new PostulateEvaluator<>(dg,
                        new AggregatedCopelandVotingRanking(reasoners, rankingCache, alpha));
            }
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(300000, true).prettyPrint());
        }

        public static void aggregatedBordaExample(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCache, double alpha) {
            DungTheoryGenerator dg = new EnumeratingDungTheoryGenerator();
            PostulateEvaluator<Argument, DungTheory> evaluator;
            if(rankingCache==null){
                evaluator = new PostulateEvaluator<>(dg,
                        new AggregatedBordaVotingRanking(reasoners, alpha));
            }else{
                evaluator = new PostulateEvaluator<>(dg,
                        new AggregatedBordaVotingRanking(reasoners, rankingCache, alpha));
            }
            //PostulateEvaluator<Argument, DungTheory> evaluator = new PostulateEvaluator<>(dg,
            //        new AggregatedBordaVotingRanking(reasoners, rankingCache, alpha));
            evaluator.addAllPostulates(all_postulates);
            System.out.println(evaluator.evaluate(300000, true).prettyPrint());
        }


}

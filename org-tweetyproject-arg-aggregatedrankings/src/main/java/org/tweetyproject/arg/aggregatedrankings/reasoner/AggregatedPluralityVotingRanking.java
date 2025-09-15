/*
 *  This file is part of "TweetyProject", a collection of Java libraries for
 *  logical aspects of artificial intelligence and knowledge representation.
 *
 *  TweetyProject is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License version 3 as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright 2018 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
package org.tweetyproject.arg.aggregatedrankings.reasoner;

import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.rankings.reasoner.AbstractRankingReasoner;
import org.tweetyproject.comparator.GeneralComparator;
import org.tweetyproject.comparator.NumericalPartialOrder;

import java.util.Collection;
import java.util.List;

public class AggregatedPluralityVotingRanking extends Voting {

    public AggregatedPluralityVotingRanking(List<AbstractRankingReasoner<?>> reasoners) {
        super(reasoners);
    }

    public AggregatedPluralityVotingRanking(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCash) {
        super(reasoners,rankingCash);
    }

    @Override
    public boolean isInstalled() {
        return false;
    }

    @Override
    public Collection<NumericalPartialOrder<Argument, DungTheory>> getModels(DungTheory arguments) {
        return List.of(this.getModel(arguments));
    }

    public NumericalPartialOrder<Argument, DungTheory> getModel22(DungTheory arguments) {

        NumericalPartialOrder<Argument, DungTheory> agg_ranking = new NumericalPartialOrder<Argument, DungTheory>();
        agg_ranking.setSortingType(NumericalPartialOrder.SortingType.DESCENDING);

        for (Argument a : arguments) {
            agg_ranking.put(a, 0.0);
        }
        GeneralComparator<Argument, DungTheory> model;

        for (AbstractRankingReasoner<?> reasoner : reasoners) {
            //if (model != null) { //Tuples gibt voll oft 0 aus als model!
            // Only the "best" arguments with no other argument better get a point for each reasoner
            model = rankingCache.getRanking(reasoner, arguments);
            int points = 1;
            for (Argument a : arguments) {
                points = 1;
                for (Argument b : arguments) {
                    if (!model.isIncomparable(a, b) && a != b) {
                        if (!model.isStrictlyMoreOrEquallyAcceptableThan(a, b)) {
                            points = 0;
                            break;
                        }
                    }
                }

                if (points != 0)
                    agg_ranking.put(a, 1.0);
            }

        }


        return agg_ranking;
    }

    @Override
    public NumericalPartialOrder<Argument, DungTheory> getModel(DungTheory arguments) {

        NumericalPartialOrder<Argument, DungTheory> ranking = new NumericalPartialOrder<Argument, DungTheory>();
        ranking.setSortingType(NumericalPartialOrder.SortingType.DESCENDING);

        for (Argument a : arguments) {
            ranking.put(a, 0.0);
        }

        GeneralComparator<Argument, DungTheory> model;
        for (AbstractRankingReasoner<?> reasoner : reasoners) {
            model = rankingCache.getRanking(reasoner, arguments);
            if (model == null) //Tuples gibt voll oft 0 aus als model!
                return null;
            // Only the "best" arguments with no other argument better get a point for each reasoner
            int points = 1;
            for (Argument a : arguments) {
                points = 1;
                for (Argument b : arguments) {
                    if (!model.isIncomparable(a, b) && a != b) {
                        if (!model.isStrictlyMoreOrEquallyAcceptableThan(a, b)) {
                            points = 0;
                            break;
                        }
                    }
                }

                if (points != 0)
                    ranking.put(a, ranking.get(a)+1.0);
            }

        }


        //das wieder löschen!
        /*System.out.println("Plurality: " +ranking.toString());
        System.out.println(arguments.toString());
        ranking.printOrder();
        for (AbstractRankingReasoner<?> reasoner : reasoners) {
            model = rankingCache.getRanking(reasoner, arguments);
            System.out.println(reasoner.getClass()+": "+model.toString());
        }
        System.out.println("\n");
*/

        return ranking;
    }
}

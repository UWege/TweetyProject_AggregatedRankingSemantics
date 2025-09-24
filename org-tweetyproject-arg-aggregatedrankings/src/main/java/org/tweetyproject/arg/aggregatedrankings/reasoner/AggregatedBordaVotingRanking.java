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
import org.tweetyproject.comparator.LatticePartialOrder;
import org.tweetyproject.comparator.NumericalPartialOrder;

import java.util.Collection;
import java.util.List;

public class AggregatedBordaVotingRanking extends Voting {

    double alpha = 0;

    public AggregatedBordaVotingRanking(List<AbstractRankingReasoner<?>> reasoners) {
        super(reasoners);
    }

    public AggregatedBordaVotingRanking(List<AbstractRankingReasoner<?>> reasoners, double alpha) {
        super(reasoners);
        this.alpha = alpha;
    }

    public AggregatedBordaVotingRanking(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCash) {
        super(reasoners,rankingCash);
    }

    public AggregatedBordaVotingRanking(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCash, double alpha) {
        super(reasoners, rankingCash);
        this.alpha = alpha;
    }

    @Override
    public boolean isInstalled() {
        return false;
    }

    @Override
    public Collection<NumericalPartialOrder<Argument, DungTheory>> getModels(DungTheory arguments) {
        return List.of(this.getModel(arguments));
    }

    public static NumericalPartialOrder<Argument, DungTheory> getRanking(DungTheory arguments, List<GeneralComparator<Argument, DungTheory>> rankings, double alpha) {

        NumericalPartialOrder<Argument, DungTheory> agg_ranking = new NumericalPartialOrder<>();
        agg_ranking.setSortingType(NumericalPartialOrder.SortingType.DESCENDING);

        for (Argument a : arguments) {
            agg_ranking.put(a, 0.0);
        }

        for (GeneralComparator<Argument,DungTheory> model : rankings) {
            // Each argument gets a point whenever it is strictly more acceptable than another argument;
            // it gets alpha points, if it is equally acceptable than another argument
            double points;
            for (Argument a : arguments) {
                points = 0;
                for (Argument b : arguments) {
                    if (!model.isIncomparable(a, b) && a != b) {
                        if (model.isStrictlyMoreAcceptableThan(a, b)) {
                            points += 1;
                        } else if (model.isEquallyAcceptableThan(a, b)) {
                            points += alpha;
                        }
                    }
                }
                agg_ranking.put(a, agg_ranking.get(a) + points);
            }

        }
        return agg_ranking;
    }

    @Override
    public NumericalPartialOrder<Argument, DungTheory> getModel(DungTheory arguments) {

        NumericalPartialOrder<Argument, DungTheory> ranking = new NumericalPartialOrder<>();
        ranking.setSortingType(NumericalPartialOrder.SortingType.DESCENDING);

        for (Argument a : arguments) {
            ranking.put(a, 0.0);
        }

        GeneralComparator<Argument,DungTheory> model;
        for (AbstractRankingReasoner<?> reasoner : reasoners) {
            model = rankingCache.getRanking(reasoner, arguments);//model = reasoner.getModel(arguments);
            if(model == null)
                return null;
            // Each argument gets a point whenever it is strictly more acceptable than another argument;
            // it gets alpha points, if it is equally acceptable than another argument
            double points;
            for (Argument a : arguments) {
                points = 0;
                for (Argument b : arguments) {
                    if (!model.isIncomparable(a, b) && a != b) {
                        if (model.isStrictlyMoreAcceptableThan(a, b)) {
                            points += 1;
                        } else if (model.isEquallyAcceptableThan(a, b)) {
                            points += alpha;
                        }
                    }
                }
                ranking.put(a, ranking.get(a) + points);
            }

        }

        return ranking;
    }
}

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AggregatedCopelandVotingRanking extends Voting {

    double alpha = 0.5;

    public AggregatedCopelandVotingRanking(List<AbstractRankingReasoner<?>> reasoners) {
        super(reasoners);
    }

    public AggregatedCopelandVotingRanking(List<AbstractRankingReasoner<?>> reasoners, double alpha) {
        super(reasoners);
        this.alpha = alpha;
    }

    public AggregatedCopelandVotingRanking(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCash) {
        super(reasoners,rankingCash);
    }

    public AggregatedCopelandVotingRanking(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCash, double alpha) {
        super(reasoners,rankingCash);
        this.alpha = alpha;
    }

    public void setAlpha(double newAlpha) {
        this.alpha = newAlpha;
    }

    @Override

    public boolean isInstalled() {
        return false;
    }

    @Override
    public Collection<NumericalPartialOrder<Argument, DungTheory>> getModels(DungTheory arguments) {
        return List.of(this.getModel(arguments));
    }

    public static NumericalPartialOrder<Argument, DungTheory> getRanking(DungTheory arguments, ArrayList<GeneralComparator<Argument, DungTheory>> rankings, double alpha) {

        ArrayList<Argument> argList = new ArrayList<>(arguments);
        int m = argList.size();
        double[] score = new double[m];


        for (int i = 0; i < m - 1; i++) {
            Argument ai = argList.get(i);
            for (int j = i + 1; j < m; j++) {
                int aggi = 0;
                int aggj = 0;
                Argument aj = argList.get(j);
                // compare Arguments i and j in each Ranking
                for (GeneralComparator<Argument, DungTheory> model : rankings) {
                    if (model.isStrictlyMoreAcceptableThan(ai, aj)) {
                        aggi += 1;
                    } else if (model.isStrictlyLessAcceptableThan(ai, aj)) {
                        aggj += 1;
                    }
                }
                // save scores
                if (aggi > aggj) {
                    score[i] += 1;
                } else if (aggi < aggj) {
                    score[j] += 1;
                } else { //equal
                    score[i] += alpha;
                    score[j] += alpha;
                }
            }
        }

        // make a ranking according to the scores
        NumericalPartialOrder<Argument, DungTheory> agg_ranking = new NumericalPartialOrder<>();
        agg_ranking.setSortingType(NumericalPartialOrder.SortingType.DESCENDING);

        for (int i = 0; i < m; i++) {
            agg_ranking.put(argList.get(i), score[i]);
        }


        return agg_ranking;
    }

    @Override
    public NumericalPartialOrder<Argument, DungTheory> getModel(DungTheory arguments) {

        ArrayList<Argument> argList = new ArrayList<>(arguments);
        int m = argList.size();
        double[] score = new double[m];
        GeneralComparator<Argument, DungTheory> model;


        for (int i = 0; i < m - 1; i++) {
            Argument ai = argList.get(i);
            for (int j = i + 1; j < m; j++) {
                int aggi = 0;
                int aggj = 0;
                Argument aj = argList.get(j);
                // compare Arguments i and j in each Ranking
                for (AbstractRankingReasoner<?> reasoner : reasoners) {
                    model = rankingCache.getRanking(reasoner, arguments);
                    if(model == null)
                        return null;
                    if (model.isStrictlyMoreAcceptableThan(ai, aj)) {
                        aggi += 1;
                    } else if (model.isStrictlyLessAcceptableThan(ai, aj)) {
                        aggj += 1;
                    }
                }
                // save scores
                if (aggi > aggj) {
                    score[i] += 1;
                } else if (aggi < aggj) {
                    score[j] += 1;
                } else { //equal
                    score[i] += alpha;
                    score[j] += alpha;
                }
            }
        }

        // make a ranking according to the scores
        NumericalPartialOrder<Argument, DungTheory> ranking = new NumericalPartialOrder<>();
        ranking.setSortingType(NumericalPartialOrder.SortingType.DESCENDING);

        for (int i = 0; i < m; i++) {
            ranking.put(argList.get(i), score[i]);
        }

        return ranking;
    }
}


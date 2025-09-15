package org.tweetyproject.arg.aggregatedrankings.reasoner;

import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.rankings.reasoner.AbstractRankingReasoner;
import org.tweetyproject.comparator.NumericalPartialOrder;

import java.util.List;

public abstract class Voting extends AbstractRankingReasoner<NumericalPartialOrder<Argument, DungTheory>> {

    List<AbstractRankingReasoner<?>> reasoners;
    protected final RankingCache rankingCache;

    public Voting(List<AbstractRankingReasoner<?>> reasoners) {
        this.reasoners = reasoners;
        this.rankingCache = new RankingCache();
    }

    public Voting(List<AbstractRankingReasoner<?>> reasoners, RankingCache rankingCache) {
        this.reasoners = reasoners;
        this.rankingCache = rankingCache;
    }

}

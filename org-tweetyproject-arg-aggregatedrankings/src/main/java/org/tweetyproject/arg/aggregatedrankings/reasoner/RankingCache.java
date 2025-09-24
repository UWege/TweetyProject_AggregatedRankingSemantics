package org.tweetyproject.arg.aggregatedrankings.reasoner;

import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.rankings.reasoner.AbstractRankingReasoner;
import org.tweetyproject.commons.util.Pair;
import org.tweetyproject.comparator.GeneralComparator;

import java.util.HashMap;
import java.util.Map;

public class RankingCache {

    Map<Pair<AbstractRankingReasoner<?>, DungTheory>, GeneralComparator<Argument, DungTheory>> rankingCache = new HashMap<>();

    public GeneralComparator<Argument, DungTheory> getRanking(AbstractRankingReasoner<?> reasoner, DungTheory dt) {
        Pair<AbstractRankingReasoner<?>, DungTheory> pair = new Pair<>(reasoner, dt);
        GeneralComparator<Argument, DungTheory> model = rankingCache.get(pair);
        if (model == null) {
            model = reasoner.getModel(dt);
            rankingCache.put(pair, model);
        }
        return model;
    }


    public void prettyPrint() {
        String s = new String("ranking Cache:");
        rankingCache.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
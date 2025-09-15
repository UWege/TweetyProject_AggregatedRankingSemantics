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
 *  Copyright 2019 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
package org.tweetyproject.arg.rankings.postulates;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.rankings.reasoner.AbstractRankingReasoner;
import org.tweetyproject.comparator.GeneralComparator;

/**
 * "equivalence"/"ordinal equivalence" postulate for ranking semantics
 *
 * @author Ulla Wege
 *
 */
public class RaOrdinalEquivalence extends RankingPostulate{

    @Override
    public String getName() {
        return "Ordinal Equivalence";
    }

    @Override
    public boolean isApplicable(Collection<Argument> kb) {
        return (kb instanceof DungTheory && kb.size()>=2);
    }

    @Override
    public boolean isSatisfied(Collection<Argument> kb, AbstractRankingReasoner<GeneralComparator<Argument, DungTheory>> ev) {
        if (!this.isApplicable(kb))
            return true;
        if (ev.getModel((DungTheory) kb) == null)
            return true;

        DungTheory dt = new DungTheory((DungTheory) kb);
        GeneralComparator<Argument, DungTheory> ranking = ev.getModel((DungTheory) dt);
        boolean bijection = false;
        for (Argument a : dt) {
            for (Argument b : dt) {
                bijection = false;
                if (a == b)
                    continue;

                Set<Argument> atta = dt.getAttackers(a);
                Set<Argument> attb = dt.getAttackers(b);
                if (atta.size() != attb.size())
                    continue;
                if (atta.isEmpty())
                    return ranking.isEquallyAcceptableThan(a, b);

                //Sort the attackers of a and attackers of b according to the ranking
                List<Argument> attLista = atta.stream().sorted((Argument x, Argument y) -> ranking.isStrictlyMoreOrEquallyAcceptableThan(x, y) ? 1 : -1).collect(Collectors.toList());
                List<Argument> attListb = attb.stream().sorted((Argument x, Argument y) -> ranking.isStrictlyMoreOrEquallyAcceptableThan(x, y) ? 1 : -1).collect(Collectors.toList());
                //Compare whether there is a bijection between the two sorted lists with equally strong elements
                for (int i = 0; i < attLista.size(); i++) {
                    if (!ranking.isEquallyAcceptableThan(attLista.get(i), attListb.get(i))) {
                        bijection = false;
                        break;
                    }
                    bijection = true;
                }
                //ordinal equivalence requirements
                if (bijection && !ranking.isEquallyAcceptableThan(a, b))
                    return false;

            }
        }
        return true;
    }



    /** Default Constructor */
    public RaOrdinalEquivalence(){}
}

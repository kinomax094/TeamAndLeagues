package pl.com.b2bnetwork.football.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.com.b2bnetwork.football.domain.Player;
import pl.com.b2bnetwork.football.dto.QueryArgsForFindPlayer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class QueryRepositoryPlayerImpl implements QueryRepositoryPlayer {

    @Autowired
    private EntityManager em;


    @Override
    public Optional<List<Player>> findPlayers(final QueryArgsForFindPlayer queryArgsForFindPlayer) {
        List<Player> result;
        String name = queryArgsForFindPlayer.getName();
        String country = queryArgsForFindPlayer.getCountry();
        Integer type = queryArgsForFindPlayer.getType();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root<Player> player = criteriaQuery.from(Player.class);
        int number = 3;
        Predicate[] tab = new Predicate[number];
        int i = 0;
        if (name != null) {
            if (!name.isEmpty()) {
                Predicate namePredicate = criteriaBuilder.like(player.get("name"), "%" + name + "%");
                tab[i] = namePredicate;
                i++;
            }
        }
        if (country != null) {
            if (!country.isEmpty()) {
                Predicate countryPredicate = criteriaBuilder.equal(player.get("playerCountry"), country);
                tab[i] = countryPredicate;
                i++;
            }
        }
        if (type != null) {
            Predicate typePredicate = criteriaBuilder.equal(player.get("type"), type);
            tab[i] = typePredicate;
            i++;
        }

        Predicate[] predictTab = new Predicate[i];
        for (int j = 0; j < predictTab.length; j++) {
            predictTab[j] = tab[j];
        }

        criteriaQuery.where(predictTab);
        TypedQuery<Player> query = em.createQuery(criteriaQuery);
        result = query.getResultList();
        return Optional.of(result);

    }
}

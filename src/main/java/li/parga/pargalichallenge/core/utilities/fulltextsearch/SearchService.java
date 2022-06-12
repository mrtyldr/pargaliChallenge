
package li.parga.pargalichallenge.core.utilities.fulltextsearch;


import li.parga.pargalichallenge.entities.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.persistence.EntityManager;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final EntityManager entityManager;

    public List<Transaction> getTransactionBasedOnCategory(String word){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb =fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Transaction.class)
                .get();

        Query transactionQuery = qb.keyword().fuzzy()
                .onFields("account.accountType","category.categoryName","account.currency")
                .matching(word)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager
                .createFullTextQuery(transactionQuery,Transaction.class);
        return (List<Transaction>) fullTextQuery.getResultList();
    }
}

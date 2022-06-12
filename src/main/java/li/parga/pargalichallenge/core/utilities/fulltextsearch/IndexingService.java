package li.parga.pargalichallenge.core.utilities.fulltextsearch;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Slf4j
public class IndexingService {

    private final EntityManager entityManager;

    @Transactional
    public void initiateIndexing() throws InterruptedException{
        log.info("initiating indexing");
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        log.info("All entities are indexed");
    }
}

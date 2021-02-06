package io.github.mat3e;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {
    Optional<Lang> findById(Integer id) {
//        var session = HibernateUtil.getSessionFactory().openSession();
//        var transaction = session.beginTransaction();
        var lang = new Lang(5, "ysc", "qz");
        return Optional.of(lang); //delete it
    }
}



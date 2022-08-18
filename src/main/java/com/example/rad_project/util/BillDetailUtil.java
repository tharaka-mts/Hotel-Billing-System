package com.example.rad_project.util;

import com.example.rad_project.entity.BillDetail;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.print.Doc;
import java.io.File;
public class BillDetailUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        File file = new File("src/application.properties");

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .loadProperties(file)
                .build();

        Metadata metadata = new MetadataSources(standardServiceRegistry)
                .addAnnotatedClass(BillDetail.class)

                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

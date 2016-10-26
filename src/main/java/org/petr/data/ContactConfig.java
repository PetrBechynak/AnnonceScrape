package org.petr.data;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by petr on 21.10.16.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "contactEntityManagerFactory",
        transactionManagerRef = "contactTransactionManager")
class ContactConfig {

    @Bean
    PlatformTransactionManager contactTransactionManager() {
        return new JpaTransactionManager(contactEntityManagerFactory().getObject());
    }

    @Bean
    LocalContainerEntityManagerFactoryBean contactEntityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(contactDataSource());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan(ContactConfig.class.getPackage().getName());

        return factoryBean;
    }

    @Bean
    DataSource contactDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("testdb");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUseSSL(false);
        dataSource.setCharacterEncoding("UTF-8");
        return dataSource;
    }
}
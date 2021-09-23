package io.ollivander.ollivanderbackend.config;

import io.ollivander.ollivanderbackend.model.DbConst;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableBatchProcessing
@PropertySource({ "classpath:mysql.properties" })
@EnableJpaRepositories(basePackages = DbConst.REPOSITORY_PACKAGE, repositoryFactoryBeanClass = DefaultRepositoryFactoryBean.class)
@Import(DatabaseMigrationConfig.class)
public class MySqlConfig {

    @Value("classpath:/org/springframework/batch/core/schema-drop-mysql.sql")
    private Resource dropSpringBatchSchema;

    @Autowired
    private Environment env;

    @Autowired
    private JobRepository jobRegistry;

//    @Bean
//    public JobLauncher jobLauncher() {
//        SimpleJobLauncher launcher = new SimpleJobLauncher();
//        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        launcher.setJobRepository(jobRegistry);
//        return launcher;
//    }

    @Bean
    public OllivanderBatchJobManager batchJobManager() {
        return new OllivanderBatchJobManager();
    }

//    @Bean
//    public EvaluationContextExtension securityExtension() {
//        return new SecurityEvaluationContextExtension();
//    }

    /**
     * We need Flyway to already have been created, do its thing before the
     * Entity Manager Factory kicks in
     *
     * @author hungmeo
     * @return
     * @throws SQLException
     */
    @Bean
    @DependsOn(DatabaseMigrationConfig.FLYWAY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { DbConst.ENTITY_PACKAGE });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    /**
     * @author hungmeo
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws Exception {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setMaxAge(4 * 60 * 60 * 1000L);
        dataSource.setMaxWait(30 * 1000); // default 30 seconds
        dataSource.setMinEvictableIdleTimeMillis(2 * 60 * 1000); // default is 1 min

        int poolSize = env.getProperty("pool.size", Integer.class, 20);
        dataSource.setInitialSize(poolSize/2);
        dataSource.setMinIdle(poolSize / 8);
        dataSource.setMaxIdle(poolSize);
        dataSource.setMaxActive((int)(poolSize + Math.round(Math.max(10, poolSize*0.25))));

        dataSource.setValidationQuery("SELECT 1");
        dataSource.setInitSQL("SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;");

        dataSource.setDbProperties(new Properties() {

            private static final long serialVersionUID = 1L;
            {
                setProperty("cachePrepStmts", "true");
                setProperty("prepStmtCacheSize", env.getProperty("dataSource.prepStmtCacheSize", "250"));
                setProperty("prepStmtCacheSqlLimit", env.getProperty("dataSource.prepStmtCacheSize", "2048"));
            }
        });

        return dataSource;
    }

    /**
     * @author hungmeo
     * @return
     * @throws SQLException
     */
//    @Bean
//    public PlatformTransactionManager transactionManager() throws Exception {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//
//        return transactionManager;
//    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @SuppressWarnings("serial")
    private Properties additionalProperties() {

        return new Properties() {
            {
                /**
                 * Disable hbm2ddl of Hibernate, because it seems to run before
                 * Flyway runs the migration
                 */
                setProperty("spring.jpa.properties.hibernate.hb2dll.auto", env.getProperty("spring.jpa.properties.hibernate.hb2dll.auto"));
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

            }
        };

    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dropSpringBatchSchema);

        initializer.setDatabasePopulator(populator);
        return initializer;
    }

}

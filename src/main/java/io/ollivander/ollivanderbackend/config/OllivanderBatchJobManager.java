package io.ollivander.ollivanderbackend.config;

import io.ollivander.ollivanderbackend.utils.DateTimeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.repository.dao.AbstractJdbcBatchMetadataDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

@Transactional
public class OllivanderBatchJobManager {

    private static final Logger logger = LoggerFactory
            .getLogger(OllivanderBatchJobManager.class);

    @PersistenceContext
    private EntityManager manager;

    /**
     * SQL statements removing step and job executions compared to a given date.
     */
    private static final String SQL_DELETE_BATCH_STEP_EXECUTION_CONTEXT = "DELETE FROM %PREFIX%STEP_EXECUTION_CONTEXT WHERE STEP_EXECUTION_ID IN (SELECT STEP_EXECUTION_ID FROM %PREFIX%STEP_EXECUTION WHERE JOB_EXECUTION_ID IN (SELECT JOB_EXECUTION_ID FROM  %PREFIX%JOB_EXECUTION where CREATE_TIME < ?))";
    private static final String SQL_DELETE_BATCH_STEP_EXECUTION = "DELETE FROM %PREFIX%STEP_EXECUTION WHERE JOB_EXECUTION_ID IN (SELECT JOB_EXECUTION_ID FROM %PREFIX%JOB_EXECUTION where CREATE_TIME < ?)";
    private static final String SQL_DELETE_BATCH_JOB_EXECUTION_CONTEXT = "DELETE FROM %PREFIX%JOB_EXECUTION_CONTEXT WHERE JOB_EXECUTION_ID IN (SELECT JOB_EXECUTION_ID FROM  %PREFIX%JOB_EXECUTION where CREATE_TIME < ?)";
    private static final String SQL_DELETE_BATCH_JOB_EXECUTION_PARAMS = "DELETE FROM %PREFIX%JOB_EXECUTION_PARAMS WHERE JOB_EXECUTION_ID IN (SELECT JOB_EXECUTION_ID FROM %PREFIX%JOB_EXECUTION where CREATE_TIME < ?)";
    private static final String SQL_DELETE_BATCH_JOB_EXECUTION = "DELETE FROM %PREFIX%JOB_EXECUTION where CREATE_TIME < ?";
    private static final String SQL_DELETE_BATCH_JOB_INSTANCE = "DELETE FROM %PREFIX%JOB_INSTANCE WHERE JOB_INSTANCE_ID NOT IN (SELECT JOB_INSTANCE_ID FROM %PREFIX%JOB_EXECUTION)";

    /**
     * Default value for the table prefix property.
     */
    private static final String DEFAULT_TABLE_PREFIX = AbstractJdbcBatchMetadataDao.DEFAULT_TABLE_PREFIX;

    private static final int ONE_MIN = 60 * 1000;
    private static final int ONE_HOUR = ONE_MIN * 60;

    public void removeBatchJobMetaData() {

        try {

            Date date = DateTimeHelper.addTime(new Date(),
                    DateTimeHelper.MINUTE, -60);

            Query query = manager
                    .createNativeQuery(getQuery(SQL_DELETE_BATCH_STEP_EXECUTION_CONTEXT));
            query.setParameter(1, date);
            query.executeUpdate();

            query = manager
                    .createNativeQuery(getQuery(SQL_DELETE_BATCH_STEP_EXECUTION));
            query.setParameter(1, date);
            query.executeUpdate();

            query = manager
                    .createNativeQuery(getQuery(SQL_DELETE_BATCH_JOB_EXECUTION_CONTEXT));
            query.setParameter(1, date);
            query.executeUpdate();

            query = manager
                    .createNativeQuery(getQuery(SQL_DELETE_BATCH_JOB_EXECUTION_PARAMS));
            query.setParameter(1, date);
            query.executeUpdate();

            query = manager
                    .createNativeQuery(getQuery(SQL_DELETE_BATCH_JOB_EXECUTION));
            query.setParameter(1, date);
            query.executeUpdate();

            query = manager
                    .createNativeQuery(getQuery(SQL_DELETE_BATCH_JOB_INSTANCE));
            // query.setParameter(1, date);
            query.executeUpdate();
        } catch (Exception e) {
            logger.error("Unable to delete Spring Batch Meta Data..", e);
        }
    }

    private String getQuery(String base) {
        return StringUtils.replace(base, "%PREFIX%", DEFAULT_TABLE_PREFIX);
    }

    @Scheduled(fixedRate = ONE_HOUR, initialDelay = ONE_MIN)
    public void run() {
        removeBatchJobMetaData();
    }

}

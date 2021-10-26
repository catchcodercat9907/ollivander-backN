package io.ollivander.ollivanderbackend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppUtils {
    private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);
    private static final int DEFAULT_QUERY_SIZE = 1000;

    public static <T> Collection<List<T>> partition(Collection<T> list, int size) {
        final AtomicInteger counter = new AtomicInteger(0);

        return list.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size))
                .values();
    }

    public static <R, I> List<R> queryBatch(Collection<I> inputCollection, Function<Collection<I>, List<R>> fn) {
        if (CollectionUtils.isEmpty(inputCollection)) return Collections.emptyList();
        List<R> result = new ArrayList<>();
        Collection<List<I>> partitions = partition(inputCollection, DEFAULT_QUERY_SIZE);
        int i = 1;
        int total = partitions.size();
        for (List<I> partition : partitions) {
            if (total > 1) {
                logger.info("QUERY BATCH " + i + "/" + total);
            }
            i++;
            result.addAll(fn.apply(partition));
        }
        return result;
    }


    public static String readResourceFile(String resourcePath) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(resourcePath);
        return new String(FileCopyUtils.copyToByteArray(classPathResource.getInputStream()));
    }
}

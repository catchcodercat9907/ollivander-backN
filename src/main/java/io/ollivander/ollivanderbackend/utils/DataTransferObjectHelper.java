package io.ollivander.ollivanderbackend.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.ollivander.ollivanderbackend.common.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;

public class DataTransferObjectHelper {

    private static final Logger logger = LoggerFactory.getLogger(DataTransferObjectHelper.class);

    private final static String FILTER_PROPERTIES_BY_NAME = "FILTER_PROPERTIES_BY_NAME";
    private final static ObjectMapper mapper = new ObjectMapper();

    private DataTransferObjectHelper() {
    }

    /**
     * @param source
     * @param extra
     * @param properties
     * @return Map<Object, Object>
     * @throws BaseException
     */

    public static Map<Object, Object> simplify(Object source, String[] properties, Map<Object, Object> extra) {

        Map<Object, Object> output = simplify(source, properties);
        if (extra != null && !extra.isEmpty()) {
            for (Map.Entry<Object, Object> e : extra.entrySet()) {
                output.put(e.getKey(), e.getValue());
            }
        }

        return output;
    }

    public static Map<Object, Object> simplify(Object source, String... properties) {

        Map<Object, Object> output = null;

        FilterProvider filters = new SimpleFilterProvider().addFilter(FILTER_PROPERTIES_BY_NAME,
                SimpleBeanPropertyFilter.filterOutAllExcept(properties));

        ObjectWriter writer = mapper.writer(filters);

        try {
            String json = writer.writeValueAsString(source);

            output = mapper.readValue(json, new TypeReference<HashMap<Object, Object>>() {
            });

        } catch (Exception e) {
            logger.error("simplify", e);
        }

        return output;
    }

    public static <T> Collection<Object> simplify(Collection<T> sources, String... properties) {

        Collection<Object> output = null;
        if (sources != null && !sources.isEmpty()) {
            output = new ArrayList<Object>();
            for (Object source : sources) {
                Object simpleObject = simplify(source, properties);
                output.add(simpleObject);
            }
        }
        return output;
    }

    /**
     * Use this method with caution, will convert all properties of object, may include sensitive data.
     * <p>
     * This should be replaced by using method simplify() >> only extract some required fields
     *
     * @param source
     * @param ignoreProperties
     * @return
     */
    @Deprecated
    public static Map<Object, Object> convertToMap(Object source, String... ignoreProperties) {

        Map<Object, Object> shallowCopy = null;

        if (source != null) {

            shallowCopy = new HashMap<Object, Object>();
            BeanWrapper beanWrapper = new BeanWrapperImpl(source);
            PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

            for (PropertyDescriptor descriptor : propertyDescriptors) {

                String key = descriptor.getName();
                if (!"class".equals(key) && !Arrays.asList(ignoreProperties).contains(key)) {
                    Object value = beanWrapper.getPropertyValue(key);
                    shallowCopy.put(key, value);
                }
            }
        }
        return shallowCopy;
    }
}

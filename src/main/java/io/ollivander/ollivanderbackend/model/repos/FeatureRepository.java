package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.entities.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer>, JpaSpecificationExecutor<Feature> {

    Feature findByFeatureKey(String featureKey);

    List<Feature> findByFeatureKeyIn(List<String> featureKey);
}

package com.igumnov.seedproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.igumnov.seedproject.model.City;

@RepositoryRestResource(collectionResourceRel = "all", path = "city")
public interface CityRepository extends CrudRepository<City, String> {

}

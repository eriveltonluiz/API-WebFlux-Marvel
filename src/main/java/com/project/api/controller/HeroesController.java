package com.project.api.controller;

import javax.decorator.Delegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.constants.HeroesConstant;
import com.project.api.model.Heroes;
import com.project.api.repository.HeroesRepository;
import com.project.api.service.HeroesService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HeroesController {

	HeroesService heroesService;
	HeroesRepository heroesRepository;

	private static final Logger log = LoggerFactory.getLogger(HeroesController.class);

	public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
		super();
		this.heroesService = heroesService;
		this.heroesRepository = heroesRepository;
	}

	@GetMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL)
	public Flux<Heroes> getAllItems() {
		log.info("requesting the list off all heroes");
		return heroesService.findAll();
	}

	@GetMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL + "/{id}")
	public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) {
		log.info("requesting the hero width id {}", id);
		return heroesService.findByIdHero(id).map(item -> new ResponseEntity<>(item, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL)
	public Mono<Heroes> createHero(@RequestBody Heroes heroes){
		log.info("a new hero was created");
		return heroesService.save(heroes);
	}
	
	@DeleteMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL + "/{id}")
	public Mono<HttpStatus> deleteByIdHero(@PathVariable String id){
		heroesService.deleteByIdHero(id);
		log.info("deleting a hero with id {}", id);
		return Mono.just(HttpStatus.CONTINUE);
	}
}

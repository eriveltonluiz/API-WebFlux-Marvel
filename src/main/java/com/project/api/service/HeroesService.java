package com.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.model.Heroes;
import com.project.api.repository.HeroesRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class HeroesService {
	
	private final HeroesRepository heroesRepository;
	
	public Flux<Heroes> findAll(){
		return Flux.fromIterable(this.heroesRepository.findAll());
	}
	
	public Mono<Heroes> findByIdHero(String id){
		return Mono.justOrEmpty(this.heroesRepository.findById(id));
	}
	
	public Mono<Heroes> save(Heroes heroes){
		return Mono.justOrEmpty(this.heroesRepository.save(heroes));
	}
	
	public Mono<Boolean> deleteByIdHero(String id){
		heroesRepository.deleteById(id);
	    return Mono.just(true);
	}
}

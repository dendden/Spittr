package spittr.data;

import java.util.List;

import spittr.Spitter;

public interface SpitterRepository {
	
	List<Spitter> findAll();
	
	Spitter save(Spitter spitter);
	
	Spitter findByUsername(String username);

}

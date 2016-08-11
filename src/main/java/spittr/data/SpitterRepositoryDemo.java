package spittr.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import spittr.Spitter;

@Component
public class SpitterRepositoryDemo implements SpitterRepository {
	
	private List<Spitter> spitters;
	
	public SpitterRepositoryDemo() {
		this.spitters = new ArrayList<Spitter>();
	}

	public List<Spitter> findAll() {
		return spitters;
	}

	public Spitter save(Spitter spitter) {
		spitters.add(spitter);
		return spitter;
	}

	public Spitter findByUsername(String username) {
		for (Spitter s : spitters) {
			String currentUsername = s.getUsername();
			if ( currentUsername.equals(username) ) {
				return s;
			}
		}
		return null;
	}

}

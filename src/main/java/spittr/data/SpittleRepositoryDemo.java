package spittr.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import spittr.Spittle;

@Component
public class SpittleRepositoryDemo implements SpittleRepository {
	
	private List<Spittle> spittles;
	
	public SpittleRepositoryDemo() {
		this.spittles = new ArrayList<Spittle>();
	}
	
	public List<Spittle> findSpittles(long max, int count) {
		for ( int i = 0; i < count; i++ ) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}

	public Spittle findOne(long id) {
		return spittles.get((int) id);
	}

}

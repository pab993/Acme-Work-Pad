package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComentableRepository;
import domain.ComentableEntity;

@Service
@Transactional
public class ComentableService {

	@Autowired
	private ComentableRepository comentableRepository;
	
	public ComentableEntity findOneAux(int comentableId){
		Assert.isTrue(comentableId != 0);
		ComentableEntity result;
		
		result = comentableRepository.findOneAux(comentableId);
		return result;
	}
	
	
	public ComentableEntity findOne(int comentableId) {
		Assert.isTrue(comentableId != 0);
		ComentableEntity result;
		
		result = comentableRepository.findOne(comentableId);
		return result;
	}
	
	public Collection<ComentableEntity> findAll() {
		Collection<ComentableEntity> result;

		result = comentableRepository.findAll();

		return result;
	}

}

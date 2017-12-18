package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.ComentableRepository;
import domain.ComentableEntity;

@Component
@Transactional
public class StringToComentableConverter implements Converter<String, ComentableEntity>{
@Autowired ComentableRepository comentableRepository;

	@Override
	public ComentableEntity convert(String text) {
		ComentableEntity result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = comentableRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}

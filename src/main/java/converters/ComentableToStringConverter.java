package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.ComentableEntity;

@Component
@Transactional
public class ComentableToStringConverter implements Converter<ComentableEntity, String>{

	@Override
	public String convert(ComentableEntity comentable) {
		
		String result;
		if(comentable == null){
			result = null;
		}else{
			result = String.valueOf(comentable.getId());
		}
		return result;
	}

}
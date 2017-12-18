package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Bulletin;

@Component
@Transactional
public class CommentToStringConverter implements Converter<Bulletin, String>{

	@Override
	public String convert(Bulletin comment) {
		
		String result;
		if(comment == null){
			result = null;
		}else{
			result = String.valueOf(comment.getId());
		}
		return result;
	}

}
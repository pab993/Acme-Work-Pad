package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.BulletinRepository;
import domain.Bulletin;

@Component
@Transactional
public class StringToCommentConverter implements Converter<String, Bulletin>{
@Autowired BulletinRepository commentRepository;

	@Override
	public Bulletin convert(String text) {
		Bulletin result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = commentRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}

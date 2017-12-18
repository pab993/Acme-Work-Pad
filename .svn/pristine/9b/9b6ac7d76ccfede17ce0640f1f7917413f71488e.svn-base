
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.SeminaryRepository;
import domain.Seminary;

@Component
@Transactional
public class StringToSeminaryConverter implements Converter<String, Seminary> {

	@Autowired
	SeminaryRepository	seminaryRepository;


	@Override
	public Seminary convert(final String text) {
		Seminary result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.seminaryRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}

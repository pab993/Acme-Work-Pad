
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Seminary;

@Component
@Transactional
public class SeminaryToStringConverter implements Converter<Seminary, String> {

	@Override
	public String convert(final Seminary seminary) {

		String result;
		if (seminary == null)
			result = null;
		else
			result = String.valueOf(seminary.getId());
		return result;
	}

}

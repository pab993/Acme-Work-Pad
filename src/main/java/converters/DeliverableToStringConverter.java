
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Deliverable;

@Component
@Transactional
public class DeliverableToStringConverter implements Converter<Deliverable, String> {

	@Override
	public String convert(final Deliverable deliverable) {

		String result;
		if (deliverable == null)
			result = null;
		else
			result = String.valueOf(deliverable.getId());
		return result;
	}

}

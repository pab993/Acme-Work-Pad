
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.DeliverableRepository;
import domain.Deliverable;

@Component
@Transactional
public class StringToDeliverableConverter implements Converter<String, Deliverable> {

	@Autowired
	DeliverableRepository	deliverableRepository;


	@Override
	public Deliverable convert(final String text) {
		Deliverable result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.deliverableRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}


package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.ActivityRecordRepository;
import domain.ActivityRecord;

@Component
@Transactional
public class StringToActivityRecordConverter implements Converter<String, ActivityRecord> {

	@Autowired
	ActivityRecordRepository	activityRecordRepository;


	@Override
	public ActivityRecord convert(final String text) {
		ActivityRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.activityRecordRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}

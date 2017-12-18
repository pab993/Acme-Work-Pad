
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.ActivityRecord;

@Component
@Transactional
public class ActivityRecordToStringConverter implements Converter<ActivityRecord, String> {

	@Override
	public String convert(final ActivityRecord activityRecord) {

		String result;
		if (activityRecord == null)
			result = null;
		else
			result = String.valueOf(activityRecord.getId());
		return result;
	}

}

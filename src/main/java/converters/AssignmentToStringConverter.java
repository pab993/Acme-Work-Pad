
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Assignment;

@Component
@Transactional
public class AssignmentToStringConverter implements Converter<Assignment, String> {

	@Override
	public String convert(final Assignment assignment) {

		String result;
		if (assignment == null)
			result = null;
		else
			result = String.valueOf(assignment.getId());
		return result;
	}

}

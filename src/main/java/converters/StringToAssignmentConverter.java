
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import repositories.AssignmentRepository;
import domain.Assignment;

public class StringToAssignmentConverter implements Converter<String, Assignment> {

	@Autowired
	AssignmentRepository	assignmentRepository;


	@Override
	public Assignment convert(final String text) {
		Assignment result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.assignmentRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

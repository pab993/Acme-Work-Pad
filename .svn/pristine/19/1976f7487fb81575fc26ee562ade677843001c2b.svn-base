
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.BibliographyRecordRepository;
import domain.BibliographyRecord;

@Component
@Transactional
public class StringToBibliographyRecordConverter implements Converter<String, BibliographyRecord> {

	@Autowired
	BibliographyRecordRepository	bibliographyRecordRepository;


	@Override
	public BibliographyRecord convert(final String text) {
		BibliographyRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.bibliographyRecordRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}

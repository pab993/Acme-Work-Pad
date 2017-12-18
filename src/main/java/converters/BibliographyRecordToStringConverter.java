
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.BibliographyRecord;

@Component
@Transactional
public class BibliographyRecordToStringConverter implements Converter<BibliographyRecord, String> {

	@Override
	public String convert(final BibliographyRecord bibliographyRecord) {

		String result;
		if (bibliographyRecord == null)
			result = null;
		else
			result = String.valueOf(bibliographyRecord.getId());
		return result;
	}

}

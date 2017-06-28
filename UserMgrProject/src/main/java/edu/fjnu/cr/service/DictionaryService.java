package edu.fjnu.cr.service;

import java.util.List;
import java.util.Map;

import edu.fjnu.cr.domain.Dictionary;

public interface DictionaryService {

	List<Dictionary> loadDictionaryByType(Integer type) throws Exception;
	
	Dictionary fingdDictionartById(Integer id) throws Exception;
	
	List<Map<String,Object>> loadDictionaryComboTree(Integer type) throws Exception;
}


 
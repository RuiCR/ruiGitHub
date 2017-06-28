package edu.fjnu.cr.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fjnu.cr.domain.Dictionary;
import edu.fjnu.cr.domain.DictionaryExample;
import edu.fjnu.cr.domain.SysPermission;
import edu.fjnu.cr.mapper.DictionaryMapper;
import edu.fjnu.cr.service.DictionaryService;

@Service("dictionaryService")
public class DictionaryServiceImp implements DictionaryService {

	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Override
	public List<Dictionary> loadDictionaryByType(Integer type) throws Exception {
		DictionaryExample example = new DictionaryExample();
		DictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andDictTypeEqualTo(type);
		List<Dictionary> dicList = dictionaryMapper.selectByExample(example );
		return dicList;
	}

	@Override
	public Dictionary fingdDictionartById(Integer id) throws Exception {
		Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(id);
		return dictionary;
	}

	@Override
	public List<Map<String, Object>> loadDictionaryComboTree(Integer type) throws Exception {
		List<Dictionary> dicList = this.loadDictionaryByType(type);
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		Map<String, Object> treeNode = null;
		Map<String, Map<String, Object>> id_nodeMap = new HashMap<String, Map<String, Object>>();
		for (Dictionary dic : dicList) {
			String parentid = dic.getDictParent()+"";
			treeNode = new HashMap<String, Object>();
			treeNode.put("id", dic.getDictId()+"");
			treeNode.put("text", dic.getDictName());
			String dic_id = dic.getDictId() + "";
			id_nodeMap.put(dic_id, treeNode);
			if (dic.getDictParent()==0) {
				treeList.add(treeNode);
			} else {
				Map<String, Object> parenNode = id_nodeMap.get(dic.getDictParent()+"");
				if (parenNode != null) {
					List<Map<String, Object>> childList = null;
					if (parenNode.get("children") == null) {
						childList = new ArrayList<Map<String, Object>>();
					} else {
						childList = (List<Map<String, Object>>) parenNode.get("children");
					}
					childList.add(treeNode);
					parenNode.put("children", childList);
				}
			}
		}
		return treeList;
	}

}

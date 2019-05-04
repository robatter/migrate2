package com.robatter.app.migrate.service;

import com.robatter.app.migrate.constants.MigrateConstant;
import com.robatter.app.migrate.dto.TableColumnDto;
import com.robatter.app.migrate.dto.TableRefDto;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MigrateHelper {
    static Logger logger = LoggerFactory.getLogger(MigrateHelper.class);

    public static TableRefDto parse(InputStream is, Map<String, Map<String, TableColumnDto>> tablesMap){
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(is);
            Element root = doc.getRootElement();
            TableRefDto dto = ele2dto(root);
            dto.setColsMap(tablesMap.get(dto.getId()));
            dto.setNamespace(dto.getId());
            parse(dto,root,tablesMap);
            return dto;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    static void parse(TableRefDto parentDto, Element ele, Map<String, Map<String, TableColumnDto>> tablesMap){
        List<Element> list = ele.elements();
        if(CollectionUtils.isEmpty(list)) {
            return;
        }
        List<TableRefDto> dtos = new ArrayList<>();
        for(Element e : list){
            TableRefDto dto = ele2dto(e);
            dto.setNamespace(dto.getId() + MigrateConstant.separator + parentDto.getNamespace());
            dto.setColsMap(tablesMap.get(dto.getId()));
            parse(dto,e, tablesMap);
            dtos.add(dto);
        }
        parentDto.setChildren(dtos);
    }

    static TableRefDto ele2dto(Element ele){
        TableRefDto dto = new TableRefDto();
        dto.setId(ele.attributeValue("id"));
        dto.setFilter(ele.attributeValue("filter"));
        dto.setBackIgnore(ele.attributeValue("backIgnore"));
        dto.setVirtual(ele.attributeValue("virtual"));
        dto.setOutput(ele.attributeValue("output"));
        dto.setValid(ele.attributeValue("valid"));
        return dto;
    }

    public static final String unwrap(String wrappedStr){
        String unwrap = wrappedStr.replace("${", "").replace("}","");
        return unwrap;
    }

    public static final String convertObject2String(Object obj){
        String str = "";
        if(obj instanceof String){
            str = (String)obj;
        }else if(obj instanceof Integer){
            str = ((Integer) obj).toString();
        }
        return str;
    }

    static Pattern pattern = Pattern.compile("\\$\\{(\\w+|\\.|_)\\}");
    public static final Set<String> pickUpKey(String filter){
        Set<String> set = new HashSet<>();
        Matcher matcher = pattern.matcher(filter);
        while(matcher.find()){
            set.add(unwrap(matcher.group()));
        }
        return set;
    }

    public static final String loopGetParentKeyValue(String key, Map<String,Object> context){
       do{
            Object obj = context.get(key);
            if(null != obj){
                return convertObject2String(obj);
            }
            key = getParentKey(key);
        } while( key != null);
        return null;
    }

    public static final String getParentKey(String key){
        if(key.indexOf(MigrateConstant.separator) > -1) {
            String parentKey = key.substring(key.indexOf(MigrateConstant.separator) + 1, key.length());
            return parentKey;
        }
        return null;
    }
}

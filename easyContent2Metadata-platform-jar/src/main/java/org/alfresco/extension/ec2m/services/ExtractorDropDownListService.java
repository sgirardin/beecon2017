package org.alfresco.extension.ec2m.services;

import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.namespace.QName;
import org.springframework.extensions.webscripts.DeclarativeWebScript;

import java.util.ArrayList;
import java.util.Collection;


public class ExtractorDropDownListService extends BaseProcessorExtension {

    private DictionaryService dictionaryService;

    public Collection<QName> retrieveAllDocumentModelQName(){
        return dictionaryService.getAllModels();
    }

    public Collection<QName> retrieveAllMetadataFieldsForQName(String documentModelQName){
        QName qnameToSearch = QName.createQName(documentModelQName);

        Collection<QName> metadataQNames = new ArrayList<>();

        if (dictionaryService.getAllModels().contains(qnameToSearch)) {
            metadataQNames.addAll(dictionaryService.getProperties(qnameToSearch));
            metadataQNames.addAll(dictionaryService.getAspects(qnameToSearch));
        } else {
            metadataQNames = new ArrayList<>();
        }

        return metadataQNames;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
}
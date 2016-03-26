/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate.implementation;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.gamestate.IDiseaseData;
import java.util.HashMap;
import java.util.Map;

/**
 * Uses a map from a DiseaseType enum to a DiseaseStatusInfo object to determine the stats for a Disease. Namely it it has been cured or eradicated.
 * @author James
 */
public class MappedDiseaseData implements IDiseaseData
{
    private Map<DiseaseType,DiseaseStatusInfo> data;
    
    public MappedDiseaseData()
    {
        this(new HashMap<DiseaseType,DiseaseStatusInfo>());
    }
    
    public MappedDiseaseData(Map<DiseaseType,DiseaseStatusInfo> newMap)
    {
        data = newMap;
        DiseaseType[] types = DiseaseType.values();
        for(DiseaseType type:types)
        {
            data.put(type, new DiseaseStatusInfo());
        }
    }

    @Override
    public boolean isCured(DiseaseType vir) 
    {
        return data.get(vir).isCured();
    }

    @Override
    public boolean isEradicated(DiseaseType vir) 
    {
        return data.get(vir).isEradicated();
    }

    @Override
    public void cure(DiseaseType vir) 
    {
        data.get(vir).cure();
    }

    @Override
    public void decure(DiseaseType vir) 
    {
        data.get(vir).decure();
    }

    @Override
    public void eradicate(DiseaseType vir) 
    {
        data.get(vir).eradicate();
    }

    @Override
    public void deeradicate(DiseaseType vir) 
    {
        data.get(vir).deeradicate();
    }

    @Override
    public boolean allCured()
    {
        for(DiseaseType type:DiseaseType.values())
        {
            if(!data.get(type).isCured())
            {
                return false;
            }
        }
        return true;
    }
    
}
